package ub.misim.feedgenerator.dao.impl;

import org.springframework.stereotype.Repository;

import ub.misim.feedgenerator.dao.IUserDao;
import ub.misim.feedgenerator.entities.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long>implements IUserDao {

}
