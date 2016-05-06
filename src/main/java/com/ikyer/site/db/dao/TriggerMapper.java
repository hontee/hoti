package com.ikyer.site.db.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 触发更新记录<br>
 * 注意：还需要实现产品更新和主题更新的触发操作，即类别ID变更时触发
 * @author larry.qi
 */
public interface TriggerMapper {
  
  /**
   * 主题的产品数+1
   * @param tid 主题ID
   * @return
   */
  int plusTopicProduct(@Param("tid") Long tid);

  /**
   * 主题的产品数-1
   * @param tid 主题ID
   * @return
   */
  int minusTopicProduct(@Param("tid") Long tid);

  /**
   * 产品的关注数+1
   * @param pid 产品ID
   * @return
   */
  int plusProductStar(@Param("pid") Long pid);

  /**
   * 产品的关注数-1
   * @param pid 产品ID
   * @return
   */
  int minusProductStar(@Param("pid") Long pid);

  /**
   * 主题的关注数+1
   * @param tid 主题ID
   * @return
   */
  int plusTopicStar(@Param("tid") Long tid);

  /**
  * 取消关注主题时，关注数-1
  * @param tid 主题ID
  * @return
  */
  int minusTopicStar(@Param("tid") Long tid);
  
  /**
   * 统计产品的关注数
   * @return
   */
  int countProductStar();
  
  /**
   * 统计主题的产品数和关注数
   * @return
   */
  int countTopicStar();

}
