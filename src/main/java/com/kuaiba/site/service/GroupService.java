package com.kuaiba.site.service;

import java.util.List;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.GroupVO;

public interface GroupService extends Pager<Group, GroupExample> {
	
	int countByExample(GroupExample example) throws SecurityException;

    void deleteByExample(GroupExample example) throws SecurityException;

    void deleteByPrimaryKey(Long id) throws SecurityException;

    void add(GroupVO vo) throws SecurityException;

    List<Group> findByExample(GroupExample example) throws SecurityException;

    Group findByPrimaryKey(Long id) throws SecurityException;

    void updateByExample(Group record, GroupExample example) throws SecurityException;

    void updateByPrimaryKey(Long id, GroupVO vo) throws SecurityException;
    
    void unfollow(Long fid) throws SecurityException;

	void follow(Long fid) throws SecurityException;
	
	void removeBookmark(Long gid, Long bmid) throws SecurityException;

    void addBookmark(Long gid, Long bmid) throws SecurityException;
    
    /**
	 * 验证Group名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkGroupName(String name) throws SecurityException;
	
	/**
	 * 验证Group标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkGroupTitle(String title) throws SecurityException;

}
