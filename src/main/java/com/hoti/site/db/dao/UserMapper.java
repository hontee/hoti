package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserExample;

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
   * @return
   */
  List<User> followProductUser(Long fid);
  
  /**
   * 关注主题的用户列表
   * @param fid 主题ID
   * @return
   */
  List<User> followTopicUser(Long fid);
  
  /**
   * 用户关注的产品IDs
   * @param uid
   * @return
   */
  List<Long> followProductIds(Long uid);
  
  /**
   * 用户关注的主题IDs
   * @param uid
   * @return
   */
  List<Long> followTopicIds(Long uid);

  int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  int updateByPrimaryKey(User record);
}
