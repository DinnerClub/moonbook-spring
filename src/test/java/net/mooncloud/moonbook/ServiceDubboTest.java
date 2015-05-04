package net.mooncloud.moonbook;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
import net.mooncloud.moonbook.service.payment.UserPaymentDetailService;
import net.mooncloud.moonbook.service.payment.UserPaymentModeService;
import net.mooncloud.moonbook.utils.SomeStaticUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:applicationContext.xml")
public class ServiceDubboTest
{
	// @Resource
	@Autowired
	UserPaymentDetailService userPaymentDetailService;
	@Autowired
	UserPaymentModeService bookFestivalService;

	@Test
	public void search()
	{
		Map<String, Object> querys = new HashMap<String, Object>();
		System.out.println(bookFestivalService.search(querys));
	}

	// @Test
	public void userPaymentDetailService()
	{
		UserPaymentDetail userPaymentDetail = new UserPaymentDetail();
		userPaymentDetail.setDetailid(System.currentTimeMillis());
		userPaymentDetail.setUserid(134569960586L);

		Date date = new Date();
		userPaymentDetail.setTime(date);
		userPaymentDetail.setThedate(Integer.parseInt(SomeStaticUtils.FORMAT3.format(date)));
		userPaymentDetail.setYear((short) (date.getYear() + 1900));
		userPaymentDetail.setMonth((byte) (date.getMonth() + 1));
		userPaymentDetail.setDate((byte) date.getDate());
		userPaymentDetail.setDay((byte) date.getDay());
		userPaymentDetail.setHour((byte) date.getHours());
		userPaymentDetail.setMinute((byte) date.getMinutes());
		userPaymentDetail.setSecond((byte) date.getSeconds());

		userPaymentDetail.setLatitude(120.5);
		userPaymentDetail.setLongitude(30.4);
		userPaymentDetail.setAddress("上海市闵行区紫秀路100号虹桥总部一号5幢");

		userPaymentDetail.setPid((short) 2);
		userPaymentDetail.setCid((short) 2002);
		userPaymentDetail.setMid((short) 1);
		userPaymentDetail.setSid((short) 1001);

		userPaymentDetail.setMoney(45.5);

		userPaymentDetail.setComment("");

		userPaymentDetail.setCurlat(122.12);
		userPaymentDetail.setCurlon(31.53);

		userPaymentDetail.setSyn((short) 0);
		userPaymentDetail.setCreated(new Date());
		userPaymentDetail.setUpdated(userPaymentDetail.getCreated());
		userPaymentDetail.setStatus((short) 0);

		System.out.println(userPaymentDetail);

		userPaymentDetailService.save(userPaymentDetail);

		System.out.println(userPaymentDetailService.search(null));
	}

}
