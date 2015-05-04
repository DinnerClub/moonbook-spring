package net.mooncloud.moonbook.service.income;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeDetail;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
public interface UserIncomeDetailService
{
	public UserIncomeDetail save(UserIncomeDetail userIncomeDetail);

	public UserIncomeDetail delete(UserIncomeDetail userIncomeDetail);

	public List<UserIncomeDetail> search(Map<String, Object> querys);
}
