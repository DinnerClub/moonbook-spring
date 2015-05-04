package net.mooncloud.moonbook.service.income.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeDailySubtotal;
import net.mooncloud.moonbook.repository.income.UserIncomeDailySubtotalDao;
import net.mooncloud.moonbook.service.income.UserIncomeDailySubtotalService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userIncomeDailySubtotalService")
public class UserIncomeDailySubtotalServiceImpl implements UserIncomeDailySubtotalService
{
	@Autowired
	UserIncomeDailySubtotalDao userIncomeDailySubtotalDao;

	@Override
	public UserIncomeDailySubtotal save(UserIncomeDailySubtotal userIncomeDailySubtotal)
	{
		userIncomeDailySubtotalDao.insertUpdate(userIncomeDailySubtotal);
		return userIncomeDailySubtotal;
		// return
		// update(userIncomeDailySubtotalDao.getCatName(userIncomeDailySubtotal.getPid(),
		// userIncomeDailySubtotal.getCid()), userIncomeDailySubtotal);
	}

	@Override
	public UserIncomeDailySubtotal delete(UserIncomeDailySubtotal userIncomeDailySubtotal)
	{
		userIncomeDailySubtotalDao.delete(userIncomeDailySubtotal.getUserid(), userIncomeDailySubtotal.getThedate());
		return userIncomeDailySubtotal;
		// return update(userIncomeDailySubtotal, null);
	}

	public UserIncomeDailySubtotal update(UserIncomeDailySubtotal userIncomeDailySubtotalOrigin, UserIncomeDailySubtotal userIncomeDailySubtotal)
	{
		if (userIncomeDailySubtotalOrigin == null && userIncomeDailySubtotal == null)
		{
			return null;
		}

		if (userIncomeDailySubtotal == null)
		{// delete
			userIncomeDailySubtotal = userIncomeDailySubtotalOrigin;

			userIncomeDailySubtotalDao.delete(userIncomeDailySubtotal.getUserid(), userIncomeDailySubtotal.getThedate());
		}
		else
		{// insert or update
			userIncomeDailySubtotalDao.insertUpdate(userIncomeDailySubtotal);
		}

		return userIncomeDailySubtotal;
	}

	@Override
	public List<UserIncomeDailySubtotal> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_income_daily_subtotal";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userIncomeDailySubtotalDao.search(queryMap);
	}
}
