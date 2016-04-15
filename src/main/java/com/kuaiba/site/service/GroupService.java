package com.kuaiba.site.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.GroupVO;

public interface GroupService extends Pager<Group, GroupExample> {
	
	/**
	 * * 带分页的条件查询
	 * @param example
	 * @param p
	 * @return PageInfo
	 * @throws SecurityException
	 */
	PageInfo<Bookmark> find(BookmarkExample example, Pagination p) throws SecurityException;
	
	/**
	 * 按条件统计群组
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(GroupExample example) throws SecurityException;
	
	/**
	 * 按标题模糊统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(String title) throws SecurityException;

	/**
	 * 按条件删除群组
	 * @param example
	 * @throws SecurityException
	 */
    void delete(GroupExample example) throws SecurityException;

    /**
     * 按ID删除
     * @param id
     * @throws SecurityException
     */
    void delete(Long id) throws SecurityException;
    
	/**
	 * 移除群组-站点
	 * @param gid
	 * @param bmid
	 * @throws SecurityException
	 */
	void remove(Long gid, Long bmid) throws SecurityException;
	
	/**
	 * 批量移除群组-站点
	 * @param gid
	 * @param bmids
	 * @throws SecurityException
	 */
	void remove(Long gid, Long[] bmids) throws SecurityException;

    /**
     * 添加群组
     * @param vo
     * @throws SecurityException
     */
    void add(GroupVO vo) throws SecurityException;
    
	/**
	 * 添加群组-站点
	 * @param gid
	 * @param bmid
	 * @throws SecurityException
	 */
    void add(Long gid, Long bmid) throws SecurityException;
    
    /**
     * 批量添加群组-站点
     * @param gid
     * @param bmids
     * @throws SecurityException
     */
    void add(Long gid, Long[] bmids) throws SecurityException;
    
    /**
     * 获取群组列表
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Group> findAll(GroupExample example) throws SecurityException;

    /**
     * 获取群组
     * @param id
     * @return
     * @throws SecurityException
     */
    Group findOne(Long id) throws SecurityException;

    /**
     * 按条件更新群组
     * @param record
     * @param example
     * @throws SecurityException
     */
    void update(Group record, GroupExample example) throws SecurityException;

    /**
     * 按ID更新群组
     * @param id
     * @param vo
     * @throws SecurityException
     */
    void update(Long id, GroupVO vo) throws SecurityException;
    
    /**
     * 更新统计
     * @param id
     * @param count
     * @param stars
     * @throws SecurityException
     */
    void update(Long id, int count, int stars) throws SecurityException;
    
    /**
     * 关注群组
     * @param fid
     * @throws SecurityException
     */
	void follow(Long fid) throws SecurityException;
	
    /**
     * 取消关注群组
     * @param fid
     * @throws SecurityException
     */
    void unfollow(Long fid) throws SecurityException;
    
    /**
     * 添加精选
     * @param ids
     * @throws SecurityException
     */
    void pick(Long[] ids) throws SecurityException;
    
    /**
     * 取消精选
     * @param ids
     * @throws SecurityException
     */
    void unpick(Long[] ids) throws SecurityException;

    /**
	 * 验证属性值是否存在
	 * @param name
	 * @return
	 */
	boolean validate(Attribute attr, String value) throws SecurityException;
	
	/**
	 * 检测是否已关注
	 * @param fid
	 * @return
	 * @throws SecurityException
	 */
	boolean validateFollow(Long fid) throws SecurityException;
	
}
