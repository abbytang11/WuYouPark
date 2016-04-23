package com.ajie.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajie.wechat.entity.User;
import com.ajie.wechat.service.IBaseService;
import com.ajie.wechat.service.IUserService;
import com.ajie.wechat.util.QueryCondition;

@Service("userService")
@Transactional
public class UserServiceImpl extends DefultBaseService implements IUserService{

	@Resource(name="baseService")
	private IBaseService baseService;
	
	public User getByAccount(String account){
		User user = null;
		try {
			// 1、使用hpql进行查询
//			user = (User) baseService.getUniqueResultByJpql("from User as o where o.account=?", account);
			
			// 2、借助QueryCondition 进行查询
			QueryCondition queryCondition = new QueryCondition("account", QueryCondition.EQ, account);
			
			// 3、借助QueryCondition，使用自定义hpql进行查询
//			String hpql = "o.account='" + account + "'";
//			QueryCondition queryCondition = new QueryCondition(hpql);
			
			List<QueryCondition> queryConditions = new ArrayList<QueryCondition>();
			queryConditions.add(queryCondition);
			
			user = (User) baseService.getSingleResult(User.class, queryConditions);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean register(User user){
		boolean flag = false;
		try {
			baseService.save(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
