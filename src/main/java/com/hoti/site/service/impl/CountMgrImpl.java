package com.hoti.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.dao.MapMapper;
import com.hoti.site.db.entity.BookmarkExample;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.Group;
import com.hoti.site.db.entity.GroupExample;
import com.hoti.site.service.BookmarkService;
import com.hoti.site.service.CategoryService;
import com.hoti.site.service.Countable;
import com.hoti.site.service.GroupService;

@Service
public class CountMgrImpl implements Countable {
	
	private Logger logger = LoggerFactory.getLogger(CountMgrImpl.class);
	
	@Resource
	private CategoryService categoryService;
	@Resource
	private BookmarkService bookmarkService;
	@Resource
	private GroupService groupService;
	@Resource
	private MapMapper mapMapper;

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
				int count = mapMapper.countGBByGroupId(g.getId());
				int stars = mapMapper.countGroupUserById(g.getId());
				groupService.update(g.getId(), count, stars);
			} catch (Exception e) {
				logger.debug("统计群组数据失败");
			}
		});
	}

}
