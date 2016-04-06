package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.dao.GroupBookmarkMapper;
import com.kuaiba.site.db.dao.GroupFollowMapper;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.Countable;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.GroupService;

@Service
public class CountMgrImpl implements Countable {
	
	private Logger logger = LoggerFactory.getLogger(CountMgrImpl.class);
	
	@Resource
	private DomainService domainService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private BookmarkService bookmarkService;
	@Resource
	private GroupService groupService;
	@Resource
	private GroupBookmarkMapper gbMapper;
	@Resource
	private GroupFollowMapper gfMapper;

	@Override
	public void countDomainTask() throws SecurityException {
		
		List<Domain> list = domainService.findAll();
		
		list.forEach((d) -> {
			try {
				CategoryExample example = new CategoryExample();
				example.createCriteria().andDomainEqualTo(d.getId());
				int count = categoryService.count(example);
				domainService.update(d.getId(), count);
			} catch (Exception e) {
				logger.debug("统计领域数据失败");
			}
		});
	}

	@Override
	public void countCategoryTask() throws SecurityException {
		
		List<Category> list = categoryService.findAll();

		// 统计站点数和群组数
		list.forEach((c) -> {
			try {
				BookmarkExample example = new BookmarkExample();
				example.createCriteria().andCategoryEqualTo(c.getId());
				int count = bookmarkService.count(example);
				
				GroupExample example2 = new GroupExample();
				example2.createCriteria().andCategoryEqualTo(c.getId());
				int groupCount = groupService.count(example2);
				categoryService.update(c.getId(), count, groupCount);
			} catch (Exception e) {
				logger.debug("统计分类数据失败");
			}
		});
		
	}

	@Override
	public void countGroupTask() throws SecurityException {
		
		List<Group> list = groupService.findAll(new GroupExample());
		
		list.forEach((g) -> {
			try {
				int count = gbMapper.countByGroupId(g.getId());
				int stars = gfMapper.countByGroupId(g.getId());
				groupService.update(g.getId(), count, stars);
			} catch (Exception e) {
				logger.debug("统计群组数据失败");
			}
		});
	}

}
