package com.kuaiba.site.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.FollowException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UnfollowException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.exception.ValidationException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.core.security.MemcachedUtil;
import com.kuaiba.site.db.dao.BookmarkMapper;
import com.kuaiba.site.db.dao.GroupMapper;
import com.kuaiba.site.db.dao.MapMapper;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.front.vo.GroupVO;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.CachePolicy;
import com.kuaiba.site.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

  @Resource
  private GroupMapper mapper;
  @Resource
  private MapMapper mapMapper;
  @Resource
  private BookmarkMapper bmMapper;
  @Resource
  private CachePolicy cacheMgr;
  @Resource
  private BookmarkService bookmarkService;

  @Override
  public PageInfo<Group> find(GroupExample example, Pagination p) throws SecurityException {
    try {
      VUtil.assertNotNull(example, p);
      PagerUtil.startPage(p);
      List<Group> list = findAll(example);
      return new PageInfo<>(list);
    } catch (Exception e) {
      throw new ReadException("分页读取群组失败", e);
    }
  }

  @Override
  public PageInfo<Bookmark> find(BookmarkExample example, Pagination p) throws SecurityException {
    try {
      VUtil.assertNotNull(example, p);
      PagerUtil.startPage(p);
      List<Bookmark> list = bmMapper.selectByRelation(example);
      for (Bookmark bm : list) {
        bm.setMt(cacheMgr.readMtype(bm.getMtype()));
        bm.setFollow(bookmarkService.validateFollow(bm.getId()) ? 1 : 0);
      }

      return new PageInfo<>(list);
    } catch (Exception e) {
      throw new ReadException("分页读取群组书签失败", e);
    }
  }


  @Override
  public int count(GroupExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      return mapper.countByExample(example);
    } catch (Exception e) {
      throw new ReadException("统计群组失败", e);
    }
  }

  @Override
  public int count(String title) throws SecurityException {
    GroupExample example = new GroupExample();
    GroupExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotEmpty(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }
    return count(example);
  }

  @Override
  public void delete(GroupExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      mapper.deleteByExample(example);
    } catch (Exception e) {
      throw new DeleteException("删除群组失败", e);
    }
  }

  @Override
  public void delete(Long id) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      mapper.deleteByPrimaryKey(id);
    } catch (Exception e) {
      throw new DeleteException("删除群组失败", e);
    }
  }

  @Override
  public void remove(Long gid, Long[] bmids) throws SecurityException {
    Arrays.asList(bmids).stream().forEach((bmid) -> {
      try {
        remove(gid, bmid);
      } catch (Exception e) {}
    });
  }

  @Override
  public void add(GroupVO vo) throws SecurityException {
    try {
      VUtil.assertNotNull(vo);
      Group record = new Group();
      record.setCategory(vo.getCategory());
      record.setCount(0);
      record.setCreateBy(AuthzUtil.getUserId());
      record.setDescription(vo.getDescription());
      record.setMtype(vo.getMtype());
      record.setName(vo.getNameUUID());
      record.setStar(0);
      record.setState(vo.getState());
      record.setTitle(vo.getTitle());
      mapper.insert(record);
    } catch (Exception e) {
      throw new CreateException("添加群组失败", e);
    }
  }

  @Override
  public void add(Long gid, Long bmid) throws SecurityException {
    try {
      VUtil.assertNotNull(gid, bmid);
      mapMapper.insertGB(gid, bmid);
    } catch (Exception e) {
      throw new CreateException("群组关联站点失败", e);
    }
  }

  @Override
  public void add(Long gid, Long[] bmids) throws SecurityException {
    Arrays.asList(bmids).stream().forEach((bmid) -> {
      try {
        add(gid, bmid);
      } catch (Exception e) {}
    });
  }

  @Override
  public List<Group> findAll(GroupExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      List<Group> list = mapper.selectByExample(example);
      for (Group g : list) {
        g.setMt(cacheMgr.readMtype(g.getMtype()));
        g.setFollow(validateFollow(g.getId()) ? 1 : 0);
      }
      return list;
    } catch (Exception e) {
      throw new ReadException("读取群组失败", e);
    }
  }

  @Override
  public Group findOne(Long id) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      Group g = mapper.selectByPrimaryKey(id);
      g.setFollow(validateFollow(g.getId()) ? 1 : 0);
      return g;
    } catch (Exception e) {
      throw new ReadException("读取群组失败", e);
    }
  }

  @Override
  public void update(Group record, GroupExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(record, example);
      mapper.updateByExample(record, example);
    } catch (Exception e) {
      throw new UpdateException("更新群组失败", e);
    }
  }

  @Override
  public void update(Long id, GroupVO vo) throws SecurityException {
    try {
      VUtil.assertNotNull(vo, id);
      Group record = new Group();
      record.setId(id);
      record.setCategory(vo.getCategory());
      record.setDescription(vo.getDescription());
      record.setMtype(vo.getMtype());
      record.setState(vo.getState());
      record.setTitle(vo.getTitle());
      mapper.updateByPrimaryKey(record);
    } catch (Exception e) {
      throw new UpdateException("更新群组失败", e);
    }
  }

  @Override
  public void update(Long id, int count, int stars) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      Group record = new Group();
      record.setId(id);
      record.setCount(count);
      record.setStar(stars);
      mapper.updateByPrimaryKey(record);
    } catch (Exception e) {
      throw new UpdateException("统计群组失败", e);
    }
  }

  @Override
  public void unfollow(Long fid) throws SecurityException {
    try {
      MemcachedUtil.delete("user_follow_group");
      VUtil.assertNotNull(fid);
      mapMapper.unfollowGroup(AuthzUtil.getUserId(), fid);
    } catch (Exception e) {
      throw new UnfollowException("取消关注群组失败", e);
    }
  }

  @Override
  public void follow(Long fid) throws SecurityException {
    try {
      MemcachedUtil.delete("user_follow_group");
      VUtil.assertNotNull(fid);
      mapMapper.followGroup(AuthzUtil.getUserId(), fid);
    } catch (Exception e) {
      throw new FollowException("关注群组失败", e);
    }
  }

  @Override
  public void remove(Long gid, Long bmid) throws SecurityException {
    try {
      VUtil.assertNotNull(gid, bmid);
      mapMapper.deleteGB(gid, bmid);
    } catch (Exception e) {
      throw new DeleteException("群组移除站点失败", e);
    }
  }

  @Override
  public void pick(Long[] ids) throws SecurityException {
    pick(1, ids, "添加精选操作失败");
  }

  @Override
  public void unpick(Long[] ids) throws SecurityException {
    pick(0, ids, "取消精选操作失败");
  }

  @Override
  public boolean validate(Attribute attr, String value) throws SecurityException {
    try {
      VUtil.assertNotNull(value);
      GroupExample example = new GroupExample();

      if (attr == Attribute.TITLE) {
        example.createCriteria().andTitleEqualTo(value);
      } else { // Attribute.NAME
        example.createCriteria().andNameEqualTo(value);
      }

      return !mapper.selectByExample(example).isEmpty();
    } catch (Exception e) {
      throw new ValidationException("验证群组" + attr.name() + "失败", e);
    }
  }

  @Override
  public boolean validateFollow(Long fid) throws SecurityException {
    try {
      VUtil.assertNotNull(fid);
      return cacheMgr.readUserFollowGroup().contains(fid);
    } catch (Exception e) {
      throw new ReadException("判断用户是否关注群组失败", e);
    }
  }
  
  private void pick(int pick, Long[] ids, String message) throws SecurityException {
    try {
      mapper.updateByPick(pick, ids);
    } catch (Exception e) {
      throw new UpdateException(message, e);
    }
  }

}
