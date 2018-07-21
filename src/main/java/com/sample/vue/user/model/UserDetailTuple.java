package com.sample.vue.user.model;

import com.sample.vue.group.model.GroupEntity;

import lombok.Data;

@Data
public class UserDetailTuple {
	
	private UserEntity userEntity;
	private GroupEntity groupEntity;
}
