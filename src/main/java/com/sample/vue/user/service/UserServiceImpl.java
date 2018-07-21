package com.sample.vue.user.service;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.repository.UserMapper;
import com.sample.vue.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserEntity searchUser() {
		return userMapper.selectUser();
	}
	
	/**
	 * 단일쿼리 : 유저 삽입
	 */
	@Override
	public void insertUser( Map<String, Object> param) throws Exception {
		log.debug("insertUser");
		UserEntity entity = new UserEntity();
		BeanUtils.populate(entity,param);
		userRepository.insert( entity);
	}
	
	/**
	 * 단일쿼리 : 유저 조회
	 */
	@Override
	public UserEntity  selectUser( Map<String, Object> param) throws Exception {
		log.debug("selectUser");
		UserEntity entity = new UserEntity();
		BeanUtils.populate(entity,param);
		return userRepository.selectOne( entity);
	}
	
	/**
	 * 단일쿼리 : 유저 갱신
	 */
	@Override
	public void updateUser(Map<String, Object> param) throws Exception {
		log.debug("updateUser");
		//FIX: 복사하지 않을 프로퍼티 설정하는 방법!
		UserEntity entity = new UserEntity();
		BeanUtils.populate(entity,param);
		userRepository.update( entity);
	}
	
	/**
	 * 단일쿼리 : 유저 삭제
	 */
	@Override
	public void deleteUser(Map<String, Object> param) throws Exception {
		log.debug("deleteUser");
		UserEntity entity = new UserEntity();
		BeanUtils.populate(entity,param);
		userRepository.delete( entity);
	}

	/**
	 * 복합쿼리 : 조회예제 1
	 */
	@Override
	public UserDetailTuple selectDetailUser(Map<String, Object> param) {
		return userMapper.selectDetailUser( param);
	}

}
	