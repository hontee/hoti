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
    
}