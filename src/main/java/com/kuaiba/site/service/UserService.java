package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.db.model.Pager;
import com.kuaiba.site.front.vo.UserVO;

public interface UserService extends Pager<User, UserExample> {
	
	int countByExample(UserExample example);

	void deleteByExample(UserExample example);

    void deleteByPrimaryKey(Long id);

    void add(UserVO vo);

    List<User> findByExample(UserExample example);

    User findByPrimaryKey(Long id);

    void updateByExample(User record, UserExample example);

    void updateByPrimaryKey(Long id, UserVO vo);
    
    User findByName(String name);
    
    boolean existsName(String name);
    
    void login(String username, String password) throws Exception;
    
    /**
	 * 验证User名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkUserName(String name);
    
}
