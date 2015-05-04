package net.mooncloud.moonbook.service.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDailySubtotal;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
public interface UserPaymentDailySubtotalService
{
	public UserPaymentDailySubtotal save(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public UserPaymentDailySubtotal delete(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public List<UserPaymentDailySubtotal> search(Map<String, Object> querys);
}
