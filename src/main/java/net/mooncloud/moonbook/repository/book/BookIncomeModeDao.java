package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookIncomeMode;

public interface BookIncomeModeDao
{
	public void insertUpdate(BookIncomeMode mbIncomeMode);

	public void insertIgnore(BookIncomeMode mbIncomeMode);

	public void deleteMid(short mid);

	public void delete(short mid, short sid);

	public void update(BookIncomeMode mbIncomeMode);

	public List<BookIncomeMode> search(Map<String, Object> querys);
}
