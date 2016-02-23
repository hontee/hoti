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

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Resource
	private RecommendMapper mapper;

	@Override
	public PageInfo<Recommend> findByExample(RecommendExample example, Pagination p) throws Exception {
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Recommend> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(RecommendExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(RecommendExample example) {
		mapper.deleteByExample(example);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);		
	}

	@Override
	public void add(Recommend record) {
		mapper.insert(record);		
	}

	@Override
	public List<Recommend> findByExample(RecommendExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public Recommend findByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Recommend record, RecommendExample example) {
		mapper.updateByExample(record, example);
	}

	@Override
	public void updateByPrimaryKey(Recommend record) {
		mapper.updateByPrimaryKey(record);
	}

}
