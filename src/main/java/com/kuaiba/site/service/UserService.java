package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.vo.UserVO;
import com.kuaiba.site.service.kit.Pager;

public interface UserService extends Pager<User, UserExample> {
	
	int countByExample(UserExample example);

	void deleteByExample(UserExample example);

    void deleteByPrimaryKey(Long id);

    void add(UserVO vo);

    List<User> findByExample(UserExample example);

    User findByPrimaryKey(Long id);

    void updateByExample(User record, UserExample example);

    void updateByPrimaryKey(Long id, UserVO vo);
    
    String findNameByEmail(String email);
    
    boolean existsEmail(String email);
    
    User findByName(String name);
    
    boolean existsName(String name);
    
    Result login(String username, String password);
    
}
