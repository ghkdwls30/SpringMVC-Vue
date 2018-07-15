package com.sample.vue.user.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserEntity {
	
	private int id;
	private String name;
	private int age;
	private String password;
	private Timestamp regDt;
}
