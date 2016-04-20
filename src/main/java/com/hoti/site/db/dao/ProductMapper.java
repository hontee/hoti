package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;

public interface ProductMapper {
  
  int countByExample(ProductExample example);

  int deleteByExample(ProductExample example);

  int deleteByPrimaryKey(Long id);

  int insert(Product record);

  List<Product> selectByExample(ProductExample example);

  Product selectByPrimaryKey(Long id);
  
  /**
   * 用户关注的产品列表
   * @param uid 用户ID
   * @return
   */
  List<Product> selectByUid(@Param("uid") Long uid);
  
  /**
   * 主题关联的产品列表
   * @param tid 主题ID
   * @param title 产品标题
   * @param cid 类别ID
   * @param state 产品状态
   * @return
   */
  List<Product> selectByTid(@Param("tid") Long tid, @Param("title") String title,
      @Param("cid") Long cid, @Param("state") Byte state);

  int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

  int updateByPrimaryKey(Product record);
  
  /**
   * 精选批量操作
   * @param pick
   * @param array
   * @return
   */
  int updateByPick(@Param("pick") int pick, @Param("array") Long[] array);
}
