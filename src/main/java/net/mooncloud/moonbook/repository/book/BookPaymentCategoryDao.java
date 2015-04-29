package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentCategory;

public interface BookPaymentCategoryDao
{
	public void insertUpdate(BookPaymentCategory mbPaymentCategory);

	public void insertIgnore(BookPaymentCategory mbPaymentCategory);

	public void deletePid(short pid);

	public void delete(short pid, short cid);

	public void update(BookPaymentCategory mbPaymentCategory);

	public List<BookPaymentCategory> search(Map<String, Object> querys);
}
