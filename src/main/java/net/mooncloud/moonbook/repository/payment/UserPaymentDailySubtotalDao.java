package net.mooncloud.moonbook.repository.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDailySubtotal;

public interface UserPaymentDailySubtotalDao
{
	public void save(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public void incrementalSave(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public void delete(Long userid, Long thedate);

	public void update(UserPaymentDailySubtotal userPaymentDailySubtotal);

	public List<UserPaymentDailySubtotal> search(Map<String, Object> querys);
}
