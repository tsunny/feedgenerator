package ub.misim.feedgenerator.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ub.misim.feedgenerator.dao.ITopicDao;
import ub.misim.feedgenerator.entities.Topic;
import ub.misim.feedgenerator.services.ITopicService;

@Service
public class TopicServiceImpl implements ITopicService {

	@Autowired
	private ITopicDao topicDao;

	@Transactional
	public Topic insert(Topic topic) {

		topic.setLowerCasedName(topic.getName().toLowerCase().trim());

		Topic existingTopic = topicDao.findByName("lowerCasedName", topic.getLowerCasedName());
		if (existingTopic != null) {
			return existingTopic;
		}

		return topicDao.insert(topic);
	}

	@Transactional
	public Topic insert(String topicName) {

		String cleansedTopicName = topicName.toLowerCase().trim();

		Topic existingTopic = topicDao.findByName("lowerCasedName", cleansedTopicName);
		if (existingTopic != null) {
			return existingTopic;
		}

		Topic topic = new Topic();
		topic.setName(topicName);
		topic.setLowerCasedName(cleansedTopicName);

		return topicDao.insert(topic);
	}

	@Transactional
	public Topic update(Topic topic) {
		return topicDao.update(topic);
	}

	@Transactional
	public void delete(Long id) {
		Topic topic = find(id);
		if (topic != null) {
			topicDao.delete(topic);
		}
	}

	@Transactional
	public Topic find(Long id) {
		return topicDao.find(id);
	}

	@Transactional
	public List<Topic> getAll() {
		return topicDao.getAll();
	}

}
