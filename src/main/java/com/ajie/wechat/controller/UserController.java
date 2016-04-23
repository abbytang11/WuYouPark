package com.ajie.wechat.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajie.wechat.entity.User;
import com.ajie.wechat.service.IUserService;

@Controller

public class UserController {
	@Resource(name="userService")
	private IUserService userService;
	@RequestMapping(value = "/saveUser.htm")
	public String saveUser(){
		User user = new User ();
		user.setAccount("ajie");
		userService.save(user);
		return "ajie";
	}
}
