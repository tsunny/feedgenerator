package ub.misim.feedgenerator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ub.misim.feedgenerator.entities.User;
import ub.misim.feedgenerator.services.IUserService;

@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private IUserService userService;

	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseBody
	public User insert(@RequestBody User user) {
		return userService.insert(user);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT)
	@ResponseBody
	public User update(@RequestBody User user) {
		return userService.update(user);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAll() {
		return userService.getAll();
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public User find(@PathVariable Long id) {
		return userService.find(id);
	}

}
