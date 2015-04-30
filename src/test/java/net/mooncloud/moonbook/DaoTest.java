package net.mooncloud.moonbook;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookIncomeMode;
import net.mooncloud.moonbook.entity.book.BookPaymentMode;
import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
import net.mooncloud.moonbook.repository.book.BookIncomeModeDao;
import net.mooncloud.moonbook.repository.book.BookPaymentCategoryDao;
import net.mooncloud.moonbook.repository.book.BookPaymentModeDao;
import net.mooncloud.moonbook.repository.chart.ChartDataQueryDao;
import net.mooncloud.moonbook.repository.payment.UserPaymentDetailDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:applicationContext.xml")
public class DaoTest
{
	@Autowired
	UserPaymentDetailDao userPaymentDetailDao;
	@Autowired
	ChartDataQueryDao chartDataQueryDao;
	@Autowired
	BookPaymentCategoryDao mbPaymentCategoryDao;
	@Autowired
	BookPaymentModeDao mbPaymentModeDao;
	@Autowired
	BookIncomeModeDao mbIncomeModeDao;

//	@Test
	public void mbIncomeModeDao()
	{
		BookIncomeMode mbPaymentMode = new BookIncomeMode();
		mbPaymentMode.setMid((short) 1);
		mbPaymentMode.setMname("工作收入");
		mbPaymentMode.setSid((short) 1001);
		mbPaymentMode.setSname("工资");
		mbPaymentMode.setUpdated(new Date());
		System.out.println(mbPaymentMode);
		mbIncomeModeDao.insertUpdate(mbPaymentMode);
//		mbIncomeModeDao.insertIgnore(mbPaymentMode);

		// mbPaymentModeDao.update(mbPaymentMode);
	}

	// @Test
	public void mbPaymentCategoryDao()
	{
		BookPaymentMode mbPaymentMode = new BookPaymentMode();
		mbPaymentMode.setMid((short) 1);
		mbPaymentMode.setMname("现金");
		mbPaymentMode.setSid((short) 1001);
		mbPaymentMode.setSname("金线");
		mbPaymentMode.setUpdated(new Date());
		System.out.println(mbPaymentMode);
		mbPaymentModeDao.insertUpdate(mbPaymentMode);

		// mbPaymentModeDao.update(mbPaymentMode);
	}

	// @Test
	public void chartDataQueryDao()
	{
		// String query = SqlFacetQueryString.facetQueryString(fields,
		// aggregates, index, table, querys, orderby, limit, offset, true);
		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query",
				" month, day, SUM(money) AS SUM, COUNT(money) AS COUNT FROM user_payment_detail GROUP BY month, day ORDER BY SUM DESC, COUNT ASC ");
		System.out.println(chartDataQueryDao.userPaymentFacetQuery(queryMap));
	}

	 @Test
	public void userPaymentDetailDao()
	{
//		UserPaymentDetail userPaymentDetail = new UserPaymentDetail();
//		userPaymentDetail.setDetailid(12345);
//		userPaymentDetail.setMoney(3.45);
//		System.out.println(userPaymentDetail);
//		userPaymentDetailDao.insertIgnore(userPaymentDetail);
//		userPaymentDetail.setMoney(5.32);
//		userPaymentDetailDao.update(userPaymentDetail);
//		userPaymentDetailDao.delete(12345L);
		
		System.out.println(userPaymentDetailDao.get(1L));
	}
}
