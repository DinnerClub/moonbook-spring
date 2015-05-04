package net.mooncloud.moonbook.service.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookIncomeMode;

public interface BookIncomeModeService
{
	public BookIncomeMode save(BookIncomeMode bookIncomeMode);

	public BookIncomeMode delete(BookIncomeMode bookIncomeMode);

	public List<BookIncomeMode> search(Map<String, Object> querys);
}
