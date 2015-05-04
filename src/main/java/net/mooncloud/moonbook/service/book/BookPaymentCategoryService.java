package net.mooncloud.moonbook.service.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentCategory;

public interface BookPaymentCategoryService
{
	public BookPaymentCategory save(BookPaymentCategory bookPaymentCategory);

	public BookPaymentCategory delete(BookPaymentCategory bookPaymentCategory);

	public List<BookPaymentCategory> search(Map<String, Object> querys);
}
