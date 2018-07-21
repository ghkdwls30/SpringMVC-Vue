package com.sample.vue.user.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.model.UserDetailTuple;


@Mapper
public interface UserMapper {
	UserEntity selectUser();
	UserDetailTuple selectDetailUser(Map<String, Object> param);
}
