package net.mooncloud.moonbook.repository.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;

public interface UserPaymentDetailDao
{
	public void insertUpdate(UserPaymentDetail userPaymentDetail);

	public void insertIgnore(UserPaymentDetail userPaymentDetail);

	public void delete(Long detailid);

	public void update(UserPaymentDetail userPaymentDetail);

	public UserPaymentDetail get(Long detailid);

	public List<UserPaymentDetail> search(Map<String, Object> querys);
}
