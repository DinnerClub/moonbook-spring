package net.mooncloud.moonbook;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
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
	UserPaymentDetailDao UserPaymentDetailDao;

	@Test
	public void UsePaymentDetailDao()
	{
		UserPaymentDetail UsePaymentDetail = new UserPaymentDetail();
		UsePaymentDetail.setDetailid(12345);
		UsePaymentDetail.setMoney(3.45);
		System.out.println(UsePaymentDetail);
		System.out.println("22222222");
		UserPaymentDetailDao.save(UsePaymentDetail);
	}
}
