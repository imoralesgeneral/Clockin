package com.uoc.clockin.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uoc.clockin.app.commons.models.entity.User;

@FeignClient(name = "service-users")
public interface UserFeignClient {

	@GetMapping("/api/users/username/{username}")
	public User findByUsername(@RequestParam String username);
}
