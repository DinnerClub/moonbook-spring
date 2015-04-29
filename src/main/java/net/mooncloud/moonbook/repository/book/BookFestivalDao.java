package net.mooncloud.moonbook.repository.book;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.book.BookFestival;

public interface BookFestivalDao
{
	public void insertUpdate(BookFestival mbFestival);

	public void insertIgnore(BookFestival mbFestival);

	public void deleteDate(int thedate);

	public void delete(int thedate, byte lunar);

	public void update(BookFestival mbFestival);

	public List<BookFestival> search(Map<String, Object> querys);
}
