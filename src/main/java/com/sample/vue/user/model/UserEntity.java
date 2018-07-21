package com.sample.vue.user.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserEntity {
	
	public static final String FILED_ID = "id";
	
	private int id;
	private int groupId;
	private String name;
	private int age;
	private String password;
	private Timestamp regDt;
}
