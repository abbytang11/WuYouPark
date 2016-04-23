package com.ajie.wechat.service;

import com.ajie.wechat.entity.User;



public interface IUserService extends IBaseService{
	
	public User getByAccount(String account);

	public boolean register(User user);
	

}
