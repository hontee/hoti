package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKey(User record);
    
    /**
     * 根据Email查询用户名，同时可用于验证邮箱是否存在
     * @param email
     * @return
     */
    String selectNameByEmail(@Param("email") String email);
    
    /**
     * 根据用户名查询用户记录
     * @param email
     * @return
     */
    User selectByName(@Param("name") String name);
    
}