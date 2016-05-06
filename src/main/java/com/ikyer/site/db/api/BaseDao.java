package com.ikyer.site.db.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ikyer.site.db.entity.Menu;
import com.ikyer.site.db.entity.MenuExample;
import com.ikyer.site.db.entity.Pagination;
import com.ikyer.site.db.entity.Product;
import com.ikyer.site.db.entity.ProductExample;
import com.ikyer.site.db.entity.Recommend;
import com.ikyer.site.db.entity.RecommendExample;
import com.ikyer.site.db.entity.Topic;
import com.ikyer.site.db.entity.TopicExample;
import com.ikyer.site.db.entity.TopicProduct;
import com.ikyer.site.db.entity.User;
import com.ikyer.site.db.entity.UserExample;

public interface BaseDao {

  /**
   * 统计Menu
   * @param example
   * @return
   */
  int countMenu(MenuExample example) throws Exception;

  /**
   * 统计产品
   * @param example
   * @return
   */
  int countProduct(ProductExample example) throws Exception;

  /**
   * 统计推荐
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
   * 统计产品的关注数
   * @return
   */
  void countProductStar() throws Exception;
  
  /**
   * 统计主题的关注数和产品数
   * @return
   */
  void countTopicStar() throws Exception;

  /**
   * 删除菜单
   * @param id
   * @return
   */
  int deleteMenu(Long id) throws Exception;

  /**
   * 删除产品
   * @param id
   * @return
   */
  int deleteProduct(Long id) throws Exception;

  /**
   * 删除推荐
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
   * 取消关联主题和产品
   * @param tid 主题ID
   * @param pid 产品ID
   * @return
   */
  int deleteTP(Long tid, Long pid) throws Exception;

  /**
   * 删除用户
   * @param id
   * @return
   */
  int deleteUser(Long id) throws Exception;

  /**
   * 添加菜单
   * @param record
   * @return
   */
  int addMenu(Menu record) throws Exception;

  /**
   * 添加产品
   * @param record
   * @return
   */
  int addProduct(Product record) throws Exception;

  /**
   * 添加推荐
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
   * 关联主题和产品
   * @param tid 主题ID
   * @param pid 产品ID
   * @return
   */
  int addTP(Long tid, Long pid) throws Exception;

  /**
   * 添加用户
   * @param record
   * @return
   */
  int addUser(User record) throws Exception;

  /**
   * 查询菜单
   * @param id
   * @return
   */
  Menu findMenu(Long id) throws Exception;

  /**
   * 查询菜单
   * @param example
   * @return
   */
  PageInfo<Menu> findMenus(MenuExample example, Pagination p) throws Exception;

  /**
   * 查询所有菜单
   * @param example
   * @return
   */
  List<Menu> findAllMenus() throws Exception;

  /**
   * 查询产品
   * @param id
   * @return
   */
  Product findProduct(Long id) throws Exception;

  /**
   * 查询产品
   * @param example
   * @return
   */
  PageInfo<Product> findProducts(ProductExample example, Pagination p) throws Exception;


  /**
   * 主题关联的产品列表
   * @param tp 查询参数
   * @return
   */
  PageInfo<Product> findProducts(TopicProduct tp, Pagination p) throws Exception;

  /**
   * 用户关注的产品列表
   * @param uid 用户ID
   * @return
   */
  PageInfo<Product> findProducts(Long uid, Pagination p) throws Exception;

  /**
   * 查询推荐
   * @param id
   * @return
   */
  Recommend findRecommend(Long id) throws Exception;

  /**
   * 查询推荐
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
  PageInfo<Topic> findTopics(Long uid, Pagination p) throws Exception;

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
  PageInfo<User> findUsersByProduct(Long fid, Pagination p) throws Exception;

  /**
   * 关注产品的用户列表
   * @param fid 产品ID
   * @param user 用户对象
   * @return
   */
  PageInfo<User> findUsersByProduct(Long fid, User user, Pagination p) throws Exception;

  /**
   * 关注主题的用户列表
   * @param fid 主题ID
   * @return
   */
  PageInfo<User> findUsersByTopic(Long fid, Pagination p) throws Exception;

  /**
   * 关注主题的用户列表
   * @param fid 产品ID
   * @param user 用户对象
   * @return
   */
  PageInfo<User> findUsersByTopic(Long fid, User user, Pagination p) throws Exception;

  /**
   * 用户关注产品的列表
   * @param uid 用户ID
   * @return
   */
  List<Long> findProductIds(Long uid) throws Exception;

  /**
   * 用户关注主题的列表
   * @param uid 用户ID
   * @return
   */
  List<Long> findTopicIds(Long uid) throws Exception;

  /**
   * 更新菜单
   * @param record
   * @return
   */
  int updateMenu(Menu record) throws Exception;



  /**
   * 更新产品
   * @param record
   * @return
   */
  int updateProduct(Product record) throws Exception;

  /**
   * 更新推荐
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
   * 关注产品
   * @param uid 用户ID
   * @param fid 产品ID
   * @return
   */
  int followProduct(Long uid, Long fid) throws Exception;

  /**
   * 取消关注产品
   * @param uid 用户ID
   * @param fid 产品ID
   * @return
   */
  int unfollowProduct(Long uid, Long fid) throws Exception;

  /**
   * 关注主题
   * @param uid 用户ID
   * @param fid 主题ID
   * @return
   */
  int followTopic(Long uid, Long fid) throws Exception;

  /**
   * 取消关注主题
   * @param uid 用户ID
   * @param fid 主题ID
   * @return
   */
  int unfollowTopic(Long uid, Long fid) throws Exception;

  /**
   * 精选产品
   * @param pick
   * @param array
   * @return
   */
  int pickProduct(Long[] array) throws Exception;

  /**
   * 取消精选产品
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
