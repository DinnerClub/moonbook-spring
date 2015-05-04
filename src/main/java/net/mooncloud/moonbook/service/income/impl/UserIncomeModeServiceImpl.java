package net.mooncloud.moonbook.service.income.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeMode;
import net.mooncloud.moonbook.repository.income.UserIncomeModeDao;
import net.mooncloud.moonbook.service.income.UserIncomeModeService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userIncomeModeService")
public class UserIncomeModeServiceImpl implements UserIncomeModeService
{
	@Autowired
	UserIncomeModeDao userIncomeModeDao;

	@Override
	public UserIncomeMode save(UserIncomeMode userIncomeMode)
	{
		userIncomeModeDao.insertUpdate(userIncomeMode);
		return userIncomeMode;
		// return
		// update(userIncomeModeDao.getCatName(userIncomeMode.getPid(),
		// userIncomeMode.getCid()), userIncomeMode);
	}

	@Override
	public UserIncomeMode delete(UserIncomeMode userIncomeMode)
	{
		userIncomeModeDao.delete(userIncomeMode.getUserid(), userIncomeMode.getMid(), userIncomeMode.getSid());
		return userIncomeMode;
		// return update(userIncomeMode, null);
	}

	public UserIncomeMode update(UserIncomeMode userIncomeModeOrigin, UserIncomeMode userIncomeMode)
	{
		if (userIncomeModeOrigin == null && userIncomeMode == null)
		{
			return null;
		}

		if (userIncomeMode == null)
		{// delete
			userIncomeMode = userIncomeModeOrigin;

			userIncomeModeDao.delete(userIncomeMode.getUserid(), userIncomeMode.getMid(), userIncomeMode.getSid());
		}
		else
		{// insert or update
			userIncomeModeDao.insertUpdate(userIncomeMode);
		}

		return userIncomeMode;
	}

	@Override
	public List<UserIncomeMode> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_income_mode";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userIncomeModeDao.search(queryMap);
	}
}
