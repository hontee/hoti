package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.MenuMapper;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.front.vo.MenuVO;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.service.utils.Pagination;
import com.kuaiba.site.service.utils.ValidUtils;

@Service
public class MenuServiceImpl implements MenuService {
	
	private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Resource
	private MenuMapper mapper;

	@Override
	public PageInfo<Menu> findByExample(MenuExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Menu> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public int countByExample(MenuExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void deleteByExample(MenuExample example) {
		ValidUtils.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void add(MenuVO vo) {
		ValidUtils.checkNotNull(vo);
		try {
			Menu record = new Menu();
			record.setCreator(CurrentUser.getCurrentUserName());
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setPath(vo.getPath());
			record.setWeight(vo.getWeight());
			record.setOrganization(vo.getOrganization());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public List<Menu> findByExample(MenuExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public Menu findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void updateByExample(Menu record, MenuExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, MenuVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
			Menu record = new Menu();
			record.setId(id);
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setPath(vo.getPath());
			record.setWeight(vo.getWeight());
			record.setOrganization(vo.getOrganization());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}		
	}

}
