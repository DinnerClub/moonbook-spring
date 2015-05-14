package net.mooncloud.moonbook.service.user;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.user.User;
import net.mooncloud.moonbook.utils.MD5Hash;

public interface UserService
{
	public User save(User user);

	public User delete(User user);

	public List<User> search(Map<String, Object> querys);

	/**
	 * 加密
	 * 
	 * @param user
	 * @return
	 */
	public User encrypt(User user);

	/**
	 * 解密
	 * 
	 * @param user
	 * @return
	 */
	public User decrypt(User user);

	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 */
	public User signIn(User user);

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	public User signUp(User user);

	public User changeUsernick(User user);

	public User changePassword(User user, MD5Hash newPassword);
}
