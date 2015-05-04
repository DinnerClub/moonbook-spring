package net.mooncloud.moonbook.service.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentMode;

public interface BookPaymentModeService
{
	public BookPaymentMode save(BookPaymentMode bookPaymentMode);

	public BookPaymentMode delete(BookPaymentMode bookPaymentMode);

	public List<BookPaymentMode> search(Map<String, Object> querys);
}
