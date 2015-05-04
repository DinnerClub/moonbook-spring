package net.mooncloud.moonbook.service.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentCategory;

public interface UserPaymentCategoryService
{
	public UserPaymentCategory save(UserPaymentCategory userPaymentCategory);

	public UserPaymentCategory delete(UserPaymentCategory userPaymentCategory);

	public List<UserPaymentCategory> search(Map<String, Object> querys);
}
