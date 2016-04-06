package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(MenuExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(MenuExample example);

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
    int insert(Menu record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Menu> selectByExample(MenuExample example);

    /**
     * 查询
     * @param id
     * @return
     */
    Menu selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Menu record);
    
}