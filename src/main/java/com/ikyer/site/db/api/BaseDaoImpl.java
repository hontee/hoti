package com.ikyer.site.db.api;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ikyer.site.db.dao.MenuMapper;
import com.ikyer.site.db.dao.ProductMapper;
import com.ikyer.site.db.dao.RecommendMapper;
import com.ikyer.site.db.dao.RelationMapper;
import com.ikyer.site.db.dao.TopicMapper;
import com.ikyer.site.db.dao.TriggerMapper;
import com.ikyer.site.db.dao.UserMapper;
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

@Repository
public class BaseDaoImpl implements BaseDao {
  
  private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
  
  @Resource
  private MenuMapper mm;
  @Resource
  private ProductMapper pm;
  @Resource
  private RecommendMapper rm;
  @Resource
  private RelationMapper re;
  @Resource
  private TopicMapper tm;
  @Resource
  private TriggerMapper trigger;
  @Resource
  private UserMapper um;
  
  /**
   * 添加分页功能
   * @param p
   */
  private void addPageHelper(Pagination p) {
    PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
  }

  @Override
  public int countMenu(MenuExample example) throws Exception {
    logger.info("统计菜单信息");
    return mm.countByExample(example);
  }

  @Override
  public int countProduct(ProductExample example) throws Exception {
    logger.info("统计产品信息");
    return pm.countByExample(example);
  }

  @Override
  public int countRecommend(RecommendExample example) throws Exception {
    logger.info("统计推荐信息");
    return rm.countByExample(example);
  }

  @Override
  public int countTopic(TopicExample example) throws Exception {
    logger.info("统计主题信息");
    return tm.countByExample(example);
  }

  @Override
  public int countUser(UserExample example) throws Exception {
    logger.info("统计用户信息");
    return um.countByExample(example);
  }

  @Override
  public void countProductStar() throws Exception {
    logger.info("统计产品的关注数");
    trigger.countProductStar();
  }

  @Override
  public void countTopicStar() throws Exception {
    logger.info("统计主题的关注数和产品数");
    trigger.countTopicStar();
  }

