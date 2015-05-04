package net.mooncloud.moonbook.repository.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentCategory;
import net.mooncloud.moonbook.entity.payment.UserPaymentDailySubtotal;

public interface UserPaymentCategoryDao
{
	public void insertUpdate(UserPaymentCategory userPaymentCategory);

	public void insertIgnore(UserPaymentCategory userPaymentCategory);

	public void incrementalUpdateCount(UserPaymentCategory userPaymentCategory);

	public void deletePid(long userid, int pid);

	public void delete(long userid, int pid, int cid);

	public void update(UserPaymentCategory userPaymentCategory);

	public UserPaymentCategory getCatName(long userid, int pid, int cid);

	public UserPaymentCategory get(long userid, int pid, int cid);

	public List<UserPaymentCategory> getAllPid(long userid);

	public List<UserPaymentCategory> search(Map<String, Object> querys);
}
