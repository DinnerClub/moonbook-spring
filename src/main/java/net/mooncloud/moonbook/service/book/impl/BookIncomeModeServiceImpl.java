package net.mooncloud.moonbook.service.book.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookIncomeMode;
import net.mooncloud.moonbook.repository.book.BookIncomeModeDao;
import net.mooncloud.moonbook.service.book.BookIncomeModeService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;

public class BookIncomeModeServiceImpl implements BookIncomeModeService
{
	@Autowired
	BookIncomeModeDao bookIncomeModeDao;

	@Override
	public BookIncomeMode save(BookIncomeMode bookIncomeMode)
	{
		bookIncomeModeDao.insertUpdate(bookIncomeMode);
		return bookIncomeMode;
		// return
		// update(bookIncomeModeDao.getCatName(bookIncomeMode.getPid(),
		// bookIncomeMode.getCid()), bookIncomeMode);
	}

	@Override
	public BookIncomeMode delete(BookIncomeMode bookIncomeMode)
	{
		bookIncomeModeDao.delete(bookIncomeMode.getMid(), bookIncomeMode.getSid());
		return bookIncomeMode;
		// return update(bookIncomeMode, null);
	}

	public BookIncomeMode update(BookIncomeMode bookIncomeModeOrigin, BookIncomeMode bookIncomeMode)
	{
		if (bookIncomeModeOrigin == null && bookIncomeMode == null)
		{
			return null;
		}

		if (bookIncomeMode == null)
		{// delete
			bookIncomeMode = bookIncomeModeOrigin;

			bookIncomeModeDao.delete(bookIncomeMode.getMid(), bookIncomeMode.getSid());
		}
		else
		{// insert or update
			bookIncomeModeDao.insertUpdate(bookIncomeMode);
		}

		return bookIncomeMode;
	}

	@Override
	public List<BookIncomeMode> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "book_income_mode";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return bookIncomeModeDao.search(queryMap);
	}
}
