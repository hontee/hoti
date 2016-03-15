package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.GroupBookmarkMapper;
import com.kuaiba.site.db.dao.GroupFollowMapper;
import com.kuaiba.site.db.dao.GroupMapper;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.model.Pagination;
import com.kuaiba.site.db.model.ValidUtils;
import com.kuaiba.site.front.vo.GroupVO;
import com.kuaiba.site.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	private Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

	@Resource
	private GroupMapper mapper;
	@Resource
	private GroupFollowMapper gfMapper;
	@Resource
	private GroupBookmarkMapper gbMapper;

	@Override
	public PageInfo<Group> findByExample(GroupExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Group> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public int countByExample(GroupExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void deleteByExample(GroupExample example) {
		ValidUtils.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void add(GroupVO vo) {
		ValidUtils.checkNotNull(vo);
		try {
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
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}

	@Override
	public List<Group> findByExample(GroupExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public Group findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void updateByExample(Group record, GroupExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id,GroupVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
			Group record = new Group();
			record.setId(id);
			record.setCategory(vo.getCategory());
			record.setDescription(vo.getDescription());
			record.setMtype(vo.getMtype());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void unfollow(Long fid) {
		ValidUtils.checkPrimaryKey(fid);
		try {
			gfMapper.deleteByPrimaryKey(CurrentUser.getCurrentUserId(), fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UNFOLLOW);
		}
	}

	@Override
	public void follow(Long fid) {
		ValidUtils.checkPrimaryKey(fid);
		try {
			gfMapper.insert(CurrentUser.getCurrentUserId(), fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_FOLLOW);
		}
	}

	@Override
	public void removeBookmark(Long gid, Long bmid) {
		ValidUtils.checkPrimaryKey(gid);
		ValidUtils.checkPrimaryKey(bmid);
		try {
			gbMapper.insert(gid, bmid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void addBookmark(Long gid, Long bmid) {
		ValidUtils.checkPrimaryKey(gid);
		ValidUtils.checkPrimaryKey(bmid);
		try {
			gbMapper.insert(gid, bmid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}
	
	@Override
	public boolean checkGroupName(String name) {
		ValidUtils.checkNotNull(name);
		try {
			GroupExample example = new GroupExample();
			example.createCriteria().andNameEqualTo(name);
			List<Group> list = mapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkGroupTitle(String title) {
		ValidUtils.checkNotNull(title);
		try {
			GroupExample example = new GroupExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Group> list = mapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}

}
