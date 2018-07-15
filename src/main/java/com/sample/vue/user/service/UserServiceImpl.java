package com.sample.vue.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserEntity searchUser() {
		return userMapper.selectUser();
	}

}
	