package com.kuaiba.site.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.db.dao.RecommendMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.service.Auditable;
import com.kuaiba.site.service.BookmarkService;

@Service
public class AuditMgrImpl implements Auditable {

	@Resource
	private RecommendMapper rMapper;

	@Resource
	private BookmarkService bmService;

	@Override
	public void auditRecommend(Long id, String remark) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(remark);
			ContraintValidator.checkPrimaryKey(id);
			Recommend record = new Recommend();
			record.setId(id);
			record.setState((byte) 3); // 审核拒绝
			record.setRemark(remark);
			rMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("推荐审核拒绝失败", e);
		}
	}

	@Override
	public void auditRecommend(Long id, BookmarkVO vo) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			Recommend record = new Recommend();
			record.setId(id);
			record.setState((byte) 2); // 审核通过
			rMapper.updateByPrimaryKey(record);
			bmService.add(vo);
		} catch (Exception e) {
			throw new UpdateException("推荐审核通过失败", e);
		}
	}

}
