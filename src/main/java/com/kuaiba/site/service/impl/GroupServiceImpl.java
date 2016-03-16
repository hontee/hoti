package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.FollowException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UnfollowException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.GroupBookmarkMapper;
import com.kuaiba.site.db.dao.GroupFollowMapper;
import com.kuaiba.site.db.dao.GroupMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.GroupVO;
import com.kuaiba.site.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Resource
	private GroupMapper mapper;
	@Resource
	private GroupFollowMapper gfMapper;
	@Resource
	private GroupBookmarkMapper gbMapper;

	@Override
	public PageInfo<Group> findByExample(GroupExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Group> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取数据失败", e);
		}
	}

	@Override
	public int countByExample(GroupExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计数据失败", e);
		}
	}

	@Override
	public void deleteByExample(GroupExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除数据失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除数据失败", e);
		}
	}

	@Override
	public void add(GroupVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			Group record = new Group();
			record.setCategory(vo.getCategory());
			record.setCount(0);
			record.setCreateBy(CurrentUser.getCurrentUserId());
			record.setDescription(vo.getDescription());
			record.setMtype(vo.getMtype());
			record.setName(vo.getNameUUID());
			record.setStars(0);
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加数据失败", e);
		}
	}

	@Override
	public List<Group> findByExample(GroupExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取数据失败", e);
		}
	}

	@Override
	public Group findByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取数据失败", e);
		}
	}

	@Override
	public void updateByExample(Group record, GroupExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新数据失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id,GroupVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			Group record = new Group();
			record.setId(id);
			record.setCategory(vo.getCategory());
			record.setDescription(vo.getDescription());
			record.setMtype(vo.getMtype());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新数据失败", e);
		}
	}

	@Override
	public void unfollow(Long fid) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(fid);
			gfMapper.deleteByPrimaryKey(CurrentUser.getCurrentUserId(), fid);
		} catch (Exception e) {
			throw new UnfollowException("取消关注失败", e);
		}
	}

	@Override
	public void follow(Long fid) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(fid);
			gfMapper.insert(CurrentUser.getCurrentUserId(), fid);
		} catch (Exception e) {
			throw new FollowException("关注失败", e);
		}
	}

	@Override
	public void removeBookmark(Long gid, Long bmid) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(gid);
			ContraintValidator.checkPrimaryKey(bmid);
			gbMapper.insert(gid, bmid);
		} catch (Exception e) {
			throw new DeleteException("群组移除站点失败", e);
		}
	}

	@Override
	public void addBookmark(Long gid, Long bmid) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(gid);
			ContraintValidator.checkPrimaryKey(bmid);
			gbMapper.insert(gid, bmid);
		} catch (Exception e) {
			throw new CreateException("群组关联站点失败", e);
		}
	}
	
	@Override
	public boolean checkGroupName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			GroupExample example = new GroupExample();
			example.createCriteria().andNameEqualTo(name);
			List<Group> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测名称失败", e);
		}
	}

	@Override
	public boolean checkGroupTitle(String title) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(title);
			GroupExample example = new GroupExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Group> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测标题失败", e);
		}
	}

}
