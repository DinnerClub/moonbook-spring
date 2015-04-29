package net.mooncloud.moonbook.repository.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDailySubtotal;

public interface UserPaymentDailySubtotalDao
{
	public void insertUpdate(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public void insertIgnore(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public void incrementalUpdateMoney(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public void delete(Long userid, Long thedate);

	public void update(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public List<UserPaymentDailySubtotal> search(Map<String, Object> querys);
}
