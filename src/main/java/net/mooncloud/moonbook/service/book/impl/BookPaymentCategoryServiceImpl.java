package net.mooncloud.moonbook.service.book.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentCategory;
import net.mooncloud.moonbook.repository.book.BookPaymentCategoryDao;
import net.mooncloud.moonbook.service.book.BookPaymentCategoryService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;

public class BookPaymentCategoryServiceImpl implements BookPaymentCategoryService
{
	@Autowired
	BookPaymentCategoryDao bookPaymentCategoryDao;

	@Override
	public BookPaymentCategory save(BookPaymentCategory bookPaymentCategory)
	{
		bookPaymentCategoryDao.insertUpdate(bookPaymentCategory);
		return bookPaymentCategory;
		// return
		// update(bookPaymentCategoryDao.getCatName(bookPaymentCategory.getPid(),
		// bookPaymentCategory.getCid()), bookPaymentCategory);
	}

	@Override
	public BookPaymentCategory delete(BookPaymentCategory bookPaymentCategory)
	{
		bookPaymentCategoryDao.delete(bookPaymentCategory.getPid(), bookPaymentCategory.getCid());
		return bookPaymentCategory;
		// return update(bookPaymentCategory, null);
	}

	public BookPaymentCategory update(BookPaymentCategory bookPaymentCategoryOrigin, BookPaymentCategory bookPaymentCategory)
	{
		if (bookPaymentCategoryOrigin == null && bookPaymentCategory == null)
		{
			return null;
		}

		if (bookPaymentCategory == null)
		{// delete
			bookPaymentCategory = bookPaymentCategoryOrigin;

			bookPaymentCategoryDao.delete(bookPaymentCategory.getPid(), bookPaymentCategory.getCid());
		}
		else
		{// insert or update
			bookPaymentCategoryDao.insertUpdate(bookPaymentCategory);
		}

		return bookPaymentCategory;
	}

	@Override
	public List<BookPaymentCategory> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "book_payment_category";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return bookPaymentCategoryDao.search(queryMap);
	}
}
