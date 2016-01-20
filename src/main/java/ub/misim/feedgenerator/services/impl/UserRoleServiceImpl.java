package ub.misim.feedgenerator.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ub.misim.feedgenerator.dao.IUserRoleDao;
import ub.misim.feedgenerator.entities.UserRole;
import ub.misim.feedgenerator.services.IUserRoleService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Autowired
	private IUserRoleDao userRoleDao;

	@Transactional
	public UserRole insert(UserRole userRole) {

		userRole.setName(userRole.getName().toLowerCase());
		UserRole existingUserRole = userRoleDao.findByName(userRole.getName());
		if (existingUserRole != null) {
			return existingUserRole;
		}
		return userRoleDao.insert(userRole);
	}

	@Transactional
	public UserRole insert(String userRoleName) {

		String cleansedRoleName = userRoleName.toLowerCase().trim();

		UserRole existingUserRole = userRoleDao.findByName(cleansedRoleName);
		if (existingUserRole != null) {
			return existingUserRole;
		}
		UserRole userRole = new UserRole();
		userRole.setName(cleansedRoleName);

		return userRoleDao.insert(userRole);
	}

	@Transactional
	public UserRole update(UserRole userRole) {
		return userRoleDao.update(userRole);
	}

	@Transactional
	public void delete(Long id) {
		UserRole userRole = find(id);
		if (userRole != null) {
			userRoleDao.delete(userRole);
		}
	}

	@Transactional
	public UserRole find(Long id) {
		return userRoleDao.find(id);
	}

	@Transactional
	public List<UserRole> getAll() {
		return userRoleDao.getAll();
	}

}
