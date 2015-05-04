package net.mooncloud.moonbook.service.income;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeDailySubtotal;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
public interface UserIncomeDailySubtotalService
{
	public UserIncomeDailySubtotal save(UserIncomeDailySubtotal userIncomeDailySubtotal);

	public UserIncomeDailySubtotal delete(UserIncomeDailySubtotal userIncomeDailySubtotal);

	public List<UserIncomeDailySubtotal> search(Map<String, Object> querys);
}
