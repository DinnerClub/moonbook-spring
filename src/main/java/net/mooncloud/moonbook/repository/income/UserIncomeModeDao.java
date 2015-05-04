package net.mooncloud.moonbook.repository.income;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeMode;

public interface UserIncomeModeDao
{
	public void insertUpdate(UserIncomeMode userIncomeMode);

	public void insertIgnore(UserIncomeMode userIncomeMode);

	public void incrementalUpdateCount(UserIncomeMode userIncomeMode);

	public void deleteMid(long userid, int mid);

	public void delete(long userid, int mid, int sid);

	public void update(UserIncomeMode userIncomeMode);

	public UserIncomeMode getModeName(int mid, int sid);

	public UserIncomeMode get(long userid, int mid, int sid);

	public List<UserIncomeMode> search(Map<String, Object> querys);
}
