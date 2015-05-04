package net.mooncloud.moonbook.repository.income;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeDailySubtotal;

public interface UserIncomeDailySubtotalDao
{
	public void insertUpdate(UserIncomeDailySubtotal userIncomeDailySubtotal);

	public void insertIgnore(UserIncomeDailySubtotal userIncomeDailySubtotal);

	public void incrementalUpdateMoney(UserIncomeDailySubtotal userIncomeDailySubtotal);

	public void delete(long userid, int thedate);

	public void update(UserIncomeDailySubtotal userIncomeDailySubtotal);

	public List<UserIncomeDailySubtotal> search(Map<String, Object> querys);
}
