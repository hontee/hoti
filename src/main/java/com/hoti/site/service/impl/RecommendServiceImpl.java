package com.hoti.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.DeleteException;
import com.hoti.site.core.exception.ReadException;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.exception.UpdateException;
import com.hoti.site.core.security.ThreadUtil;
import com.hoti.site.db.dao.RecommendMapper;
import com.hoti.site.db.entity.FetchFactory;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.front.vo.BookmarkVO;
import com.hoti.site.front.vo.RecommendVO;
import com.hoti.site.service.BookmarkService;
import com.hoti.site.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {

  @Resource
  private RecommendMapper mapper;

  @Resource
  private BookmarkService bookmarkService;

  @Override
  public PageInfo<Recommend> find(RecommendExample example, Pagination p) throws SecurityException {
    try {
      VUtil.assertNotNull(example, p);
      PagerUtil.startPage(p);
      List<Recommend> list = findAll(example);
      return new PageInfo<>(list);
    } catch (Exception e) {
      throw new ReadException("分页读取推荐失败", e);
    }
  }

  @Override
  public int count(RecommendExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      return mapper.countByExample(example);
    } catch (Exception e) {
      throw new ReadException("统计推荐失败", e);
    }
  }

  @Override
  public void delete(RecommendExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      mapper.deleteByExample(example);
    } catch (Exception e) {
      throw new DeleteException("删除推荐失败", e);
    }
  }

  @Override
  public void delete(Long id) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      mapper.deleteByPrimaryKey(id);
    } catch (Exception e) {
      throw new DeleteException("删除推荐失败", e);
    }
  }

  @Override
  public void add(String url) throws SecurityException {
    ThreadUtil.execute(new Runnable() {
      public void run() {
        try {
          VUtil.assertNotNull(url);
          Recommend record = FetchFactory.get(url);

          // 检测数据库, 如果已存在则直接设置为拒绝审核
          if (bookmarkService.validate(url)) {
            record.setState((byte) 3); // 审核拒绝
            record.setRemark("系统检测：链接已存在");
          }

          mapper.insert(record);
        } catch (Exception e) {}
      }
    });
  }

  @Override
  public List<Recommend> findAll(RecommendExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      return mapper.selectByExample(example);
    } catch (Exception e) {
      throw new ReadException("读取推荐失败", e);
    }
  }

  @Override
  public Recommend findOne(Long id) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      return mapper.selectByPrimaryKey(id);
    } catch (Exception e) {
      throw new ReadException("读取推荐失败", e);
    }
  }

  @Override
  public void update(Recommend record, RecommendExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(record, example);
      mapper.updateByExample(record, example);
    } catch (Exception e) {
      throw new UpdateException("更新推荐失败", e);
    }
  }

  @Override
  public void update(Long id, RecommendVO vo) throws SecurityException {
    try {
      VUtil.assertNotNull(vo, id);
      Recommend record = new Recommend();
      record.setId(id);
      record.setDescription(vo.getDescription());
      record.setTitle(vo.getTitle());
      record.setKeywords(vo.getKeywords());
      mapper.updateByPrimaryKey(record);
    } catch (Exception e) {
      throw new UpdateException("更新推荐失败", e);
    }
  }

  @Override
  public void audit(Long id, String remark) throws SecurityException {
    try {
      VUtil.assertNotNull(remark, id);
      Recommend record = new Recommend();
      record.setId(id);
      record.setState((byte) 3); // 审核拒绝
      record.setRemark(remark);
      mapper.updateByPrimaryKey(record);
    } catch (Exception e) {
      throw new UpdateException("推荐审核拒绝失败", e);
    }
  }

  @Override
  public void audit(Long id, BookmarkVO vo) throws SecurityException {
    try {
      VUtil.assertNotNull(vo, id);
      Recommend record = new Recommend();
      record.setId(id);
      record.setState((byte) 2); // 审核通过
      mapper.updateByPrimaryKey(record);
      bookmarkService.add(vo);
    } catch (Exception e) {
      throw new UpdateException("推荐审核通过失败", e);
    }
  }

}
