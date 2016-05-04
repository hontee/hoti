package com.ikyer.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RelationMapper {

  /**
   * 添加主题和产品的关联
   * @param tid 主题ID
   * @param pid 产品ID
   * @return
   */
  int insertTopicProduct(@Param("tid") Long tid, @Param("pid") Long pid);
  
  /**
   * 删除主题和产品的关联
   * @param tid 主题ID
   * @param pid 产品ID
   * @return
   */
  int deleteTopicProduct(@Param("tid") Long tid, @Param("pid") Long pid);

  /**
   * 关注产品
   * @param uid 用户ID
   * @param fid 产品ID
   * @return
   */
  int followProduct(@Param("uid") Long uid, @Param("fid") Long fid);

  /**
   * 取消关注产品
   * @param uid 用户ID
   * @param fid 产品ID
   * @return
   */
  int unfollowProduct(@Param("uid") Long uid, @Param("fid") Long fid);

  /**
   * 关注主题
   * @param uid 用户ID
   * @param fid 主题ID
   * @return
   */
  int followTopic(@Param("uid") Long uid, @Param("fid") Long fid);

  /**
   * 取消关注主题
   * @param uid 用户ID
   * @param fid 主题ID
   * @return
   */
  int unfollowTopic(@Param("uid") Long uid, @Param("fid") Long fid);
  
  /**
   * 用户关注产品的列表
   * @param uid 用户ID
   * @return
   */
  List<Long> followProductIds(@Param("uid") Long uid);
  
  /**
   * 用户关注主题的列表
   * @param uid 用户ID
   * @return
   */
  List<Long> followTopicIds(@Param("uid") Long uid);

}
