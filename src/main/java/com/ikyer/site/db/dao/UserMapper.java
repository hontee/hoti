package com.ikyer.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ikyer.site.db.entity.User;
import com.ikyer.site.db.entity.UserExample;

public interface UserMapper {

  int countByExample(UserExample example);

  int deleteByExample(UserExample example);

  int deleteByPrimaryKey(Long id);

  int insert(User record);

  List<User> selectByExample(UserExample example);

  User selectByPrimaryKey(Long id);
  
  /**
   * 关注产品的用户列表
   * @param fid 产品ID
   * @param user 用户对象
   * @return
   */
  List<User> followProductUser(@Param("fid") Long fid, @Param("user") User user);
  
  /**
   * 关注主题的用户列表
   * @param fid 产品ID
   * @param user 用户对象
   * @return
   */
  List<User> followTopicUser(@Param("fid") Long fid, @Param("user") User user);

  int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  int updateByPrimaryKey(User record);
}
