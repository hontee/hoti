package com.hoti.site.db.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;
import com.hoti.site.db.entity.Menu;
import com.hoti.site.db.entity.MenuExample;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;
import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.db.entity.TopicExample;
import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserExample;

public interface BaseDao {

  /**
   * 统计类别
   * 
   * @param example
   * @return
   */
  int countCategory(CategoryExample example) throws Exception;

  /**
   * 统计Menu
   * 
   * @param example
   * @return
   */
  int countMenu(MenuExample example) throws Exception;
  
  /**
   * 统计产品
   * 
   * @param example
   * @return
   */
  int countProduct(ProductExample example) throws Exception;

  /**
   * 统计推荐
   * 
   * @param example
   * @return
   */
  int countRecommend(RecommendExample example) throws Exception;

  /**
   * 统计主题
   * @param example
   * @return
   */
  int countTopic(TopicExample example) throws Exception;

  /**
   * 统计用户
   * @param example
   * @return
   */
  int countUser(UserExample example) throws Exception;
  
  /**
   * 删除类别
   * 
   * @param id
   * @return
   */
  int deleteCategory(Long id) throws Exception;

  /**
   * 删除菜单
   * 
   * @param id
   * @return
   */
  int deleteMenu(Long id) throws Exception;

  /**
   * 删除产品
   * 
   * @param id
   * @return
   */
  int deleteProduct(Long id) throws Exception;

  /**
   * 删除推荐
   * 
   * @param id
   * @return
   */
  int deleteRecommend(Long id) throws Exception;

  /**
   * 删除主题
   * @param id
   * @return
   */
  int deleteTopic(Long id) throws Exception;

  /**
   * 删除主题和产品的关联
   * 
   * @param tid 主题ID
   * @param pid 产品ID
   * @return
   */
  int deleteTopicProduct(Long tid, Long pid) throws Exception;

  /**
   * 删除用户
   * @param id
   * @return
   */
  int deleteUser(Long id) throws Exception;

  /**
   * 添加类别
   * 
   * @param record
   * @return
   */
  int addCategory(Category record) throws Exception;

  /**
   * 添加菜单
   * 
   * @param record
   * @return
   */
  int addMenu(Menu record) throws Exception;

  /**
   * 添加产品
   * 
   * @param record
   * @return
   */
  int addProduct(Product record) throws Exception;

  /**
   * 添加推荐
   * 
   * @param record
   * @return
   */
  int addRecommend(Recommend record) throws Exception;

  /**
   * 添加主题
   * @param record
   * @return
   */
  int addTopic(Topic record) throws Exception;

  /**
   * 添加主题和产品的关联
   * 
   * @param tid 主题ID
   * @param pid 产品ID
   * @return
   */
  int addTopicProduct(Long tid, Long pid) throws Exception;

  /**
   * 添加用户
   * @param record
   * @return
   */
  int addUser(User record) throws Exception;

  /**
   * 查询类别
   * 
   * @param id
   * @return
   */
  Category findCategory(Long id) throws Exception;

  /**
   * 查询类别
   * 
   * @param example
   * @return
   */
  PageInfo<Category> findCategories(CategoryExample example, Pagination p) throws Exception;
  
  /**
   * 查询所有类别
   * 
   * @param example
   * @return
   */
  List<Category> findAllCategories() throws Exception;

  /**
   * 查询菜单
   * 
   * @param id
   * @return
   */
  Menu findMenu(Long id) throws Exception;

  /**
   * 查询菜单
   * 
   * @param example
   * @return
   */
  PageInfo<Menu> findMenus(MenuExample example, Pagination p) throws Exception;
  
  /**
   * 查询所有菜单
   * 
   * @param example
   * @return
   */
  List<Menu> findAllMenus() throws Exception;

  /**
   * 查询产品
   * 
   * @param id
   * @return
   */
  Product findProduct(Long id) throws Exception;

  /**
   * 查询产品
   * 
   * @param example
   * @return
   */
  PageInfo<Product> findProducts(ProductExample example, Pagination p) throws Exception;
  

  /**
   * 主题关联的产品列表
   * 
   * @param tid 主题ID
   * @return
   */
  PageInfo<Product> findTopicProducts(Long tid, Pagination p) throws Exception;

  /**
   * 用户关注的产品列表
   * 
   * @param uid 用户ID
   * @return
   */
  PageInfo<Product> findUserProducts(Long uid, Pagination p) throws Exception;

  /**
   * 查询推荐
   * 
   * @param id
   * @return
   */
  Recommend findRecommend(Long id) throws Exception;

