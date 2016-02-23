package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.SiteFollowMapper;
import com.kuaiba.site.db.dao.WebsiteMapper;
import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.service.WebsiteService;
import com.kuaiba.site.support.Pagination;

@Service
public class WebsiteServiceImpl implements WebsiteService {
	
	@Resource
	private WebsiteMapper mapper;
	
	@Resource
	private SiteFollowMapper sfMapper;

	@Override
	public PageInfo<Website> findByExample(WebsiteExample example, Pagination p) throws Exception {
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Website> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(WebsiteExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(WebsiteExample example) {
		mapper.deleteByExample(example);		
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void add(Website record) {
		mapper.insert(record);
	}

	@Override
	public List<Website> findByExample(WebsiteExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public Website findByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Website record, WebsiteExample example) {
		mapper.updateByExample(record, example);		
	}

	@Override
	public void updateByPrimaryKey(Website record) {
		mapper.updateByPrimaryKey(record);		
	}

	@Override
	public void unfollow(Long uid, Long fid) {
		sfMapper.deleteByPrimaryKey(uid, fid);
	}

	@Override
	public void follow(Long uid, Long fid) {
		sfMapper.insert(uid, fid);		
	}

}
