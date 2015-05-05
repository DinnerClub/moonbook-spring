package net.mooncloud.moonbook.repository.user;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.user.User;

public interface UserDao
{
	public void insertUpdate(User user);

	public void insertIgnore(User user);

	public void delete(long userid);

	public void update(User user);

	public User getByUsername(User user);

	public List<User> search(Map<String, Object> querys);
}
