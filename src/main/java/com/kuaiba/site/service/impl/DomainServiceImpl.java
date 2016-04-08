package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.exception.ValidationException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.dao.DomainMapper;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.front.vo.DomainVO;
import com.kuaiba.site.interceptor.ClearCache;
import com.kuaiba.site.service.CachePolicy;
import com.kuaiba.site.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService {

	@Resource
	private DomainMapper mapper;
	@Resource
	private CategoryMapper cm;
	@Resource
	private CachePolicy cacheMgr;
	
	@Override
	public PageInfo<Domain> find(DomainExample example, Pagination p) throws SecurityException { 
		try {
			VUtil.assertNotNull(example, p);
			PagerUtil.startPage(p);
			List<Domain> list = findAll(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取领域失败", e);
		}
	}

	@Override
	public int count(DomainExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计领域失败", e);
		}
	}

	@Override
	@ClearCache("domains")
	public void delete(DomainExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除领域失败", e);
		}
	}

	@Override
	@ClearCache("domains")
	public void delete(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除领域失败", e);
		}
	}

	@Override
	@ClearCache("domains")
	public void add(DomainVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo);
			Domain record = new Domain();
			record.setCreator(AuthzUtil.getUsername());
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
	public List<Domain> findAll() throws SecurityException {
		return cacheMgr.readDomains();
	}

	@Override
	public List<Domain> findAll(DomainExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取领域失败", e);
		}
	}

	@Override
	public Domain findOne(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取领域失败", e);
		}
	}

	@Override
	@ClearCache("menus")
	public void update(Domain record, DomainExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新领域失败", e);
		}
	}

	@Override
	@ClearCache("domains")
	public void update(Long id, DomainVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo, id);
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
	@ClearCache("domains")
	public void update(Long id, int count) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			Domain record = new Domain();
			record.setId(id);
			record.setCount((short) count);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新领域失败", e);
		}
	}

	@Override
	public List<Domain> findAllWithCates(DomainExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			List<Domain> list = mapper.selectByExample(example);
			for (Domain domain : list) {
				CategoryExample ce = new CategoryExample();
				ce.createCriteria().andDomainEqualTo(domain.getId());
				domain.setCates(cm.selectByExample(ce));
			}
			
			return list;
		} catch (Exception e) {
			throw new ReadException("读取领域失败", e);
		}
	}
	
	@Override
	public boolean validate(Attribute attr, String value) throws SecurityException {
		try {
			VUtil.assertNotNull(value);
			DomainExample example = new DomainExample();
			
			 if (attr == Attribute.TITLE) {
				example.createCriteria().andTitleEqualTo(value);
			} else { // Attribute.NAME
				example.createCriteria().andNameEqualTo(value);
			}
			
			return !mapper.selectByExample(example).isEmpty();
		} catch (Exception e) {
			throw new ValidationException("验证领域" + attr.name() + "失败", e);
		}
	}

}
