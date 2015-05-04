package net.mooncloud.moonbook.service.income;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeMode;

public interface UserIncomeModeService
{
	public UserIncomeMode save(UserIncomeMode userIncomeMode);

	public UserIncomeMode delete(UserIncomeMode userIncomeMode);

	public List<UserIncomeMode> search(Map<String, Object> querys);
}
