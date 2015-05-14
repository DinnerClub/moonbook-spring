package net.mooncloud.moonbook.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.NamingException;

import net.mooncloud.moonbook.Constants;
import net.mooncloud.moonbook.entity.user.User;
import net.mooncloud.moonbook.repository.user.UserDao;
import net.mooncloud.moonbook.service.user.UserService;
import net.mooncloud.moonbook.utils.MD5Hash;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	UserDao userDao;

	@Override
	public User save(User user)
	{
		userDao.insertUpdate(user);
		return user;
		// return
		// update(userDao.getCatName(user.getPid(),
		// user.getCid()), user);
	}

	@Override
	public User delete(User user)
	{
		userDao.delete(user.getUserid());
		return user;
		// return update(user, null);
	}

	public User update(User userOrigin, User user)
	{
		if (userOrigin == null && user == null)
		{
			return null;
		}

		if (user == null)
		{// delete
			user = userOrigin;

			userDao.delete(user.getUserid());
		}
		else
		{// insert or update
			userDao.insertUpdate(user);
		}

		return user;
	}

	@Override
	public List<User> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "auth_user";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userDao.search(queryMap);
	}

	@Override
	public User encrypt(User user)
	{
		MD5Hash passwordMD5Hash = new MD5Hash(user.getSalt());// MD5Hash.digest(user.getPassword());

		long min = 1000000000000000000L;
		long max = 9223372036854775807L;

		long seed = user.getUpdated().getTime() / 1000;
		Random r = new Random(seed);
		String secretkey = String.valueOf((long) (r.nextDouble() * (max - min) + min));
		String secretkey2 = String.valueOf((long) (r.nextDouble() * (max - min) + min));

		MD5Hash secretkeyMD5Hash = MD5Hash.digest(secretkey);
		MD5Hash secretkey2MD5Hash = MD5Hash.digest(secretkey2);
		MD5Hash secretPassword = MD5Hash.xor(passwordMD5Hash, secretkeyMD5Hash);

		user.setSalt(secretPassword.toString());
		user.setPassword(secretkey2MD5Hash.toString());

		user.setPassword(user.getPassword().substring(0, 16) + user.getSalt() + user.getPassword().substring(16));

		return user;
	}

	@Override
	public User decrypt(User user)
	{
		MD5Hash secretPassword = new MD5Hash(user.getSalt());

		long min = 1000000000000000000L;
		long max = 9223372036854775807L;

		long seed = user.getUpdated().getTime() / 1000;
		Random r = new Random(seed);
		String secretkey = String.valueOf((long) (r.nextDouble() * (max - min) + min));
		String secretkey2 = String.valueOf((long) (r.nextDouble() * (max - min) + min));

		MD5Hash secretkeyMD5Hash = MD5Hash.digest(secretkey);
		MD5Hash secretkey2MD5Hash = MD5Hash.digest(secretkey2);
		MD5Hash passwordMD5Hash = MD5Hash.xor(secretPassword, secretkeyMD5Hash);

		user.setSalt(passwordMD5Hash.toString());
		return user;
	}

	@Override
	public User signUp(User user)
	{
		try
		{
			Map<String, Object> querys = new HashMap<String, Object>();
			querys.put("username", user.getUsername());
			List<User> users = search(querys);

			if (users.size() > 0)
			{
				throw new NamingException(user.getUsername() + " already exist");
			}

			user.setStatus(Constants.USER_STATUS);
			save(user);

			user.setStatus(Constants.USER_STATUS_SIGUP_SUCCESS);
		}
		catch (NamingException e)
		{
			user.setStatus(Constants.USER_STATUS_SIGUP_USERNAME_ALREADY_EXIST);
			// e.printStackTrace();
		}
		catch (Exception e)
		{
			user.setStatus(Constants.USER_STATUS_SIGUP_FAILED);
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User signIn(User user)
	{
		try
		{
			Map<String, Object> querys = new HashMap<String, Object>();
			querys.put("username", user.getUsername());
			List<User> users = search(querys);

			if (users.size() > 0)
			{
				User userServer = users.get(0);
				decrypt(userServer);
				decrypt(user);

				if (userServer.getSalt().equals(user.getSalt()))
				{
					user.setUserid(userServer.getUserid());
					user.setUsername(userServer.getUsername());
					user.setUsernick(userServer.getUsernick());
					user.setEmail(userServer.getEmail());
					user.setMobile(userServer.getMobile());
					user.setSyn(userServer.getSyn());
					user.setCreated(userServer.getCreated());
					user.setUpdated(userServer.getUpdated());
					user.setStatus(Constants.USER_STATUS_SIGIN_SUCCESS);
				}
				else
				{
					user.setStatus(Constants.USER_STATUS_SIGIN_PASSWORD_ERROR);
				}
			}
			else
			{
				user.setStatus(Constants.USER_STATUS_SIGIN_USERNAME_NOT_EXIST);
			}
		}
		catch (Exception e)
		{
			user.setStatus(Constants.USER_STATUS_SIGIN_FAILED);
			e.printStackTrace();
		}

		user.setPassword(null);
		user.setSalt(null);

		return user;
	}

	@Override
	public User changeUsernick(User user)
	{
		try
		{
			Map<String, Object> querys = new HashMap<String, Object>();
			querys.put("username", user.getUserid());
			List<User> users = search(querys);

			User userServer = users.get(0);

			decrypt(userServer);

			userServer.setUsernick(user.getUsernick());
			userServer.setSyn(user.getSyn());
			userServer.setUpdated(user.getUpdated());

			encrypt(userServer);

			userDao.update(userServer);

			userServer.setPassword(null);
			userServer.setSalt(null);

			userServer.setStatus(Constants.USER_STATUS_UPDATE_SUCCESS);
			return userServer;
		}
		catch (Exception e)
		{
			user.setStatus(Constants.USER_STATUS_UPDATE_FAILED);
			e.printStackTrace();
		}

		user.setPassword(null);
		user.setSalt(null);

		return user;
	}

	@Override
	public User changePassword(User user, MD5Hash newPassword)
	{
		try
		{
			Map<String, Object> querys = new HashMap<String, Object>();
			querys.put("username", user.getUsername());
			List<User> users = search(querys);

			if (users.size() > 0)
			{
				User userServer = users.get(0);
				decrypt(userServer);
				decrypt(user);

				if (userServer.getSalt().equals(user.getSalt()))
				{
					userServer.setPassword(newPassword.toString());
					userServer.setSalt(newPassword.toString());
					userServer.setSyn(user.getSyn());
					userServer.setUpdated(user.getUpdated());
					encrypt(userServer);
					userDao.update(userServer);

					userServer.setStatus(Constants.USER_STATUS_UPDATE_SUCCESS);
					userServer.setPassword(null);
					userServer.setSalt(null);
					return userServer;
				}
				else
				{
					user.setStatus(Constants.USER_STATUS_SIGIN_PASSWORD_ERROR);
				}
			}
			else
			{
				user.setStatus(Constants.USER_STATUS_SIGIN_USERNAME_NOT_EXIST);
			}
		}
		catch (Exception e)
		{
			user.setStatus(Constants.USER_STATUS_UPDATE_FAILED);
			e.printStackTrace();
		}

		user.setPassword(null);
		user.setSalt(null);

		return user;
	}

}
