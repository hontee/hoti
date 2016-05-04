package com.ikyer.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ikyer.site.db.entity.Topic;
import com.ikyer.site.db.entity.TopicExample;

public interface TopicMapper {

  int countByExample(TopicExample example);

  int deleteByExample(TopicExample example);

  int deleteByPrimaryKey(Long id);

  int insert(Topic record);

  List<Topic> selectByExample(TopicExample example);

  Topic selectByPrimaryKey(Long id);
  
  /**
   * 用户关注的主题列表
   * @param uid 用户ID
   * @return
   */
  List<Topic> selectByUid(@Param("uid") Long uid);

  int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

  int updateByPrimaryKey(Topic record);
  
  /**
   * 精选批量操作
   * @param pick
   * @param array
   * @return
   */
  int updateByPick(@Param("pick") int pick, @Param("array") Long[] array);
}
