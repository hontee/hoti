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
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Resource
	private RecommendMapper mapper;
	
	@Resource
	private BookmarkService webService;

	@Override
	public PageInfo<Recommend> findByExample(RecommendExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Recommend> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取数据失败", e);
		}
	}

	@Override
	public int countByExample(RecommendExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计数据失败", e);
		}
	}

	@Override
	public void deleteByExample(RecommendExample example) throws SecurityException { 
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
	public void add(String url) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(url);
			Recommend record = FetchFactory.get(url);
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加数据失败", e);
		}
	}

	@Override
	public List<Recommend> findByExample(RecommendExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取数据失败", e);
		}
	}

	@Override
	public Recommend findByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取数据失败", e);
		}
	}

	@Override
	public void updateByExample(Recommend record, RecommendExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新数据失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, RecommendVO vo) throws SecurityException { 
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
			throw new UpdateException("更新数据失败", e);
		}
	}

	@Override
	public void audit(Long id, String remark) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(remark);
			ContraintValidator.checkPrimaryKey(id);
			Recommend record = new Recommend();
			record.setId(id);
			record.setState((byte) 3); // 审核拒绝
			record.setRemark(remark);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("推荐站点审核不通过失败", e);
		}
	}

	@Override
	public void audit(Long id, BookmarkVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			Recommend record = new Recommend();
			record.setId(id);
			record.setState((byte) 2); // 审核通过
			mapper.updateByPrimaryKey(record);
			webService.add(vo);
		} catch (Exception e) {
			throw new UpdateException("推荐站点审核通过失败", e);
		}
	}

}
