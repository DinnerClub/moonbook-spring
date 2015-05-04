package net.mooncloud.moonbook.repository.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentCategory;
import net.mooncloud.moonbook.entity.payment.UserPaymentMode;

public interface UserPaymentModeDao
{
	public void insertUpdate(UserPaymentMode userPaymentMode);

	public void insertIgnore(UserPaymentMode userPaymentMode);

	public void incrementalUpdateCount(UserPaymentMode userPaymentMode);

	public void deleteMid(long userid, int mid);

	public void delete(long userid, int mid, int sid);

	public void update(UserPaymentMode userPaymentMode);

	public UserPaymentMode getModeName(int mid, int sid);

	public UserPaymentMode get(long userid, int mid, int sid);

	public List<UserPaymentMode> search(Map<String, Object> querys);
}
