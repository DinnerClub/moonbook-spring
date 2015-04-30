package net.mooncloud.moonbook.service.payment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentCategory;
import net.mooncloud.moonbook.entity.payment.UserPaymentDailySubtotal;
import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
import net.mooncloud.moonbook.entity.payment.UserPaymentMode;
import net.mooncloud.moonbook.repository.payment.UserPaymentCategoryDao;
import net.mooncloud.moonbook.repository.payment.UserPaymentDailySubtotalDao;
import net.mooncloud.moonbook.repository.payment.UserPaymentDetailDao;
import net.mooncloud.moonbook.repository.payment.UserPaymentModeDao;
import net.mooncloud.moonbook.service.chart.impl.SqlFacetQueryString;
import net.mooncloud.moonbook.service.payment.UserPaymentDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
@Service("userPaymentDetailService")
public class UserPaymentDetailServiceImpl implements UserPaymentDetailService
{
	@Autowired
	UserPaymentDetailDao userPaymentDetailDao;
	@Autowired
	UserPaymentDailySubtotalDao userPaymentDailySubtotalDao;
	@Autowired
	UserPaymentCategoryDao userPaymentCategoryDao;
	@Autowired
	UserPaymentModeDao userPaymentModeDao;

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

		if (userPaymentDetailOrigin == null)
		{// insert
			userPaymentDetailDao.insertIgnore(userPaymentDetail);

			// daily subtotal
			moneyDiff = userPaymentDetail.getMoney() - 0;
			if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
			{
				updateDailySubtotal(userPaymentDetail, userPaymentDetail.getMoney());
			}

			// user category
			updateUserCategoryCount(userPaymentDetail, 1);

			// user mode
			updateUserModeCount(userPaymentDetail, 1);

		}
		else if (userPaymentDetail == null)
		{// delete
			userPaymentDetailOrigin.setStatus((short) 1);
			userPaymentDetailDao.update(userPaymentDetailOrigin);

			// daily subtotal
			moneyDiff = 0 - userPaymentDetailOrigin.getMoney();
			if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
			{
				updateDailySubtotal(userPaymentDetailOrigin, moneyDiff);
			}

			// user category
			updateUserCategoryCount(userPaymentDetailOrigin, -1);
			// user mode
			updateUserModeCount(userPaymentDetailOrigin, -1);
		}
		else
		{// update
			userPaymentDetailDao.update(userPaymentDetail);

			// daily subtotal
			moneyDiff = userPaymentDetail.getMoney() - userPaymentDetailOrigin.getMoney();
			if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
			{
				updateDailySubtotal(userPaymentDetail, userPaymentDetail.getMoney());
				updateDailySubtotal(userPaymentDetailOrigin, 0 - userPaymentDetailOrigin.getMoney());
			}

			// user category
			if (userPaymentDetail.getCid() != userPaymentDetailOrigin.getCid())
			{
				updateUserCategoryCount(userPaymentDetail, 1);
				updateUserCategoryCount(userPaymentDetailOrigin, -1);
			}

			// user mode
			if (userPaymentDetail.getSid() != userPaymentDetailOrigin.getSid())
			{
				updateUserModeCount(userPaymentDetail, 1);
				updateUserModeCount(userPaymentDetailOrigin, -1);
			}
		}

		return userPaymentDetail;
	}

	private void updateDailySubtotal(UserPaymentDetail userPaymentDetail, double moneyDiff)
	{
		if ((int) (moneyDiff * 100) != 0) // update DailySubtotal
		{
			UserPaymentDailySubtotal userPaymentDailySubtotal = new UserPaymentDailySubtotal();
			userPaymentDailySubtotal.setUserid(userPaymentDetail.getUserid());
			userPaymentDailySubtotal.setThedate(userPaymentDetail.getThedate());
			userPaymentDailySubtotal.setYear(userPaymentDetail.getYear());
			userPaymentDailySubtotal.setMonth(userPaymentDetail.getMonth());
			userPaymentDailySubtotal.setDate(userPaymentDetail.getDate());
			userPaymentDailySubtotal.setMoney(moneyDiff);
			userPaymentDailySubtotal.setSyn(userPaymentDetail.getSyn());
			userPaymentDailySubtotal.setCreated(userPaymentDetail.getCreated());
			userPaymentDailySubtotal.setUpdated(userPaymentDetail.getUpdated());
			// userPaymentDailySubtotal.setStatus(userPaymentDetail.getStatus());
			userPaymentDailySubtotalDao.incrementalUpdateMoney(userPaymentDailySubtotal);
		}
	}

	private void updateUserCategoryCount(UserPaymentDetail userPaymentDetail, long count)
	{
		UserPaymentCategory userPaymentCategory = userPaymentCategoryDao.getCatName(userPaymentDetail.getPid(), userPaymentDetail.getCid());
		userPaymentCategory.setUserid(userPaymentDetail.getUserid());
		userPaymentCategory.setCount(count);
		userPaymentCategory.setSyn(userPaymentDetail.getSyn());
		userPaymentCategory.setCreated(userPaymentDetail.getCreated());
		userPaymentCategory.setUpdated(userPaymentDetail.getUpdated());
		// userPaymentCategory.setStatus(userPaymentDetail.getStatus());
		userPaymentCategoryDao.incrementalUpdateCount(userPaymentCategory);
	}

	private void updateUserModeCount(UserPaymentDetail userPaymentDetail, long count)
	{
		UserPaymentMode userPaymentMode = userPaymentModeDao.getModeName(userPaymentDetail.getMid(), userPaymentDetail.getSid());
		userPaymentMode.setUserid(userPaymentDetail.getUserid());
		userPaymentMode.setCount(count);
		userPaymentMode.setSyn(userPaymentDetail.getSyn());
		userPaymentMode.setCreated(userPaymentDetail.getCreated());
		userPaymentMode.setUpdated(userPaymentDetail.getUpdated());
		// userPaymentMode.setStatus(userPaymentDetail.getStatus());
		userPaymentModeDao.incrementalUpdateCount(userPaymentMode);
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
