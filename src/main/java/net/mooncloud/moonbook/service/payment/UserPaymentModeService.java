package net.mooncloud.moonbook.service.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentMode;

public interface UserPaymentModeService
{
	public UserPaymentMode save(UserPaymentMode userPaymentMode);

	public UserPaymentMode delete(UserPaymentMode userPaymentMode);

	public List<UserPaymentMode> search(Map<String, Object> querys);
}
