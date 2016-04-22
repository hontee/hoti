package com.hoti.site.db.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 触发更新记录<br>
 * 注意：还需要实现产品更新和主题更新的触发操作，即类别ID变更时触发
 * @author larry.qi
 */
public interface TriggerMapper {

  /**
   * 添加产品时触发产品数+1
   * @param cid 类别ID
   * @return
   */
  int insertProduct(@Param("cid") Long cid);

  /**
   * 删除产品时触发产品数-1
   * @param cid 类别ID
   * @return
   */
  int deleteProduct(@Param("cid") Long cid);

  /**
   * 添加主题时触发主题数+1
   * @param cid 类别ID
   * @return
   */
  int insertTopic(@Param("cid") Long cid);

  /**
   * 删除主题时触发主题数-1
   * @param cid 类别ID
   * @return
   */
  int deleteTopic(@Param("cid") Long cid);

  /**
   * 添加主题关联的产品时，主题统计数+1
   * @param tid 主题ID
   * @return
   */
  int insertTopicProduct(@Param("tid") Long tid);

  /**
   * 删除主题关联的产品时，主题统计数-1
   * @param tid 主题ID
   * @return
   */
  int deleteTopicProduct(@Param("tid") Long tid);

  /**
   * 关注产品时，关注数+1
   * @param pid 产品ID
   * @return
   */
  int followProduct(@Param("pid") Long pid);

  /**
   * 取消关注产品时，关注数-1
   * @param pid 产品ID
   * @return
   */
  int unfollowProduct(@Param("pid") Long pid);

  /**
   * 关注主题时，关注数+1
   * @param tid 主题ID
   * @return
   */
  int followTopic(@Param("tid") Long tid);

  /**
  * 取消关注主题时，关注数-1
  * @param tid 主题ID
  * @return
  */
  int unfollowTopic(@Param("tid") Long tid);
  
  /**
   * 更新产品类别名称
   * 
   * @param cid
   * @param category
   * @return
   */
  int updateProductCategory(@Param("cid") Long cid);
  
  /**
   * 更新主题类别名称
   * 
   * @param cid
   * @param category
   * @return
   */
  int updateTopicCategory(@Param("cid") Long cid);
  
  /**
   * 统计产品的关注数
   * @return
   */
  int countProductStar();
  
  /**
   * 统计类别的产品数和主题数
   * @return
   */
  int countCategoryPT();
  
  /**
   * 统计主题的产品数和关注数
   * @return
   */
  int countTopicStar();

}
