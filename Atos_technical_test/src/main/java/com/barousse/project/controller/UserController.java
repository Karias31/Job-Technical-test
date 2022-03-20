package com.barousse.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barousse.project.domain.User;
import com.barousse.project.service.IUserService;

@RestController
@RequestMapping(method = RequestMethod.POST) 
public class UserController {
	@Autowired
	private IUserService userService;
	
	/**
	 * Method to save an user, mapped to the "/saveUser" URL via post method
	 * @param user the user we try to store in the database
	 * @return message to be displayed after the operation
	 */
	@PostMapping("/saveUser")
    public String saveUser(@RequestBody  User user) {
    	String result= userService.saveUser(user);
    	return result;
    };
    
    
    /**
     * Method to display an user's information, mapped to the "/displayUser" URL via post method
     * @param name name of the wanted user
     * @return message to be displayed after the operation
     */
    @PostMapping("/displayUser/")    
	public  String displayUser(@RequestBody String name) {
    	String result = userService.displayUser(name);
    	
    	return (result);
	};


}
