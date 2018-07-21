package com.sample.vue.user.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.model.UserEntity;

public interface UserService  {
	UserEntity searchUser();
	void insertUser(Map<String, Object> param) throws Exception;
	void updateUser(Map<String, Object> param) throws Exception;
	void deleteUser(Map<String, Object> param) throws Exception ;
	UserEntity selectUser(Map<String, Object> param) throws Exception;
	UserDetailTuple selectDetailUser(Map<String, Object> param) throws Exception;
	List<UserEntity> selectUserList(Map<String, Object> param) throws Exception;
	PageInfo<UserEntity> selectUserListPage(Map<String, Object> param, PageCriteria pageCriteria);
	PageInfo<UserDetailTuple> selectUserDetailListPage(Map<String, Object> param, PageCriteria pageCriteria);
	List<UserDetailTuple> selectUserDetailList(Map<String, Object> param);
}
