package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.front.vo.UserVO;

public interface UserService extends Pager<User, UserExample> {
	
	int countByExample(UserExample example) throws SecurityException;

	void deleteByExample(UserExample example) throws SecurityException;

    void deleteByPrimaryKey(Long id) throws SecurityException;

    void add(UserVO vo) throws SecurityException;

    List<User> findByExample(UserExample example) throws SecurityException;

    User findByPrimaryKey(Long id) throws SecurityException;

    void updateByExample(User record, UserExample example) throws SecurityException;

    void updateByPrimaryKey(Long id, UserVO vo) throws SecurityException;
    
    User findByName(String name) throws SecurityException;
    
    boolean existsName(String name) throws SecurityException;
    
    void login(String username, String password) throws SecurityException;
    
    /**
	 * 验证User名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkUserName(String name) throws SecurityException;
    
}
