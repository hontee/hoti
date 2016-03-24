package com.kuaiba.site.service;

import java.util.List;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.GroupVO;

public interface GroupService extends Pager<Group, GroupExample> {
	
	/**
	 * 按条件统计群组
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(GroupExample example) throws SecurityException;

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
     * 添加群组
     * @param vo
     * @throws SecurityException
     */
    void add(GroupVO vo) throws SecurityException;
    
    /**
     * 获取群组列表
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Group> read(GroupExample example) throws SecurityException;

    /**
     * 获取群组
     * @param id
     * @return
     * @throws SecurityException
     */
    Group read(Long id) throws SecurityException;

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
     * 取消关注群组
     * @param fid
     * @throws SecurityException
     */
    void unfollow(Long fid) throws SecurityException;

    /**
     * 关注群组
     * @param fid
     * @throws SecurityException
     */
	void follow(Long fid) throws SecurityException;
	
	/**
	 * 移除群组-站点
	 * @param gid
	 * @param bmid
	 * @throws SecurityException
	 */
	void removeBookmark(Long gid, Long bmid) throws SecurityException;
	
	/**
	 * 批量移除群组-站点
	 * @param gid
	 * @param bmids
	 * @throws SecurityException
	 */
	void removeBookmark(Long gid, Long[] bmids) throws SecurityException;

	/**
	 * 添加群组-站点
	 * @param gid
	 * @param bmid
	 * @throws SecurityException
	 */
    void addBookmark(Long gid, Long bmid) throws SecurityException;
    
    /**
     * 批量添加群组-站点
     * @param gid
     * @param bmids
     * @throws SecurityException
     */
    void addBookmark(Long gid, Long[] bmids) throws SecurityException;
    
    /**
	 * 验证属性值是否存在
	 * @param name
	 * @return
	 */
	boolean validate(Attribute attr, String value) throws SecurityException;
	

}
