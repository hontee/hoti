package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.RecommendMapper;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.ValidUtils;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Resource
	private RecommendMapper mapper;

	@Override
	public PageInfo<Recommend> findByExample(RecommendExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Recommend> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(RecommendExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(RecommendExample example) {
		ValidUtils.checkNotNull(example);
		mapper.deleteByExample(example);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		mapper.deleteByPrimaryKey(id);		
	}

	@Override
	public void add(Recommend record) {
		ValidUtils.checkNotNull(record);
		mapper.insert(record);		
	}

	@Override
	public List<Recommend> findByExample(RecommendExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.selectByExample(example);
	}

	@Override
	public Recommend findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Recommend record, RecommendExample example) {
		ValidUtils.checkNotNull(record, example);
		mapper.updateByExample(record, example);
	}

	@Override
	public void updateByPrimaryKey(Recommend record) {
		ValidUtils.checkNotNull(record);
		ValidUtils.checkPrimaryKey(record.getId());
		mapper.updateByPrimaryKey(record);
	}

}
