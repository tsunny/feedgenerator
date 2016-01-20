package ub.misim.feedgenerator.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ub.misim.feedgenerator.dao.IUserDao;
import ub.misim.feedgenerator.dao.IUserInterestMapDao;
import ub.misim.feedgenerator.entities.Topic;
import ub.misim.feedgenerator.entities.User;
import ub.misim.feedgenerator.entities.UserInterestMap;
import ub.misim.feedgenerator.entities.UserRole;
import ub.misim.feedgenerator.services.ITopicService;
import ub.misim.feedgenerator.services.IUserRoleService;
import ub.misim.feedgenerator.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	private ITopicService topicService;

	@Autowired
	private IUserInterestMapDao userInterestMapDao;

	@Transactional
	public User insert(User user) {

		user = userDao.insert(user);

		List<UserRole> userRoles = new ArrayList<UserRole>();
		Set<String> userRoleNames = user.getRoles();
		if (userRoleNames != null) {
			for (String roleName : userRoleNames) {
				UserRole userRole = userRoleService.insert(roleName);
				userRoles.add(userRole);
			}
			user.setUserRoles(userRoles);
			userDao.update(user);
		}

		addInterestsAndWeights(user);

		return user;
	}

	@Transactional
	public User update(User user) {

		User existingUser = userDao.find(user.getId());

		if (existingUser == null) {
			return null;
		}

		existingUser.setUsername(user.getUsername());
		existingUser.setRoles(user.getRoles());
		existingUser.setInterestWeightMap(user.getInterestWeightMap());

		updateUserRoles(existingUser);
		updateInterestsAndWeights(existingUser);

		userDao.update(existingUser);

		populateViewProperties(existingUser);

		return existingUser;
	}

	/**
	 * if the topics are not empty then this method will clear the existing
	 * weights for the topics and set the weights the new topics
	 * 
	 * @param newUser
	 */
	private void updateInterestsAndWeights(User existingUser) {

		Map<String, Float> interestWeightMap = existingUser.getInterestWeightMap();
		if (!interestWeightMap.isEmpty()) {

			List<UserInterestMap> interests = existingUser.getInterests();
			for (Iterator iterator = interests.iterator(); iterator.hasNext();) {
				UserInterestMap userInterestMap = (UserInterestMap) iterator.next();
				userInterestMapDao.delete(userInterestMap);
			}
			existingUser.getInterests().clear();

			addInterestsAndWeights(existingUser);
		}
	}

	private void addInterestsAndWeights(User user) {

		Set<Entry<String, Float>> entrySet = user.getInterestWeightMap().entrySet();

		for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {

			Entry<String, Float> entry = (Entry<String, Float>) iterator.next();
			String topicName = entry.getKey();
			Float weight = entry.getValue();

			Topic topic = topicService.insert(topicName);

			UserInterestMap userInterestMap = new UserInterestMap();
			userInterestMap.setUser(user);
			userInterestMap.setTopic(topic);
			userInterestMap.setWeight(weight);

			userInterestMapDao.insert(userInterestMap);

			user.getInterests().add(userInterestMap);

		}
	}

	/**
	 * This adds new roles roles to existing user but doesnot delete existing
	 * ones.
	 * 
	 * @param user
	 * @param existingUser
	 */
	private void updateUserRoles(User user) {

		for (String roleName : user.getRoles()) {
			UserRole userRole = userRoleService.insert(roleName);
			user.getUserRoles().add(userRole);
		}
	}

	@Transactional
	public void delete(Long id) {
		User user = find(id);
		if (user != null) {
			userDao.delete(user);
		}
	}

	@Transactional
	public User find(Long id) {

		User user = userDao.find(id);

		if (user != null) {
			populateViewProperties(user);
		}
		return user;
	}

	@Transactional
	public List<User> getAll() {
		return userDao.getAll();
	}

	private void populateViewProperties(User user) {

		Map<String, Float> interestWeightMap = new HashMap<String, Float>();
		for (UserInterestMap userInterestMap : user.getInterests()) {
			interestWeightMap.put(userInterestMap.getTopic().getName(), userInterestMap.getWeight());
		}
		user.setInterestWeightMap(interestWeightMap);

		Set<String> roles = new HashSet<String>();
		for (UserRole userRole : user.getUserRoles()) {
			roles.add(userRole.getName());
		}
		user.setRoles(roles);
	}

}
