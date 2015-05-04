package net.mooncloud.moonbook.service.book.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookPaymentMode;
import net.mooncloud.moonbook.repository.book.BookPaymentModeDao;
import net.mooncloud.moonbook.service.book.BookPaymentModeService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookPaymentModeService")
public class BookPaymentModeServiceImpl implements BookPaymentModeService
{
	@Autowired
	BookPaymentModeDao bookPaymentModeDao;

	@Override
	public BookPaymentMode save(BookPaymentMode bookPaymentMode)
	{
		bookPaymentModeDao.insertUpdate(bookPaymentMode);
		return bookPaymentMode;
		// return
		// update(bookPaymentModeDao.getCatName(bookPaymentMode.getPid(),
		// bookPaymentMode.getCid()), bookPaymentMode);
	}

	@Override
	public BookPaymentMode delete(BookPaymentMode bookPaymentMode)
	{
		bookPaymentModeDao.delete(bookPaymentMode.getMid(), bookPaymentMode.getSid());
		return bookPaymentMode;
		// return update(bookPaymentMode, null);
	}

	public BookPaymentMode update(BookPaymentMode bookPaymentModeOrigin, BookPaymentMode bookPaymentMode)
	{
		if (bookPaymentModeOrigin == null && bookPaymentMode == null)
		{
			return null;
		}

		if (bookPaymentMode == null)
		{// delete
			bookPaymentMode = bookPaymentModeOrigin;

			bookPaymentModeDao.delete(bookPaymentMode.getMid(), bookPaymentMode.getSid());
		}
		else
		{// insert or update
			bookPaymentModeDao.insertUpdate(bookPaymentMode);
		}

		return bookPaymentMode;
	}

	@Override
	public List<BookPaymentMode> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "book_payment_mode";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return bookPaymentModeDao.search(queryMap);
	}
}
