package com.kuaiba.site.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.RecommendMapper;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.net.FetchUtils;
import com.kuaiba.site.net.FetchUtils.WebModel;
import com.kuaiba.site.security.LoginUser;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.ValidUtils;
import com.kuaiba.site.vo.RecommendVO;

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
	public void add(String url) {
		ValidUtils.checkNotNull(url);
		
		WebModel wm = FetchUtils.connect(url);
		Recommend record = new Recommend();
		record.setCreator(LoginUser.getName());
		record.setDescription(wm.getDescription());
		record.setName(UUID.randomUUID().toString());
		record.setState((byte)1); // 待审核
		record.setTitle(wm.getTitle());
		record.setUrl(url);
		record.setKeywords(wm.getKeywords());
		
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
	public void updateByPrimaryKey(Long id, RecommendVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		
		Recommend record = new Recommend();
		record.setId(id);
		record.setDescription(vo.getDescription());
		record.setState(vo.getState());
		record.setTitle(vo.getTitle());
		record.setKeywords(vo.getKeywords());
		
		mapper.updateByPrimaryKey(record);
	}

}
