package net.mooncloud.moonbook.service.payment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentCategory;
import net.mooncloud.moonbook.repository.payment.UserPaymentCategoryDao;
import net.mooncloud.moonbook.service.payment.UserPaymentCategoryService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userPaymentCategoryService")
public class UserPaymentCategoryServiceImpl implements UserPaymentCategoryService
{
	@Autowired
	UserPaymentCategoryDao userPaymentCategoryDao;

	@Override
	public UserPaymentCategory save(UserPaymentCategory userPaymentCategory)
	{
		userPaymentCategoryDao.insertUpdate(userPaymentCategory);
		return userPaymentCategory;
		// return
		// update(userPaymentCategoryDao.getCatName(userPaymentCategory.getPid(),
		// userPaymentCategory.getCid()), userPaymentCategory);
	}

	@Override
	public UserPaymentCategory delete(UserPaymentCategory userPaymentCategory)
	{
		userPaymentCategoryDao.delete(userPaymentCategory.getUserid(), userPaymentCategory.getPid(), userPaymentCategory.getCid());
		return userPaymentCategory;
		// return update(userPaymentCategory, null);
	}

	public UserPaymentCategory update(UserPaymentCategory userPaymentCategoryOrigin, UserPaymentCategory userPaymentCategory)
	{
		if (userPaymentCategoryOrigin == null && userPaymentCategory == null)
		{
			return null;
		}

		if (userPaymentCategory == null)
		{// delete
			userPaymentCategory = userPaymentCategoryOrigin;

			userPaymentCategoryDao.delete(userPaymentCategory.getUserid(), userPaymentCategory.getPid(), userPaymentCategory.getCid());
		}
		else
		{// insert or update
			userPaymentCategoryDao.insertUpdate(userPaymentCategory);
		}

		return userPaymentCategory;
	}

	@Override
	public List<UserPaymentCategory> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_payment_category";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userPaymentCategoryDao.search(queryMap);
	}
}
