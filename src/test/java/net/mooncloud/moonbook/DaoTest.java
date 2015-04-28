package net.mooncloud.moonbook;

import java.util.HashMap;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
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

	@Test
	public void chartDataQueryDao()
	{
		// String query = SqlFacetQueryString.facetQueryString(fields,
		// aggregates, index, table, querys, orderby, limit, offset, true);
		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", " month, day, SUM(money) AS SUM, COUNT(money) AS COUNT FROM user_payment_detail GROUP BY month, day ORDER BY SUM DESC, COUNT ASC ");
		System.out.println(chartDataQueryDao.userPaymentFacetQuery(queryMap));
	}

	// @Test
	public void userPaymentDetailDao()
	{
		UserPaymentDetail userPaymentDetail = new UserPaymentDetail();
		userPaymentDetail.setDetailid(12345);
		userPaymentDetail.setMoney(3.45);
		System.out.println(userPaymentDetail);
		userPaymentDetailDao.save(userPaymentDetail);
		userPaymentDetail.setMoney(5.32);
		userPaymentDetailDao.update(userPaymentDetail);
		userPaymentDetailDao.delete(12345L);
	}
}
