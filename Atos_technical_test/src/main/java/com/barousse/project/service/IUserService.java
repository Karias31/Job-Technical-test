package com.barousse.project.service;

import com.barousse.project.domain.User;

public interface IUserService {
	
	public String saveUser(User user);
	
	public String displayUser(String name);

}
