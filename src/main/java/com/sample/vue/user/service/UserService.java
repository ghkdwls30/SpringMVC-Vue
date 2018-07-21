package com.sample.vue.user.service;

import java.sql.SQLException;
import java.util.Map;

import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.model.UserDetailTuple;

public interface UserService  {
	UserEntity searchUser();
	void insertUser(Map<String, Object> param) throws Exception;
	void updateUser(Map<String, Object> param) throws Exception;
	void deleteUser(Map<String, Object> param) throws Exception ;
	UserEntity selectUser(Map<String, Object> param) throws Exception;
	UserDetailTuple selectDetailUser(Map<String, Object> param);
}
