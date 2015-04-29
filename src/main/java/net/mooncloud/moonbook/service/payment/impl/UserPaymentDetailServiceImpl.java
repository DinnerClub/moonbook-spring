package net.mooncloud.moonbook.service.payment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDailySubtotal;
import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
import net.mooncloud.moonbook.repository.payment.UserPaymentDailySubtotalDao;
import net.mooncloud.moonbook.repository.payment.UserPaymentDetailDao;
import net.mooncloud.moonbook.service.chart.impl.SqlFacetQueryString;
import net.mooncloud.moonbook.service.payment.UserPaymentDetailService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
public class UserPaymentDetailServiceImpl implements UserPaymentDetailService
{
	@Autowired
	UserPaymentDetailDao userPaymentDetailDao;
	@Autowired
	UserPaymentDailySubtotalDao userPaymentDailySubtotalDao;

	@Override
	public UserPaymentDetail save(UserPaymentDetail userPaymentDetail)
	{
		return update(userPaymentDetailDao.get(userPaymentDetail.getDetailid()), userPaymentDetail);
	}

	@Override
	public UserPaymentDetail delete(UserPaymentDetail userPaymentDetail)
	{
		return update(null, userPaymentDetail);
	}

	public UserPaymentDetail update(UserPaymentDetail userPaymentDetailOrigin, UserPaymentDetail userPaymentDetail)
	{
		if (userPaymentDetailOrigin == null && userPaymentDetail == null)
		{
			return null;
		}

		double moneyDiff = 0.00;
		int cat = 0, mode = 0;
		if (userPaymentDetailOrigin == null)
		{// insert
			userPaymentDetailDao.insertIgnore(userPaymentDetail);
			moneyDiff = userPaymentDetail.getMoney() - 0;
			cat = 1;
			mode = 1;
		}
		else if (userPaymentDetail == null)
		{// delete
			userPaymentDetailDao.update(userPaymentDetail);
			moneyDiff = 0 - userPaymentDetailOrigin.getMoney();
			cat = -1;
			mode = -1;
		}
		else
		{// update
			userPaymentDetailDao.update(userPaymentDetail);
			moneyDiff = userPaymentDetail.getMoney() - userPaymentDetailOrigin.getMoney();
			cat = userPaymentDetail.getCid() == userPaymentDetailOrigin.getCid() ? 0 : 2;
			mode = userPaymentDetail.getMid() == userPaymentDetailOrigin.getMid() ? 0 : 2;
		}

		if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
		{
			UserPaymentDailySubtotal userPaymentDailySubtotal = new UserPaymentDailySubtotal();
			userPaymentDailySubtotal.setUserid(userPaymentDetail.getUserid());
			userPaymentDailySubtotal.setThedate(userPaymentDetail.getThedate());
			userPaymentDailySubtotal.setYear(userPaymentDetail.getYear());
			userPaymentDailySubtotal.setMonth(userPaymentDetail.getMonth());
			userPaymentDailySubtotal.setDate(userPaymentDetail.getDate());
			userPaymentDailySubtotal.setMoney(moneyDiff);
			userPaymentDailySubtotal.setSyn((short) 0);
			userPaymentDailySubtotal.setCreated(userPaymentDetail.getCreated());
			userPaymentDailySubtotal.setUpdated(new Date());
			userPaymentDailySubtotal.setStatus((short) 0);
			userPaymentDailySubtotalDao.incrementalUpdateMoney(userPaymentDailySubtotal);
		}

		switch (cat)
		{
		case 0:
			break;
		}

		switch (mode)
		{
		case 0:
			break;
		}

		return userPaymentDetail;
	}

	@Override
	public List<UserPaymentDetail> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_payment_detail";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return userPaymentDetailDao.search(queryMap);
	}
}
