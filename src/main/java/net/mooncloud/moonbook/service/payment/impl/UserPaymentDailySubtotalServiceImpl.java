package net.mooncloud.moonbook.service.payment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDailySubtotal;
import net.mooncloud.moonbook.repository.payment.UserPaymentDailySubtotalDao;
import net.mooncloud.moonbook.service.payment.UserPaymentDailySubtotalService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;

public class UserPaymentDailySubtotalServiceImpl implements UserPaymentDailySubtotalService
{
	@Autowired
	UserPaymentDailySubtotalDao userPaymentDailySubtotalDao;

	@Override
	public UserPaymentDailySubtotal save(UserPaymentDailySubtotal userPaymentDailySubtotal)
	{
		userPaymentDailySubtotalDao.insertUpdate(userPaymentDailySubtotal);
		return userPaymentDailySubtotal;
		// return
		// update(userPaymentDailySubtotalDao.getCatName(userPaymentDailySubtotal.getPid(),
		// userPaymentDailySubtotal.getCid()), userPaymentDailySubtotal);
	}

	@Override
	public UserPaymentDailySubtotal delete(UserPaymentDailySubtotal userPaymentDailySubtotal)
	{
		userPaymentDailySubtotalDao.delete(userPaymentDailySubtotal.getUserid(), userPaymentDailySubtotal.getThedate());
		return userPaymentDailySubtotal;
		// return update(userPaymentDailySubtotal, null);
	}

	public UserPaymentDailySubtotal update(UserPaymentDailySubtotal userPaymentDailySubtotalOrigin, UserPaymentDailySubtotal userPaymentDailySubtotal)
	{
		if (userPaymentDailySubtotalOrigin == null && userPaymentDailySubtotal == null)
		{
			return null;
		}

		if (userPaymentDailySubtotal == null)
		{// delete
			userPaymentDailySubtotal = userPaymentDailySubtotalOrigin;

			userPaymentDailySubtotalDao.delete(userPaymentDailySubtotal.getUserid(), userPaymentDailySubtotal.getThedate());
		}
		else
		{// insert or update
			userPaymentDailySubtotalDao.insertUpdate(userPaymentDailySubtotal);
		}

		return userPaymentDailySubtotal;
	}

	@Override
	public List<UserPaymentDailySubtotal> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_payment_daily_subtotal";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userPaymentDailySubtotalDao.search(queryMap);
	}
}
