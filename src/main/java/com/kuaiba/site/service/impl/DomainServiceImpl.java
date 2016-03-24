package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.cache.CacheIDs;
import com.kuaiba.site.core.cache.MemcachedUtil;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.DomainMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.DomainVO;
import com.kuaiba.site.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService {

	@Resource
	private DomainMapper mapper;

	@Override
	public PageInfo<Domain> findByExample(DomainExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Domain> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取领域失败", e);
		}
	}

	@Override
	public int countByExample(DomainExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计领域失败", e);
		}
	}

	@Override
	public void deleteByExample(DomainExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除领域失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除领域失败", e);
		}
	}

	@Override
	public void add(DomainVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			Domain record = new Domain();
			record.setCreator(CurrentUser.getCurrentUserName());
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setWeight(vo.getWeight());
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加领域失败", e);
		}
	}

	@Override
	public List<Domain> findByExample(DomainExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取领域失败", e);
		}
	}

	@Override
	public Domain findByPrimaryKey(Long id) throws SecurityException { 
		
		ContraintValidator.checkPrimaryKey(id);
		List<Domain> list = this.getDomains();
		
		for (Domain domain : list) {
			if (id.equals(domain.getId())) {
				return domain;
			}
		}
		
		return new Domain();
	}

	@Override
	public void updateByExample(Domain record, DomainExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新领域失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, DomainVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			Domain record = new Domain();
			record.setId(id);
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setWeight(vo.getWeight());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新领域失败", e);
		}
	}
	
	@Override
	public void updateByPrimaryKey(Long id, int count) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			Domain record = new Domain();
			record.setId(id);
			record.setCount((short) count);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新领域失败", e);
		}
	}

	@Override
	public List<Domain> findByCollect(DomainExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByCollect(example);
		} catch (Exception e) {
			throw new ReadException("读取领域失败", e);
		}
	}

	@Override
	public boolean checkDomainName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			DomainExample example = new DomainExample();
			example.createCriteria().andNameEqualTo(name);
			List<Domain> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测领域名称失败", e);
		}
	}

	@Override
	public boolean checkDomainTitle(String title) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(title);
			DomainExample example = new DomainExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Domain> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测领域标题失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Domain> getDomains() throws SecurityException {
		
		List<Domain> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(CacheIDs.DOMAINS)) {
			list = (List<Domain>) MemcachedUtil.get(CacheIDs.DOMAINS);
		} else {
			// 从数据库中获取
			list = this.findByExample(new DomainExample());
			MemcachedUtil.set(CacheIDs.DOMAINS, 1000 * 60 * 30, list);
		}
		
		return list;
	}

}
