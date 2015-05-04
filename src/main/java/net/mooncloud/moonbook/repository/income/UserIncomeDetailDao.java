package net.mooncloud.moonbook.repository.income;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.income.UserIncomeDetail;

public interface UserIncomeDetailDao
{
	public void insertUpdate(UserIncomeDetail userIncomeDetail);

	public void insertIgnore(UserIncomeDetail userIncomeDetail);

	public void delete(Long detailid);

	public void update(UserIncomeDetail userIncomeDetail);

	public UserIncomeDetail get(Long detailid);

	public List<UserIncomeDetail> search(Map<String, Object> querys);
}
