package ub.misim.feedgenerator.services;

import java.util.List;

import ub.misim.feedgenerator.entities.User;

public interface IUserService {

	User insert(User user);

	User update(User user);

	void delete(Long id);

	User find(Long id);

	List<User> getAll();

}
