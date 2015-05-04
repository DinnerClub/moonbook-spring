package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentCategory;

public interface BookPaymentCategoryDao
{
	public void insertUpdate(BookPaymentCategory bookPaymentCategory);

	public void insertIgnore(BookPaymentCategory bookPaymentCategory);

	public void deletePid(int pid);

	public void delete(int pid, int cid);

	public void update(BookPaymentCategory bookPaymentCategory);

	public BookPaymentCategory getCatName(int pid, int cid);

	public List<BookPaymentCategory> search(Map<String, Object> querys);
}
