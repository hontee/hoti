package com.ikyer.site.rest;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ikyer.site.db.api.BaseDao;

@Service
public class TaskServiceImpl implements TaskService {

  @Resource
  private BaseDao dao;
  
  @Override
  public void rebuildCountTask() throws SecurityException {
    
    try {
      dao.countProductStar();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
