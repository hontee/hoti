package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserExample;

public interface UserMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(UserExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(UserExample example);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<User> selectByExample(UserExample example);

    /**
     * 查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
    
}