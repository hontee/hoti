package com.hoti.site.db.api;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.hoti.site.core.security.ThreadUtil;
import com.hoti.site.db.dao.CategoryMapper;
import com.hoti.site.db.dao.MenuMapper;
import com.hoti.site.db.dao.ProductMapper;
import com.hoti.site.db.dao.RecommendMapper;
import com.hoti.site.db.dao.RelationMapper;
import com.hoti.site.db.dao.TopicMapper;
import com.hoti.site.db.dao.TriggerMapper;
import com.hoti.site.db.dao.UserMapper;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;
import com.hoti.site.db.entity.Menu;
import com.hoti.site.db.entity.MenuExample;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;
import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.db.entity.TopicExample;
import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserExample;

@Repository
public class BaseDaoImpl implements BaseDao {
  
  private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
  
  @Resource
  private CategoryMapper cm;
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
    PagerUtil.startPage(p);
  }

  @Override
  public int countCategory(CategoryExample example) throws Exception {
    logger.info("统计类别信息");
    return cm.countByExample(example);
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
  public int deleteCategory(Long id) throws Exception {
    logger.info("删除类别：{}", id);
    return cm.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteMenu(Long id) throws Exception {
    logger.info("删除菜单：{}", id);
    return mm.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteProduct(Long id) throws Exception {
    logger.info("删除产品：{}", id);
    
    // 触发类别的产品数-1
    Product p = pm.selectByPrimaryKey(id);
    Preconditions.checkNotNull(p, "产品[" + id + "]不存在");
    trigger.deleteProduct(p.getCid());
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
    
    // 触发类别的主题数-1
    Topic t = tm.selectByPrimaryKey(id);
    Preconditions.checkNotNull(t, "产品[" + id + "]不存在");
    trigger.deleteProduct(t.getCid());
    
    return tm.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteTopicProduct(Long tid, Long pid) throws Exception {
    logger.info("删除主题：{}, 关联的产品：{}", tid, pid);
    
    /*触发主题的产品数 -1*/
    trigger.deleteTopicProduct(tid);
    return re.deleteTopicProduct(tid, pid);
  }

  @Override
  public int deleteUser(Long id) throws Exception {
    logger.info("删除用户：{}", id);
    return um.deleteByPrimaryKey(id);
  }

  @Override
  public int addCategory(Category record) throws Exception {
    logger.info("添加类别：{}", JSON.toJSONString(record));
    return cm.insert(record);
  }

  @Override
  public int addMenu(Menu record) throws Exception {
    logger.info("添加菜单：{}", JSON.toJSONString(record));
    return mm.insert(record);
  }

  @Override
  public int addProduct(Product record) throws Exception {
    logger.info("添加产品：{}", JSON.toJSONString(record));
    
    // 触发类别的产品数+1
    trigger.insertProduct(record.getCid());
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
    
    // 触发类别的主题数+1
    trigger.insertTopic(record.getCid());
    return tm.insert(record);
  }

  @Override
  public int addTopicProduct(Long tid, Long pid) throws Exception {
    logger.info("添加主题：{}, 关联的产品：{}", tid, pid);
    
    /*触发主题的产品数 +1*/
    trigger.insertTopicProduct(tid);
    return re.insertTopicProduct(tid, pid);
  }

  @Override
  public int addUser(User record) throws Exception {
    logger.info("添加用户：{}", JSON.toJSONString(record));
    return um.insert(record);
  }

  @Override
  public Category findCategory(Long id) throws Exception {
    logger.info("查询类别：{}", id);
    return cm.selectByPrimaryKey(id);
  }

  @Override
  public PageInfo<Category> findCategories(CategoryExample example, Pagination p) throws Exception {
    logger.info("分页查询类别");
    addPageHelper(p);
    List<Category> list = cm.selectByExample(example);
    return new PageInfo<>(list);
  }

  @Override
  public List<Category> findAllCategories() throws Exception {
    logger.info("查询所有类别");
    return cm.selectByExample(new CategoryExample());
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
  public PageInfo<Product> findTopicProducts(Long tid, Pagination p) throws Exception {
    return findTopicProducts(tid, null, null, null, p);
  }

  @Override
  public PageInfo<Product> findTopicProducts(Long tid, String title, Long cid, Byte state,
      Pagination p) throws Exception {
    logger.info("分页查询主题关联的产品：{}", tid);
    addPageHelper(p);
    List<Product> list = pm.selectByTid(tid, title, cid, state);
    return new PageInfo<>(list);
  }

  @Override
  public PageInfo<Product> findUserProducts(Long uid, Pagination p) throws Exception {
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
  public PageInfo<Topic> findUserTopics(Long uid, Pagination p) throws Exception {
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
  public PageInfo<User> findProductUsers(Long fid, Pagination p) throws Exception {
    logger.info("分页查询关注产品[{}]的用户列表", fid);
    return findProductUsers(fid, null, null, null, p);
  }

  @Override
  public PageInfo<User> findProductUsers(Long fid, String name, Byte type, Byte state, Pagination p)
      throws Exception {
    logger.info("分页查询关注产品[{}]的用户列表", fid);
    addPageHelper(p);
    List<User> list = um.followProductUser(fid, name, type, state);
    return new PageInfo<>(list);
  }

  @Override
  public PageInfo<User> findTopicUsers(Long fid, Pagination p) throws Exception {
    logger.info("分页查询关注主题[{}]的用户列表", fid);
    return findTopicUsers(fid, null, null, null, p);
  }

  @Override
  public PageInfo<User> findTopicUsers(Long fid, String name, Byte type, Byte state, Pagination p)
      throws Exception {
    logger.info("分页查询关注主题[{}]的用户列表", fid);
    addPageHelper(p);
    List<User> list = um.followTopicUser(fid, name, type, state);
    return new PageInfo<>(list);
  }
  
  @Override
  public List<Long> findProductIds(Long uid) throws Exception {
    logger.info("用户[{}]关注的产品IDs", uid);
    return re.followProductIds(uid);
  }

  @Override
  public List<Long> findTopicIds(Long uid) throws Exception {
    logger.info("用户[{}]关注的主题IDs", uid);
    return re.followTopicIds(uid);
  }

  @Override
  public int updateCategory(Category record) throws Exception {
    logger.info("更新类别：{}", JSON.toJSONString(record));
    
    /*更新类别时，如果类别名称修改则触发更新产品表和主题表的类别名称*/
    ThreadUtil.execute(new Runnable() {
      public void run() {
        try {
          Category category = findCategory(record.getId());
          if (!record.getTitle().equals(category.getTitle())) {
            trigger.updateProductCategory(record.getId(), record.getTitle());
            trigger.updateTopicCategory(record.getId(), record.getTitle());
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    
    return cm.updateByPrimaryKey(record);
  }

  @Override
  public int updateMenu(Menu record) throws Exception {
    logger.info("更新菜单：{}", JSON.toJSONString(record));
    return mm.updateByPrimaryKey(record);
  }

  @Override
  public int updateProduct(Product record) throws Exception {
    logger.info("更新产品：{}", JSON.toJSONString(record));
    
    // 触发产品数+1或-1
    Product p = pm.selectByPrimaryKey(record.getId());
    
    /*如果CID不一致，需同时做两次更新*/
    if (record.getCid() != p.getCid()) {
      trigger.deleteProduct(p.getCid());
      trigger.insertProduct(record.getCid());
    }
    
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
    
    // 触发主题数+1或-1
    Topic t = tm.selectByPrimaryKey(record.getId());
    
    /*如果CID不一致，需同时做两次更新*/
    if (record.getCid() != t.getCid()) {
      trigger.deleteTopic(t.getCid());
      trigger.insertTopic(record.getCid());
    }
    
    return tm.updateByPrimaryKey(record);
  }

  @Override
  public int updateUser(User record) throws Exception {
    logger.info("更新用户：{}", JSON.toJSONString(record));
    return um.updateByPrimaryKey(record);
  }

  @Override
  public List<Long> followProductIds(Long uid) throws Exception {
    logger.info("用户[{}]关注产品的IDs", uid);
    return um.followProductIds(uid);
  }

  @Override
  public List<Long> followTopicIds(Long uid) throws Exception {
    logger.info("用户[{}]关注主题的IDs", uid);
    return um.followTopicIds(uid);
  }

  @Override
  public int followProduct(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]关注产品[{}]", uid, fid);
    
    /*触发产品的关注数 +1*/
    trigger.followProduct(fid);
    return re.followProduct(uid, fid);
  }

  @Override
  public int unfollowProduct(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]取消关注产品[{}]", uid, fid);
    
    /*触发产品的关注数 -1*/
    trigger.unfollowProduct(fid);
    return re.unfollowProduct(uid, fid);
  }

  @Override
  public int followTopic(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]关注主题[{}]", uid, fid);
    
    /*触发主题的关注数 +1*/
    trigger.followTopic(fid);
    return re.followTopic(uid, fid);
  }

  @Override
  public int unfollowTopic(Long uid, Long fid) throws Exception {
    logger.info("用户[{}]取消关注主题[{}]", uid, fid);
    /*触发主题的关注数 -1*/
    trigger.unfollowTopic(fid);
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
