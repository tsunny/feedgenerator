package ub.misim.feedgenerator.dao.impl;

import org.springframework.stereotype.Repository;

import ub.misim.feedgenerator.dao.IUserRoleDao;
import ub.misim.feedgenerator.entities.UserRole;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole, Long>implements IUserRoleDao {

}
