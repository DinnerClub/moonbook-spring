package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentMode;

public interface BookPaymentModeDao
{
	public void insertUpdate(BookPaymentMode bookPaymentMode);

	public void insertIgnore(BookPaymentMode bookPaymentMode);

	public void deleteMid(int mid);

	public void delete(int mid, int sid);

	public void update(BookPaymentMode bookPaymentMode);

	public List<BookPaymentMode> search(Map<String, Object> querys);
}
