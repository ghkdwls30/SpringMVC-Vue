package com.sample.vue.home.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.service.UserService;


@RestController
@RequestMapping("/rest/home")
public class HomeRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeRestController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user")
	public UserEntity user(Locale locale, Model model) {
		
		UserEntity userEntity = userService.searchUser();
		
		//model.addAttribute("serverTime", formattedDate );
	
		return userEntity;
	}
	
}
