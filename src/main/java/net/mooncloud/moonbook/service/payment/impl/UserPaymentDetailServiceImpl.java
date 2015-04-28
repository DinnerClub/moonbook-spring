package net.mooncloud.moonbook.service.payment.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;
import net.mooncloud.moonbook.service.chart.impl.SqlFacetQueryString;
import net.mooncloud.moonbook.service.payment.UserPaymentDetailService;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
public class UserPaymentDetailServiceImpl implements UserPaymentDetailService
{

	public UserPaymentDetail save(UserPaymentDetail userPaymentDetail)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public UserPaymentDetail delete(UserPaymentDetail userPaymentDetail)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public UserPaymentDetail update(UserPaymentDetail userPaymentDetail)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserPaymentDetail> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "user_payment_detail";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);
		return null;
	}
}
