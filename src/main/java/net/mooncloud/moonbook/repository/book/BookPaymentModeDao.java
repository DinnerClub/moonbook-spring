package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentMode;

public interface BookPaymentModeDao
{
	public void insertUpdate(BookPaymentMode mbPaymentMode);

	public void insertIgnore(BookPaymentMode mbPaymentMode);

	public void deleteMid(short mid);

	public void delete(short mid, short sid);

	public void update(BookPaymentMode mbPaymentMode);

	public List<BookPaymentMode> search(Map<String, Object> querys);
}
