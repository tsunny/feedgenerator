package ub.misim.feedgenerator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ub.misim.feedgenerator.entities.Topic;
import ub.misim.feedgenerator.services.ITopicService;;

@RestController
@RequestMapping("topics")
public class TopicsController {

	@Autowired
	private ITopicService topicService;

	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseBody
	public Topic insert(@RequestBody Topic topic) {
		return topicService.insert(topic);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT)
	@ResponseBody
	public Topic update(@RequestBody Topic topic) {
		return topicService.update(topic);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		topicService.delete(id);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public List<Topic> getAll() {
		return topicService.getAll();
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Topic find(@PathVariable Long id) {
		return topicService.find(id);
	}

}
