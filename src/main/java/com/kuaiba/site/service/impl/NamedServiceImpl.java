package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kuaiba.site.db.dao.BookmarkMapper;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.dao.DomainMapper;
import com.kuaiba.site.db.dao.GroupMapper;
import com.kuaiba.site.db.dao.MenuMapper;
import com.kuaiba.site.db.dao.MtypeMapper;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.service.NamedService;
import com.kuaiba.site.service.utils.ValidUtils;

@Service
public class NamedServiceImpl implements NamedService {
	
	@Resource
	private BookmarkMapper bookmarkMapper;
	@Resource
	private CategoryMapper categoryMapper;
	@Resource
	private DomainMapper domainMapper;
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private MenuMapper menuMapper;
	@Resource
	private MtypeMapper mtypeMapper;
	@Resource
	private UserMapper userMapper;

	@Override
	public boolean checkBookmarkName(String name) {
		try {
			BookmarkExample example = new BookmarkExample();
			example.createCriteria().andNameEqualTo(name);
			List<Bookmark> list = bookmarkMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkBookmarkURL(String url) {
		try {
			BookmarkExample example = new BookmarkExample();
			example.createCriteria().andUrlEqualTo(url);
			List<Bookmark> list = bookmarkMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkBookmarkTitle(String title) {
		try {
			BookmarkExample example = new BookmarkExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Bookmark> list = bookmarkMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkCategoryName(String name) {
		try {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(name);
			List<Category> list = categoryMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkDomainName(String name) {
		try {
			DomainExample example = new DomainExample();
			example.createCriteria().andNameEqualTo(name);
			List<Domain> list = domainMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkDomainTitle(String title) {
		try {
			DomainExample example = new DomainExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Domain> list = domainMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkGroupName(String name) {
		try {
			GroupExample example = new GroupExample();
			example.createCriteria().andNameEqualTo(name);
			List<Group> list = groupMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkGroupTitle(String title) {
		try {
			GroupExample example = new GroupExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Group> list = groupMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkMenuName(String name) {
		try {
			MenuExample example = new MenuExample();
			example.createCriteria().andNameEqualTo(name);
			List<Menu> list = menuMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkMenuTitle(String title) {
		try {
			MenuExample example = new MenuExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Menu> list = menuMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkMTypeName(String name) {
		try {
			MtypeExample example = new MtypeExample();
			example.createCriteria().andNameEqualTo(name);
			List<Mtype> list = mtypeMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkMTypeTitle(String title) {
		try {
			MtypeExample example = new MtypeExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Mtype> list = mtypeMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkUserName(String name) {
		try {
			UserExample example = new UserExample();
			example.createCriteria().andNameEqualTo(name);
			List<User> list = userMapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

}
