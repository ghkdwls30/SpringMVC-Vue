package com.sample.vue.user.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.vue.user.model.UserEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sample.vue.common.model.Page;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.repository.UserMapper;
import com.sample.vue.user.repository.UserRepository;

import ch.qos.logback.classic.Logger;
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
	 * 복합쿼리 : 단일 튜플 조회 예제 
	 */
	@Override
	public UserDetailTuple selectDetailUser(Map<String, Object> param) {
		return userMapper.selectDetailUser( param);
	}
	
	/**
	 * 복합쿼리 : 엔티티 리스트 조회 예제 
	 */
	@Override
	public List<UserEntity> selectUserList(Map<String, Object> param) throws Exception {
		return userMapper.selectUserList( param);
	}

	/**
	 * 복합쿼리 : 엔티티 리스트 페이징 조회 예제 
	 */
	@Override
	public PageInfo<UserEntity> selectUserListPage(Map<String, Object> param, PageCriteria pageCriteria) {
		PageHelper.startPage(pageCriteria.getPage(), pageCriteria.getSize());
		List<UserEntity> list = userMapper.selectUserList( param);		
		return new PageInfo<UserEntity>(list);
	}

	/**
	 * 복합쿼리 : 튜플 리스트 페이징 조회 예제 
	 */
	@Override
	public PageInfo<UserDetailTuple> selectUserDetailListPage(Map<String, Object> param, PageCriteria pageCriteria) {
		PageHelper.startPage(pageCriteria.getPage(), pageCriteria.getSize());
		List<UserDetailTuple> list = userMapper.selectUserDetailList( param);		
		return new PageInfo<UserDetailTuple>(list);
	}

	/**
	 * 복합쿼리 : 튶플 리스트 조회 예제 
	 */
	@Override
	public List<UserDetailTuple> selectUserDetailList(Map<String, Object> param) {
		return userMapper.selectUserDetailList(param);
	}

}
	