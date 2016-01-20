package ub.misim.feedgenerator.dao.impl;

import org.springframework.stereotype.Repository;

import ub.misim.feedgenerator.dao.ITopicDao;
import ub.misim.feedgenerator.entities.Topic;

@Repository
public class TopicDaoImpl extends GenericDaoImpl<Topic, Long>implements ITopicDao {

}
