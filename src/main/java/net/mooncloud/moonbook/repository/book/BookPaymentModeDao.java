package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentMode;

public interface BookPaymentModeDao
{
	public void insertUpdate(BookPaymentMode mbPaymentMode);

	public void insertIgnore(BookPaymentMode mbPaymentMode);

	public void deleteMid(int mid);

	public void delete(int mid, int sid);

	public void update(BookPaymentMode mbPaymentMode);

	public List<BookPaymentMode> search(Map<String, Object> querys);
}
