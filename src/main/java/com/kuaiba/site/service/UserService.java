package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.front.vo.UserVO;

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
    List<User> read(UserExample example) throws SecurityException;

    /**
     * 读取数据
     * @param id
     * @return
     * @throws SecurityException
     */
    User read(Long id) throws SecurityException;

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
    User search(String name) throws SecurityException;
    
    /**
     * 验证用户名是否已存在
     * @param name
     * @return
     * @throws SecurityException
     */
    boolean validate(String name) throws SecurityException;
    
    /**
     * 登录
     * @param username
     * @param password
     * @throws SecurityException
     */
    void login(String username, String password) throws SecurityException;
    
}
