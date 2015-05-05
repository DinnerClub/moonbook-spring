package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookFestival;

public interface BookFestivalDao
{
	public void insertUpdate(BookFestival bookFestival);

	public void insertIgnore(BookFestival bookFestival);

	public void deleteDate(int thedate);

	public void delete(int thedate, byte lunar);

	public void update(BookFestival bookFestival);

	public List<BookFestival> search(Map<String, Object> querys);
}
