package ub.misim.feedgenerator.services;

import java.util.List;

import ub.misim.feedgenerator.entities.Topic;

public interface ITopicService {

	Topic insert(Topic user);

	Topic update(Topic user);

	void delete(Long id);

	Topic find(Long id);

	List<Topic> getAll();

	Topic insert(String topicName);

}
