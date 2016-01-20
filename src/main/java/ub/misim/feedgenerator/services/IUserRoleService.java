package ub.misim.feedgenerator.services;

import java.util.List;

import ub.misim.feedgenerator.entities.UserRole;

public interface IUserRoleService {

	UserRole insert(UserRole user);

	UserRole update(UserRole user);

	void delete(Long id);

	UserRole find(Long id);

	List<UserRole> getAll();

	UserRole insert(String userRoleName);

}