  /**
   * 查询推荐
   * 
   * @param example
   * @return
   */
  PageInfo<Recommend> findRecommends(RecommendExample example, Pagination p) throws Exception;

  /**
   * 查询主题
   * @param id
   * @return
   */
  Topic findTopic(Long id) throws Exception;

  /**
   * 查询主题
   * @param example
   * @return
   */
  PageInfo<Topic> findTopics(TopicExample example, Pagination p) throws Exception;

  /**
   * 用户关注的主题列表
   * @param uid 用户ID
   * @return
   */
  PageInfo<Topic> findUserTopics(Long uid, Pagination p) throws Exception;

  /**
   * 查询用户
   * @param id
   * @return
   */
  User findUser(Long id) throws Exception;

  /**
   * 查询用户
   * @param example
   * @return
   */
  PageInfo<User> findUsers(UserExample example, Pagination p) throws Exception;

  /**
   * 关注产品的用户列表
   * @param fid 产品ID
   * @return
   */
  PageInfo<User> findProductUsers(Long fid, Pagination p) throws Exception;
  
  /**
   * 关注产品的用户列表
   * @param fid 产品ID
   * @param name 用户名
   * @param type 用户类型 1=普通用户 2=管理员
   * @param state 用户状态 0=禁用 1=启用 2=锁定 3=已删除
   * @return
   */
  PageInfo<User> findProductUsers(Long fid, String name, Byte type, Byte state, Pagination p) throws Exception;

  /**
   * 关注主题的用户列表
   * @param fid 主题ID
   * @return
   */
  PageInfo<User> findTopicUsers(Long fid, Pagination p) throws Exception;
  
  /**
   * 关注主题的用户列表
   * @param fid 产品ID
   * @param name 用户名
   * @param type 用户类型 1=普通用户 2=管理员
   * @param state 用户状态 0=禁用 1=启用 2=锁定 3=已删除
   * @return
   */
  PageInfo<User> findTopicUsers(Long fid, String name, Byte type, Byte state, Pagination p) throws Exception;

  /**
   * 更新类别
   * 
   * @param record
   * @return
   */
  int updateCategory(Category record) throws Exception;

  /**
   * 更新菜单
   * 
   * @param record
   * @return
   */
  int updateMenu(Menu record) throws Exception;



  /**
   * 更新产品
   * 
   * @param record
   * @return
   */
  int updateProduct(Product record) throws Exception;

  /**
   * 更新推荐
   * 
   * @param record
   * @return
   */
  int updateRecommend(Recommend record) throws Exception;

  /**
   * 更新主题
   * @param record
   * @return
   */
  int updateTopic(Topic record) throws Exception;

  /**
   * 更新用户
   * @param record
   * @return
   */
  int updateUser(User record) throws Exception;
  
  /**
   * 用户关注产品IDs
   * 
   * @param uid 用户ID
   * @return
   */
  List<Long> followProductIds(Long uid) throws Exception;
  
  /**
   * 用户关注主题IDs
   * 
   * @param uid 用户ID
   * @return
   */
  List<Long> followTopicIds(Long uid) throws Exception;

  /**
   * 关注产品
   * 
   * @param uid 用户ID
   * @param fid 产品ID
   * @return
   */
  int followProduct(Long uid, Long fid) throws Exception;

  /**
   * 取消关注产品
   * 
   * @param uid 用户ID
   * @param fid 产品ID
   * @return
   */
  int unfollowProduct(Long uid, Long fid) throws Exception;

  /**
   * 关注主题
   * 
   * @param uid 用户ID
   * @param fid 主题ID
   * @return
   */
  int followTopic(Long uid, Long fid) throws Exception;

  /**
   * 取消关注主题
   * 
   * @param uid 用户ID
   * @param fid 主题ID
   * @return
   */
  int unfollowTopic(Long uid, Long fid) throws Exception;

  /**
   * 精选产品
   * 
   * @param pick
   * @param array
   * @return
   */
  int pickProduct(Long[] array) throws Exception;

  /**
   * 取消精选产品
   * 
   * @param pick
   * @param array
   * @return
   */
  int unpickProduct(Long[] array) throws Exception;

  /**
   * 精选主题
   * @param pick
   * @param array
   * @return
   */
  int pickTopic(Long[] array) throws Exception;

  /**
   * 取消精选主题
   * @param pick
   * @param array
   * @return
   */
  int unpickTopic(Long[] array) throws Exception;
  
}
