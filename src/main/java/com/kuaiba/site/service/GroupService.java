package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.front.vo.GroupVO;
import com.kuaiba.site.service.utils.Pager;

public interface GroupService extends Pager<Group, GroupExample> {
	
	int countByExample(GroupExample example);

    void deleteByExample(GroupExample example);

    void deleteByPrimaryKey(Long id);

    void add(GroupVO vo);

    List<Group> findByExample(GroupExample example);

    Group findByPrimaryKey(Long id);

    void updateByExample(Group record, GroupExample example);

    void updateByPrimaryKey(Long id, GroupVO vo);
    
    void unfollow(Long fid);

	void follow(Long fid);
	
	void removeBookmark(Long gid, Long bmid);

    void addBookmark(Long gid, Long bmid);

}
