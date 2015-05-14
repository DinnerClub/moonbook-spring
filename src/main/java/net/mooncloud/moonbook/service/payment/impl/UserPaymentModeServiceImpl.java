package net.mooncloud.moonbook.service.payment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentMode;
import net.mooncloud.moonbook.repository.payment.UserPaymentModeDao;
import net.mooncloud.moonbook.service.payment.UserPaymentModeService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userPaymentModeService")
public class UserPaymentModeServiceImpl implements UserPaymentModeService
{
	@Autowired
	UserPaymentModeDao userPaymentModeDao;

	@Override
	public UserPaymentMode save(UserPaymentMode userPaymentMode)
	{
		userPaymentModeDao.insertUpdate(userPaymentMode);
		return userPaymentMode;
		// return
		// update(userPaymentModeDao.getCatName(userPaymentMode.getmid(),
		// userPaymentMode.getsid()), userPaymentMode);
	}

	@Override
	public UserPaymentMode delete(UserPaymentMode userPaymentMode)
	{
		userPaymentModeDao.delete(userPaymentMode.getUserid(), userPaymentMode.getMid(), userPaymentMode.getSid());
		return userPaymentMode;
		// return update(userPaymentMode, null);
	}

	public UserPaymentMode update(UserPaymentMode userPaymentModeOrigin, UserPaymentMode userPaymentMode)
	{
		if (userPaymentModeOrigin == null && userPaymentMode == null)
		{
			return null;
		}

		if (userPaymentMode == null)
		{// delete
			userPaymentMode = userPaymentModeOrigin;

			userPaymentModeDao.delete(userPaymentMode.getUserid(), userPaymentMode.getMid(), userPaymentMode.getSid());
		}
		else
		{// insert or update
			userPaymentModeDao.insertUpdate(userPaymentMode);
		}

		return userPaymentMode;
	}

	@Override
	public List<UserPaymentMode> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_payment_mode";
		table = " (SELECT `userid`,`mid`, `mname`, `sid`, `sname`,`count`,`syn`,`created`,`updated`,`status` FROM `user_payment_mode` WHERE userid=" + querys.get("userid")
				+ " UNION (SELECT " + querys.get("userid") + " AS userid,`mid`, `mname`,`sid`, `sname`,`count`,`syn`,`created`,`updated`,`status` FROM `book_payment_mode`"
				+ " WHERE (`mid`,`sid`) NOT IN (SELECT `mid`,`sid` FROM `user_payment_mode` WHERE userid=" + querys.get("userid") + "))) T ";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userPaymentModeDao.search(queryMap);
	}
}
