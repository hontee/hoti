package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.db.dao.RecommendMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.FetchFactory;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Resource
	private RecommendMapper mapper;

	@Override
	public PageInfo<Recommend> search(RecommendExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Recommend> list = read(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取推荐失败", e);
		}
	}

	@Override
	public int count(RecommendExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计推荐失败", e);
		}
	}

	@Override
	public void delete(RecommendExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除推荐失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除推荐失败", e);
		}
	}

	@Override
	public void add(String url) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(url);
			Recommend record = FetchFactory.get(url);
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加推荐失败", e);
		}
	}

	@Override
	public List<Recommend> read(RecommendExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取推荐失败", e);
		}
	}

	@Override
	public Recommend read(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取推荐失败", e);
		}
	}

	@Override
	public void update(Recommend record, RecommendExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新推荐失败", e);
		}
	}

	@Override
	public void update(Long id, RecommendVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			Recommend record = new Recommend();
			record.setId(id);
			record.setDescription(vo.getDescription());
			record.setTitle(vo.getTitle());
			record.setKeywords(vo.getKeywords());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新推荐失败", e);
		}
	}

}
