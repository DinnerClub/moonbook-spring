package net.mooncloud.moonbook.service.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookFestival;

public interface BookFestivalService
{
	public BookFestival save(BookFestival bookFestival);

	public BookFestival delete(BookFestival bookFestival);

	public List<BookFestival> search(Map<String, Object> querys);
}
