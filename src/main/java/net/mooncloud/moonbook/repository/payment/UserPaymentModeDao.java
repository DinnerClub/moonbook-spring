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

	public void deleteMid(long userid, short mid);

	public void delete(long userid, short mid, short sid);

	public void update(UserPaymentMode userPaymentMode);

	public UserPaymentMode getModeName(short mid, short sid);

	public UserPaymentMode get(long userid, short mid, short sid);

	public List<UserPaymentMode> search(Map<String, Object> querys);
}