  @Override
  public int deleteMenu(Long id) throws Exception {
    logger.info("删除菜单：{}", id);
    return mm.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteProduct(Long id) throws Exception {
    logger.info("删除产品：{}", id);
    return pm.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteRecommend(Long id) throws Exception {
    logger.info("删除推荐：{}", id);
    return rm.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteTopic(Long id) throws Exception {
    logger.info("删除主题：{}", id);
    return tm.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteTP(Long tid, Long pid) throws Exception {
    logger.info("删除主题：{}, 关联的产品：{}", tid, pid);
    trigger.minusTopicProduct(tid);
    return re.removeTP(tid, pid);
  }

  @Override
  public int deleteUser(Long id) throws Exception {
    logger.info("删除用户：{}", id);
    return um.deleteByPrimaryKey(id);
  }

  @Override
  public int addMenu(Menu record) throws Exception {
    logger.info("添加菜单：{}", JSON.toJSONString(record));
    return mm.insert(record);
  }

  @Override
  public int addProduct(Product record) throws Exception {
    logger.info("添加产品：{}", JSON.toJSONString(record));
    return pm.insert(record);
  }

  @Override
  public int addRecommend(Recommend record) throws Exception {
    logger.info("添加推荐：{}", JSON.toJSONString(record));
    return rm.insert(record);
  }

  @Override
  public int addTopic(Topic record) throws Exception {
    logger.info("添加主题：{}", JSON.toJSONString(record));
    return tm.insert(record);
  }

  @Override
  public int addTP(Long tid, Long pid) throws Exception {
    logger.info("添加主题：{}, 关联的产品：{}", tid, pid);
    trigger.plusTopicProduct(tid);
    return re.addTP(tid, pid);
  }

  @Override
  public int addUser(User record) throws Exception {
    logger.info("添加用户：{}", JSON.toJSONString(record));
    return um.insert(record);
  }

  @Override
  public Menu findMenu(Long id) throws Exception {
    logger.info("查询菜单：{}", id);
    return mm.selectByPrimaryKey(id);
  }

  @Override
  public PageInfo<Menu> findMenus(MenuExample example, Pagination p) throws Exception {
    logger.info("分页查询菜单");
    addPageHelper(p);
    List<Menu> list = mm.selectByExample(example);
    return new PageInfo<>(list);
  }

  @Override
  public List<Menu> findAllMenus() throws Exception {
    logger.info("查询所有菜单");
    return mm.selectByExample(new MenuExample());
  }

  @Override
  public Product findProduct(Long id) throws Exception {
    logger.info("查询产品：{}", id);
    return pm.selectByPrimaryKey(id);
  }

  @Override
  public PageInfo<Product> findProducts(ProductExample example, Pagination p) throws Exception {
    logger.info("分页查询产品");
    addPageHelper(p);
    List<Product> list = pm.selectByExample(example);
    return new PageInfo<>(list);
  }

  @Override
  public PageInfo<Product> findProducts(TopicProduct tp, Pagination p) throws Exception {
    logger.info("分页查询主题关联的产品：{}", JSON.toJSONString(tp));
    addPageHelper(p);
    List<Product> list = pm.selectByTid(tp);
    return new PageInfo<>(list);
  }

  @Override
  public PageInfo<Product> findProducts(Long uid, Pagination p) throws Exception {
    logger.info("分页查询用户关注的产品：{}", uid);
    addPageHelper(p);
    List<Product> list = pm.selectByUid(uid);
    return new PageInfo<>(list);
  }

  @Override
  public Recommend findRecommend(Long id) throws Exception {
    logger.info("查询推荐：{}", id);
    return rm.selectByPrimaryKey(id);
  }

  @Override
  public PageInfo<Recommend> findRecommends(RecommendExample example, Pagination p)
      throws Exception {
    logger.info("分页查询推荐");
    addPageHelper(p);
    List<Recommend> list = rm.selectByExample(example);
    return new PageInfo<>(list);
  }

  @Override
  public Topic findTopic(Long id) throws Exception {
    logger.info("查询主题：{}", id);
    return tm.selectByPrimaryKey(id);
  }

  @Override
  public PageInfo<Topic> findTopics(TopicExample example, Pagination p) throws Exception {
    logger.info("分页查询主题");
    addPageHelper(p);
    List<Topic> list = tm.selectByExample(example);
    return new PageInfo<>(list);
  }

  @Override
  public PageInfo<Topic> findTopics(Long uid, Pagination p) throws Exception {
    logger.info("分页查询主题");
    addPageHelper(p);
    List<Topic> list = tm.selectByUid(uid);
    return new PageInfo<>(list);
  }

  @Override
  public User findUser(Long id) throws Exception {
    logger.info("查询用户：{}", id);
    return um.selectByPrimaryKey(id);
  }

  @Override
  public PageInfo<User> findUsers(UserExample example, Pagination p) throws Exception {
    logger.info("分页查询用户");
    addPageHelper(p);
    List<User> list = um.selectByExample(example);
    return new PageInfo<>(list);
  }

  @Override
  public PageInfo<User> findUsersByProduct(Long fid, Pagination p) throws Exception {
    logger.info("分页查询关注产品[{}]的用户列表", fid);
    return findUsersByProduct(fid, new User(), p);
  }

  @Override
  public PageInfo<User> findUsersByProduct(Long fid, User user, Pagination p)
      throws Exception {
    logger.info("分页查询关注产品[{}]的用户列表", fid);
    addPageHelper(p);
    List<User> list = um.followProductUser(fid, user);
    return new PageInfo<>(list);
  }

  @Override
  public PageInfo<User> findUsersByTopic(Long fid, Pagination p) throws Exception {
    logger.info("分页查询关注主题[{}]的用户列表", fid);
    return findUsersByTopic(fid, new User(), p);
  }

  @Override
  public PageInfo<User> findUsersByTopic(Long fid, User user, Pagination p)
      throws Exception {
    logger.info("分页查询关注主题[{}]的用户列表", fid);
    addPageHelper(p);
    List<User> list = um.followTopicUser(fid, user);
    return new PageInfo<>(list);
  }
  
  @Override
  public List<Long> findProductIds(Long uid) throws Exception {
    logger.info("用户[{}]关注的产品IDs", uid);
    return re.followPids(uid);
  }

  @Override
  public List<Long> findTopicIds(Long uid) throws Exception {
    logger.info("用户[{}]关注的主题IDs", uid);
    return re.followTids(uid);
  }

  @Override
  public int updateMenu(Menu record) throws Exception {
    logger.info("更新菜单：{}", JSON.toJSONString(record));
    return mm.updateByPrimaryKey(record);
  }

  @Override
  public int updateProduct(Product record) throws Exception {
    logger.info("更新产品：{}", JSON.toJSONString(record));
    return pm.updateByPrimaryKey(record);
  }

  @Override
  public int updateRecommend(Recommend record) throws Exception {
    logger.info("更新推荐：{}", JSON.toJSONString(record));
    return rm.updateByPrimaryKey(record);
  }

  @Override
  public int updateTopic(Topic record) throws Exception {
    logger.info("更新主题：{}", JSON.toJSONString(record));
    return tm.updateByPrimaryKey(record);
  }

  @Override
  public int updateUser(User record) throws Exception {
    logger.info("更新用户：{}", JSON.toJSONString(record));
    return um.updateByPrimaryKey(record);
  }
  
  @Override
  public int followProduct(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]关注产品[{}]", uid, fid);
    trigger.plusProductStar(fid);
    return re.followProduct(uid, fid);
  }

  @Override
  public int unfollowProduct(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]取消关注产品[{}]", uid, fid);
    trigger.minusProductStar(fid);
    return re.unfollowProduct(uid, fid);
  }

  @Override
  public int followTopic(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]关注主题[{}]", uid, fid);
    trigger.plusTopicStar(fid);
    return re.followTopic(uid, fid);
  }

  @Override
  public int unfollowTopic(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]取消关注主题[{}]", uid, fid);
    trigger.minusTopicStar(fid);
    return re.followTopic(uid, fid);
  }

  @Override
  public int pickProduct(Long[] array) throws Exception {
    logger.info("批量精选产品: ", Arrays.toString(array));
    return pm.updateByPick(1, array);
  }

  @Override
  public int unpickProduct(Long[] array) throws Exception {
    logger.info("批量取消精选产品: ", Arrays.toString(array));
    return pm.updateByPick(0, array);
  }

  @Override
  public int pickTopic(Long[] array) throws Exception {
    logger.info("批量精选主题: ", Arrays.toString(array));
    return tm.updateByPick(1, array);
  }

  @Override
  public int unpickTopic(Long[] array) throws Exception {
    logger.info("批量取消精选主题: ", Arrays.toString(array));
    return tm.updateByPick(0, array);
  }
  
}
