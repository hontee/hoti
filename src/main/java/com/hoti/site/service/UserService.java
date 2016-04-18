package com.hoti.site.service;

import java.util.List;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Pager;
import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserExample;
import com.hoti.site.front.vo.UserVO;

public interface UserService extends Pager<User, UserExample> {
	
	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(UserExample example) throws SecurityException;

	/**
	 * 按条件删除
	 * @param example
	 * @throws SecurityException
	 */
	void delete(UserExample example) throws SecurityException;

	/**
	 * 删除
	 * @param id
	 * @throws SecurityException
	 */
    void delete(Long id) throws SecurityException;

    /**
     * 添加用户
     * @param vo
     * @throws SecurityException
     */
    void add(UserVO vo) throws SecurityException;

    /**
     * 读取数据列表
     * @param example
     * @return
     * @throws SecurityException
     */
    List<User> findAll(UserExample example) throws SecurityException;

    /**
     * 读取数据
     * @param id
     * @return
     * @throws SecurityException
     */
    User findOne(Long id) throws SecurityException;

    /**
     * 按条件更新
     * @param record
     * @param example
     * @throws SecurityException
     */
    void update(User record, UserExample example) throws SecurityException;

    /**
     * 更新
     * @param id
     * @param vo
     * @throws SecurityException
     */
    void update(Long id, UserVO vo) throws SecurityException;
    
    /**
     * 更新密码
     * @param id
     * @param password
     * @throws SecurityException
     */
    void update(Long id, String password) throws SecurityException;
    
    /**
     * 按用户名查询
     * @param name
     * @return
     * @throws SecurityException
     */
    User findByName(String name) throws SecurityException;
    
    /**
     * 验证用户名是否已存在
     * @param name
     * @return
     * @throws SecurityException
     */
    boolean validate(String name) throws SecurityException;
    
    /**
     * 登录认证
     * @param username
     * @param password
     * @throws SecurityException
     */
    void authenticate(String username, String password) throws SecurityException;
    
}
