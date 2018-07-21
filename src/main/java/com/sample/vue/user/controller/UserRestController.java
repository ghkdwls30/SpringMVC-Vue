package com.sample.vue.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.vue.common.controller.BaseRestController;
import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.service.UserService;


@RestController
@RequestMapping("/rest/user")
public class UserRestController extends BaseRestController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
	public UserDetailTuple selectDetailUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @PathVariable String id,
							  @RequestParam Map<String,Object> param) throws Exception {
		
		// 파라미터 세팅
		param.put(UserEntity.FILED_ID, id);

		// 컨트롤 -> 서비스로 넘어가는 파리미터는 무조건 Map형태여야함.
		// 이유 : 파라미터로 넣게 되면 추수 서비스에 파라미터가 추가/삭제 되는 경우 메소드의 형태가 변경되기 때문에
		UserDetailTuple tuple = userService.selectDetailUser(param);
		
		return tuple;
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public UserEntity selectUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @PathVariable String id,
							  @RequestParam Map<String,Object> param) throws Exception {
		
		// 파라미터 세팅
		param.put(UserEntity.FILED_ID, id);

		// 컨트롤 -> 서비스로 넘어가는 파리미터는 무조건 Map형태여야함.
		// 이유 : 파라미터로 넣게 되면 추수 서비스에 파라미터가 추가/삭제 되는 경우 메소드의 형태가 변경되기 때문에
		UserEntity entity = userService.selectUser(param);
		
		return entity;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String insertUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody Map<String,Object> param) throws Exception {
		userService.insertUser(param);
		return "SUCCESS";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updateUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody Map<String,Object> param) throws Exception {
		userService.updateUser( param);
		return "SUCCESS";
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody Map<String,Object> param) throws Exception {
		userService.deleteUser( param);
		return "SUCCESS";
	}
	
	
	
}
