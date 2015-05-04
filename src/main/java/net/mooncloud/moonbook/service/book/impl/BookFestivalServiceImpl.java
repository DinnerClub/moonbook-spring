package net.mooncloud.moonbook.service.book.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookFestival;
import net.mooncloud.moonbook.repository.book.BookFestivalDao;
import net.mooncloud.moonbook.service.book.BookFestivalService;
import net.mooncloud.moonbook.utils.SqlFacetQueryString;

import org.springframework.beans.factory.annotation.Autowired;

public class BookFestivalServiceImpl implements BookFestivalService
{
	@Autowired
	BookFestivalDao bookFestivalDao;

	@Override
	public BookFestival save(BookFestival bookFestival)
	{
		bookFestivalDao.insertUpdate(bookFestival);
		return bookFestival;
		// return
		// update(bookFestivalDao.getCatName(bookFestival.getPid(),
		// bookFestival.getCid()), bookFestival);
	}

	@Override
	public BookFestival delete(BookFestival bookFestival)
	{
		bookFestivalDao.delete(bookFestival.getThedate(), bookFestival.getLunar());
		return bookFestival;
		// return update(bookFestival, null);
	}

	public BookFestival update(BookFestival bookFestivalOrigin, BookFestival bookFestival)
	{
		if (bookFestivalOrigin == null && bookFestival == null)
		{
			return null;
		}

		if (bookFestival == null)
		{// delete
			bookFestival = bookFestivalOrigin;

			bookFestivalDao.delete(bookFestival.getThedate(), bookFestival.getLunar());
		}
		else
		{// insert or update
			bookFestivalDao.insertUpdate(bookFestival);
		}

		return bookFestival;
	}

	@Override
	public List<BookFestival> search(Map<String, Object> querys)
	{
		List<String> fields = new ArrayList<String>();
		String table = null;

		fields.add("*");
		table = "book_festival";
		String facetQueryString = SqlFacetQueryString.facetQueryString(fields, null, null, table, querys, null, null, null, true);

		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", facetQueryString);
		return bookFestivalDao.search(queryMap);
	}
}
