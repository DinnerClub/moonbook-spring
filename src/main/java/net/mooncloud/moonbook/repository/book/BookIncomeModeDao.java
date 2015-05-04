package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookIncomeMode;

public interface BookIncomeModeDao
{
	public void insertUpdate(BookIncomeMode mbIncomeMode);

	public void insertIgnore(BookIncomeMode mbIncomeMode);

	public void deleteMid(int mid);

	public void delete(int mid, int sid);

	public void update(BookIncomeMode mbIncomeMode);

	public List<BookIncomeMode> search(Map<String, Object> querys);
}
