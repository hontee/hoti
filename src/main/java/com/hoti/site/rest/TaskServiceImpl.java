package com.hoti.site.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoti.site.core.security.ThreadUtil;
import com.hoti.site.db.api.BaseDao;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;

@Service
public class TaskServiceImpl implements TaskService {

  @Resource
  private BaseDao dao;
  
  @Override
  public void rebuildPTCategory() throws SecurityException {
    
    ThreadUtil.execute(new Runnable() {
      
      @Override
      public void run() {
        try {
          dao.rebuildTopicCategory();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    try {
      dao.rebuildProductCategory();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void rebuildCountTask() throws SecurityException {
    
    ThreadUtil.execute(new Runnable() {

      @Override
      public void run() {
        try {
          dao.countCategoryPT();
        } catch (Exception e) {
          e.printStackTrace();
        }        
      }
      
    });
    
    ThreadUtil.execute(new Runnable() {

      @Override
      public void run() {
        try {
          dao.countTopicStar();
        } catch (Exception e) {
          e.printStackTrace();
        }        
      }
      
    });
    
    try {
      dao.countProductStar();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void rebuildCountCategoryTask() throws SecurityException {
    try {
      List<Category> list = dao.findAllCategories();
      
      list.forEach((o) -> {
        try {
          long nums = countCategory(o.getId());
          o.setCategory(nums);
          dao.updateCategory(o);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * 根据Parent统计类别数
   * @param cid
   * @return
   * @throws Exception
   */
  private long countCategory(Long cid) throws Exception {
    CategoryExample example = new CategoryExample();
    example.createCriteria().andParentEqualTo(cid);
    return dao.countCategory(example);
  }

}
