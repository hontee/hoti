package com.hoti.site.rest;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoti.site.core.security.ThreadUtil;
import com.hoti.site.db.api.BaseDao;

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

}
