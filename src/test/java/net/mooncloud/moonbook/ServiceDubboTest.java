package net.mooncloud.moonbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookFestival;
import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
import net.mooncloud.moonbook.entity.user.User;
import net.mooncloud.moonbook.service.book.BookFestivalService;
import net.mooncloud.moonbook.service.chart.ChartDataQueryService;
import net.mooncloud.moonbook.service.income.UserIncomeModeService;
import net.mooncloud.moonbook.service.payment.UserPaymentCategoryService;
import net.mooncloud.moonbook.service.payment.UserPaymentDetailService;
import net.mooncloud.moonbook.service.payment.UserPaymentModeService;
import net.mooncloud.moonbook.service.user.UserService;
import net.mooncloud.moonbook.utils.MD5Hash;
import net.mooncloud.moonbook.utils.SomeStaticUtils;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

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
	UserPaymentModeService userPaymentModeService;
	@Autowired
	UserIncomeModeService userIncomeModeService;
	@Autowired
	UserPaymentCategoryService userPaymentCategoryService;
	@Autowired
	UserService userService;
	@Autowired
	BookFestivalService bookFestivalService;
	@Autowired
	ChartDataQueryService chartDataQueryService;

	@Test
	public void chartDataQueryService()
	{
		List<String> fields = new ArrayList<String>();
		List<String> aggregates = new ArrayList<String>();
		String index = null;
		String table = null;
		Map<String, Object> querys = new HashMap<String, Object>();
		List<String> orderby = new ArrayList<String>();
		String limit = null;
		String offset = null;
		boolean facet = true;

		// querys.put("year", 2015);
		// querys.put("comment", "2222");
//		fields.add("year");
//		fields.add("month");
//		fields.add("date");
//		fields.add("day");
//		fields.add("hour");
		// fields.add("*");
		aggregates.add(SqlFacetQueryString.SUM);
		aggregates.add(SqlFacetQueryString.COUNT);
		aggregates.add(SqlFacetQueryString.AVG);
		orderby.add("SUM DESC");
		orderby.add("COUNT ASC");
		index = "money";
		table = "user_payment_detail";

		for (Object o : chartDataQueryService.userPaymentFacetQuery(fields, aggregates, index, table, querys, orderby, limit, offset))
			System.out.println(o);
	}

	// @Test
	public void bookFestivalService()
	{
		BookFestival bookFestival = new BookFestival();

		int y = 2015;
		int m = 12;
		int d = 25;
		bookFestival.setFestival("圣诞节");
		bookFestival.setLunar((byte) 0);

		Date date = new Date(y - 1900, m - 1, d);
		Date now = new Date();
		bookFestival.setThedate(Integer.parseInt(SomeStaticUtils.FORMAT3.format(date)));
		bookFestival.setYear((short) (date.getYear() + 1900));
		bookFestival.setMonth((byte) (date.getMonth() + 1));
		bookFestival.setDate((byte) date.getDate());
		bookFestival.setDay((byte) date.getDay());

		bookFestival.setCreated(now);
		bookFestival.setUpdated(now);

		bookFestivalService.save(bookFestival);
	}

	// @Test
	public void userService()
	{
		User user = new User();
		user.setUserid(13456990586L);
		user.setUsername("134569905860");
		user.setUsernick("J.D.YANG");
		user.setPassword(MD5Hash.digest("1234567abcdefg").toString());
		user.setSalt(user.getPassword());
		user.setEmail("yangjd@126.com");
		user.setMobile("13456990586");
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		// System.out.println(userService.changePassword(userService.encrypt(user),
		// MD5Hash.digest("abcdefg1234567")).getStatus());
		// System.out.println(userService.changeUsernick(userService.encrypt(user)));
		// System.out.println(userService.signUp(userService.encrypt(user)).getStatus());
		System.out.println(userService.signIn(userService.encrypt(user)).getStatus());

		// System.out.println(userService.encrypt(user));
		// System.out.println(userService.decrypt(user));
	}

	// @Test
	public void search()
	{
		Map<String, Object> querys = new HashMap<String, Object>();
		querys.put("userid", "134569960586");
		for (Object o : userIncomeModeService.search(querys))
			System.out.println(o);
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
