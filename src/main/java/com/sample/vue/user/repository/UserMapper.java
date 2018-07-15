package com.sample.vue.user.repository;

import org.apache.ibatis.annotations.Mapper;

import com.sample.vue.user.model.UserEntity;


@Mapper
public interface UserMapper {
	UserEntity selectUser();
}
