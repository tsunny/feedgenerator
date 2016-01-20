package ub.misim.feedgenerator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ub.misim.feedgenerator.entities.UserRole;
import ub.misim.feedgenerator.services.IUserRoleService;

@RestController
@RequestMapping("userroles")
public class UserRolesController {

	@Autowired
	private IUserRoleService userRoleService;

	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseBody
	public UserRole insert(@RequestBody UserRole user) {
		return userRoleService.insert(user);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT)
	@ResponseBody
	public UserRole update(@RequestBody UserRole user) {
		return userRoleService.update(user);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		userRoleService.delete(id);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public List<UserRole> getAll() {
		return userRoleService.getAll();
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserRole find(@PathVariable Long id) {
		return userRoleService.find(id);
	}

}
