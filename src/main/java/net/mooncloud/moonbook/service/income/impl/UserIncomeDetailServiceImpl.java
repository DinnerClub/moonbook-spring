package net.mooncloud.moonbook.service.income.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeDailySubtotal;
import net.mooncloud.moonbook.entity.income.UserIncomeDetail;
import net.mooncloud.moonbook.entity.income.UserIncomeMode;
import net.mooncloud.moonbook.repository.income.UserIncomeDailySubtotalDao;
import net.mooncloud.moonbook.repository.income.UserIncomeDetailDao;
import net.mooncloud.moonbook.repository.income.UserIncomeModeDao;
import net.mooncloud.moonbook.service.income.UserIncomeDetailService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
@Service("userIncomeDetailService")
public class UserIncomeDetailServiceImpl implements UserIncomeDetailService
{
	@Autowired
	UserIncomeDetailDao userIncomeDetailDao;
	@Autowired
	UserIncomeDailySubtotalDao userIncomeDailySubtotalDao;
	@Autowired
	UserIncomeModeDao userIncomeModeDao;

	@Override
	public UserIncomeDetail save(UserIncomeDetail userIncomeDetail)
	{
		return update(userIncomeDetailDao.get(userIncomeDetail.getDetailid()), userIncomeDetail);
	}

	@Override
	public UserIncomeDetail delete(UserIncomeDetail userIncomeDetail)
	{
		return update(userIncomeDetail, null);
	}

	public UserIncomeDetail update(UserIncomeDetail userIncomeDetailOrigin, UserIncomeDetail userIncomeDetail)
	{
		if (userIncomeDetailOrigin == null && userIncomeDetail == null)
		{
			return null;
		}

		double moneyDiff = 0.00;

		if (userIncomeDetailOrigin == null)
		{// insert
			userIncomeDetailDao.insertIgnore(userIncomeDetail);

			// daily subtotal
			moneyDiff = userIncomeDetail.getMoney() - 0;
			if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
			{
				updateDailySubtotal(userIncomeDetail, userIncomeDetail.getMoney());
			}

			// user mode
			updateUserModeCount(userIncomeDetail, 1);

		}
		else if (userIncomeDetail == null)
		{// delete
			userIncomeDetail = userIncomeDetailOrigin;

			userIncomeDetail.setStatus((short) 1);
			userIncomeDetailDao.update(userIncomeDetail);

			// daily subtotal
			moneyDiff = 0 - userIncomeDetail.getMoney();
			if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
			{
				updateDailySubtotal(userIncomeDetail, moneyDiff);
			}

			// user mode
			updateUserModeCount(userIncomeDetail, -1);
		}
		else
		{// update
			userIncomeDetailDao.update(userIncomeDetail);

			// daily subtotal
			moneyDiff = userIncomeDetail.getMoney() - userIncomeDetailOrigin.getMoney();
			if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
			{
				updateDailySubtotal(userIncomeDetail, userIncomeDetail.getMoney());
				updateDailySubtotal(userIncomeDetailOrigin, 0 - userIncomeDetailOrigin.getMoney());
			}

			// user mode
			if (userIncomeDetail.getSid() != userIncomeDetailOrigin.getSid())
			{
				updateUserModeCount(userIncomeDetail, 1);
				updateUserModeCount(userIncomeDetailOrigin, -1);
			}
		}

		return userIncomeDetail;
	}

	private void updateDailySubtotal(UserIncomeDetail userIncomeDetail, double moneyDiff)
	{
		if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
		{
			UserIncomeDailySubtotal userIncomeDailySubtotal = new UserIncomeDailySubtotal();
			userIncomeDailySubtotal.setUserid(userIncomeDetail.getUserid());
			userIncomeDailySubtotal.setThedate(userIncomeDetail.getThedate());
			userIncomeDailySubtotal.setYear(userIncomeDetail.getYear());
			userIncomeDailySubtotal.setMonth(userIncomeDetail.getMonth());
			userIncomeDailySubtotal.setDate(userIncomeDetail.getDate());
			userIncomeDailySubtotal.setDay(userIncomeDetail.getDay());
			userIncomeDailySubtotal.setMoney(moneyDiff);
			userIncomeDailySubtotal.setSyn(userIncomeDetail.getSyn());
			userIncomeDailySubtotal.setCreated(userIncomeDetail.getCreated());
			userIncomeDailySubtotal.setUpdated(userIncomeDetail.getUpdated());
			// userIncomeDailySubtotal.setStatus(userIncomeDetail.getStatus());
			userIncomeDailySubtotalDao.incrementalUpdateMoney(userIncomeDailySubtotal);
		}
	}

	private void updateUserModeCount(UserIncomeDetail userIncomeDetail, long count)
	{
		UserIncomeMode userIncomeMode = userIncomeModeDao.getModeName(userIncomeDetail.getMid(), userIncomeDetail.getSid());
		userIncomeMode.setUserid(userIncomeDetail.getUserid());
		userIncomeMode.setCount(count);
		userIncomeMode.setSyn(userIncomeDetail.getSyn());
		userIncomeMode.setCreated(userIncomeDetail.getCreated());
		userIncomeMode.setUpdated(userIncomeDetail.getUpdated());
		// userIncomeMode.setStatus(userIncomeDetail.getStatus());
		userIncomeModeDao.incrementalUpdateCount(userIncomeMode);
	}

	@Override
	public List<UserIncomeDetail> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_income_detail";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userIncomeDetailDao.search(queryMap);
	}
}
