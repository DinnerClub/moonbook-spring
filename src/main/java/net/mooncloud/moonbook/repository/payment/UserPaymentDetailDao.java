package net.mooncloud.moonbook.repository.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;

public interface UserPaymentDetailDao
{
	public void save(UserPaymentDetail userPaymentDetail);

	public void delete(Long detailid);

	public void update(UserPaymentDetail userPaymentDetail);

	public List<UserPaymentDetail> search(Map<String, Object> querys);
}
