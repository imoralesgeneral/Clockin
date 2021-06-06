package com.uoc.clockin.app.oauth.services;

import com.uoc.clockin.app.commons.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);
	
}
