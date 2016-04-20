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
   * @param name 用户名
   * @param type 用户类型 1=普通用户 2=管理员
   * @param state 用户状态 0=禁用 1=启用 2=锁定 3=已删除
   * @return
   */
  List<User> followProductUser(@Param("fid") Long fid, @Param("name") String name,
      @Param("type") Byte type, @Param("state") Byte state);
  
  /**
   * 关注主题的用户列表
   * @param fid 产品ID
   * @param name 用户名
   * @param type 用户类型 1=普通用户 2=管理员
   * @param state 用户状态 0=禁用 1=启用 2=锁定 3=已删除
   * @return
   */
  List<User> followTopicUser(@Param("fid") Long fid, @Param("name") String name,
      @Param("type") Byte type, @Param("state") Byte state);
  
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
