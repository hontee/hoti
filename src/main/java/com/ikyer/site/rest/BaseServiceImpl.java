package com.ikyer.site.rest;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.ikyer.site.core.exception.ErrorIDs;
import com.ikyer.site.core.exception.SecurityException;
import com.ikyer.site.core.security.MemcachedUtil;
import com.ikyer.site.core.security.ThreadUtil;
import com.ikyer.site.db.api.BaseDao;
import com.ikyer.site.db.entity.FetchFactory;
import com.ikyer.site.db.entity.GlobalIDs;
import com.ikyer.site.db.entity.HttpUtil;
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
import com.ikyer.site.front.vo.MenuVO;
import com.ikyer.site.front.vo.ProductVO;
import com.ikyer.site.front.vo.RecommendVO;
import com.ikyer.site.front.vo.TopicVO;
import com.ikyer.site.front.vo.UserVO;

@Service
public class BaseServiceImpl implements BaseService {

  @Resource
  private BaseDao dao;

  @Override
  public int countMenu(MenuExample example) throws SecurityException {
    try {
      return dao.countMenu(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.COUNT_FAILIED, e);
    }
  }

  @Override
  public int countProduct(ProductExample example) throws SecurityException {
    try {
      return dao.countProduct(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.COUNT_FAILIED, e);
    }
  }

  @Override
  public int countProduct(String title) throws SecurityException {
    ProductExample example = new ProductExample();
    example.createCriteria().andTitleLike("%" + title + "%");
    return countProduct(example);
  }

  @Override
  public int countRecommend(RecommendExample example) throws SecurityException {
    try {
      return dao.countRecommend(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.COUNT_FAILIED, e);
    }
  }

  @Override
  public int countTopic(TopicExample example) throws SecurityException {
    try {
      return dao.countTopic(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.COUNT_FAILIED, e);
    }
  }

  @Override
  public int countTopic(String title) throws SecurityException {
    TopicExample example = new TopicExample();
    example.createCriteria().andTitleLike("%" + title + "%");
    return countTopic(example);
  }

  @Override
  public int countUser(UserExample example) throws SecurityException {
    try {
      return dao.countUser(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.COUNT_FAILIED, e);
    }
  }

  @Override
  public void deleteMenu(Long id) throws SecurityException {
    try {
      dao.deleteMenu(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.DELETE_FAILIED, e);
    }
  }

  @Override
  public void deleteProduct(Long id) throws SecurityException {
    try {
      dao.deleteProduct(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.DELETE_FAILIED, e);
    }
  }

  @Override
  public void deleteRecommend(Long id) throws SecurityException {
    try {
      dao.deleteRecommend(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.DELETE_FAILIED, e);
    }
  }

  @Override
  public void deleteTopic(Long id) throws SecurityException {
    try {
      dao.deleteTopic(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.DELETE_FAILIED, e);
    }
  }

  @Override
  public void deleteTP(Long tid, Long pid) throws SecurityException {
    try {
      dao.deleteTP(tid, pid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.DELETE_FAILIED, e);
    }
  }

  @Override
  public void deleteTP(Long tid, Long[] pids) throws SecurityException {
    Arrays.asList(pids).forEach((pid) -> {
      try {
        deleteTP(tid, pid);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void deleteUser(Long id) throws SecurityException {
    try {
      dao.deleteUser(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.DELETE_FAILIED, e);
    }
  }

  @Override
  public int addMenu(Menu record) throws SecurityException {
    try {
      return dao.addMenu(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.CREATE_FAILIED, e);
    }
  }

  @Override
  public int addProduct(Product record) throws SecurityException {
    try {
      return dao.addProduct(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.CREATE_FAILIED, e);
    }
  }

  @Override
  public int addRecommend(Recommend record) throws SecurityException {
    try {
      return dao.addRecommend(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.CREATE_FAILIED, e);
    }
  }

  @Override
  public int addTopic(Topic record) throws SecurityException {
    try {
      return dao.addTopic(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.CREATE_FAILIED, e);
    }
  }

  @Override
  public void addTP(Long tid, Long pid) throws SecurityException {
    try {
      dao.addTP(tid, pid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.CREATE_FAILIED, e);
    }
  }

  @Override
  public int addUser(User record) throws SecurityException {
    try {
      return dao.addUser(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.CREATE_FAILIED, e);
    }
  }

  @Override
  public Menu findMenu(Long id) throws SecurityException {
    try {
      return dao.findMenu(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public PageInfo<Menu> findMenus(MenuExample example, Pagination p) throws SecurityException {
    try {
      return dao.findMenus(example, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public List<Menu> findAllMenus() throws SecurityException {
    try {
      return dao.findAllMenus();
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public Product findProduct(Long uid, Long id) throws SecurityException {
    try {
      /* 判断是否关注 */
      Product product = dao.findProduct(id);

      if (isFollowProduct(uid, product.getId())) {
        product.setFollow(1);
      }

      return product;
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    } finally {
      MemcachedUtil.delete("follow.product");
    }
  }

  @Override
  public PageInfo<Product> findProducts(Long uid, ProductExample example, Pagination p)
      throws SecurityException {
    try {
      PageInfo<Product> pageInfo = dao.findProducts(example, p);
      return followProductHandler(pageInfo, uid); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    } finally {
      MemcachedUtil.delete("follow.product");
    }
  }

  @Override
  public PageInfo<Product> findProducts(Long uid, TopicProduct tp, Pagination p)
      throws SecurityException {
    try {
      PageInfo<Product> pageInfo = dao.findProducts(tp, p);
      return followProductHandler(pageInfo, uid); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    } finally {
      MemcachedUtil.delete("follow.product");
    }
  }

  @Override
  public PageInfo<Product> findProducts(Long uid, Pagination p) throws SecurityException {
    try {
      PageInfo<Product> pageInfo = dao.findProducts(uid, p);
      return followProductHandler(pageInfo, uid); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    } finally {
      MemcachedUtil.delete("follow.product");
    }
  }

  @Override
  public Recommend findRecommend(Long id) throws SecurityException {
    try {
      return dao.findRecommend(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public PageInfo<Recommend> findRecommends(RecommendExample example, Pagination p)
      throws SecurityException {
    try {
      return dao.findRecommends(example, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public Topic findTopic(Long uid, Long id) throws SecurityException {
    try {
      Topic t = dao.findTopic(id);

      if (isFollowTopic(uid, t.getId())) {
        t.setFollow(1);
      }

      return t;
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    } finally {
      MemcachedUtil.delete("follow.topic");
    }
  }

  @Override
  public PageInfo<Topic> findTopics(Long uid, TopicExample example, Pagination p) throws SecurityException {
    try {
      PageInfo<Topic> pageInfo = dao.findTopics(example, p);
      return followTopicHandler(pageInfo, uid); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    } finally {
      MemcachedUtil.delete("follow.topic");
    }
  }

  @Override
  public PageInfo<Topic> findTopics(Long uid, Pagination p) throws SecurityException {
    try {
      PageInfo<Topic> pageInfo = dao.findTopics(uid, p);
      return followTopicHandler(pageInfo, uid); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    } finally {
      MemcachedUtil.delete("follow.topic");
    }
  }

  @Override
  public User findUser(Long id) throws SecurityException {
    try {
      return dao.findUser(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public PageInfo<User> findUsers(UserExample example, Pagination p) throws SecurityException {
    try {
      return dao.findUsers(example, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public PageInfo<User> findUsersByProduct(Long fid, Pagination p) throws SecurityException {
    try {
      return dao.findUsersByProduct(fid, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }



  @Override
  public PageInfo<User> findUsersByProduct(Long fid, User user, Pagination p)
      throws SecurityException {
    try {
      return dao.findUsersByProduct(fid, user, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public PageInfo<User> findUsersByTopic(Long fid, Pagination p) throws SecurityException {
    try {
      return dao.findUsersByTopic(fid, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public PageInfo<User> findUsersByTopic(Long fid, User user, Pagination p)
      throws SecurityException {
    try {
      return dao.findUsersByTopic(fid, user, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.READ_FAILIED, e);
    }
  }

  @Override
  public boolean isFollowProduct(Long pid, Long uid) {
    try {
      Object object = MemcachedUtil.get("follow.product");

      if (object == null) {
        MemcachedUtil.set("follow.product", 3600, dao.findProductIds(uid));
        object = MemcachedUtil.get("follow.product");
      }

      @SuppressWarnings("unchecked")
      List<Long> list = (List<Long>) object;
      return list.contains(pid);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isFollowTopic(Long tid, Long uid) {
    try {
      Object object = MemcachedUtil.get("follow.topic");

      if (object == null) {
        MemcachedUtil.set("follow.topic", 3600, dao.findTopicIds(uid));
        object = MemcachedUtil.get("follow.topic");
      }

      @SuppressWarnings("unchecked")
      List<Long> list = (List<Long>) object;
      return list.contains(tid);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void updateMenu(Menu record) throws SecurityException {
    try {
      dao.updateMenu(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public void updateProduct(Product record) throws SecurityException {
    try {
      dao.updateProduct(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public void updateRecommend(Recommend record) throws SecurityException {
    try {
      dao.updateRecommend(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public void updateTopic(Topic record) throws SecurityException {
    try {
      dao.updateTopic(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public void updateUser(User record) throws SecurityException {
    try {
      dao.updateUser(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public List<Long> findProductIds(Long uid) throws SecurityException {
    try {
      return dao.findProductIds(uid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.FOLLOW_FAILIED, e);
    }
  }

  @Override
  public List<Long> findTopicIds(Long uid) throws SecurityException {
    try {
      return dao.findTopicIds(uid);
    } catch (Exception e) {
      e.printStackTrace();
throw new SecurityException(ErrorIDs.FOLLOW_FAILIED, e);
    }
  }

  @Override
  public void followProduct(Long uid, Long fid) throws SecurityException {
    try {
      dao.followProduct(uid, fid);
    } catch (Exception e) {
      e.printStackTrace();
throw new SecurityException(ErrorIDs.FOLLOW_FAILIED, e);
    }
  }

  @Override
  public void unfollowProduct(Long uid, Long fid) throws SecurityException {
    try {
      dao.unfollowProduct(uid, fid);
    } catch (Exception e) {
      e.printStackTrace();
throw new SecurityException(ErrorIDs.FOLLOW_FAILIED, e);
    }
  }

  @Override
  public void followTopic(Long uid, Long fid) throws SecurityException {
    try {
      dao.followTopic(uid, fid);
    } catch (Exception e) {
      e.printStackTrace();
throw new SecurityException(ErrorIDs.FOLLOW_FAILIED, e);
    }
  }

  @Override
  public void unfollowTopic(Long uid, Long fid) throws SecurityException {
    try {
      dao.unfollowTopic(uid, fid);
    } catch (Exception e) {
      e.printStackTrace();
throw new SecurityException(ErrorIDs.FOLLOW_FAILIED, e);
    }
  }

  @Override
  public void pickProduct(Long[] array) throws SecurityException {
    try {
      dao.pickProduct(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public void unpickProduct(Long[] array) throws SecurityException {
    try {
      dao.unpickProduct(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public void pickTopic(Long[] array) throws SecurityException {
    try {
      dao.pickTopic(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public void unpickTopic(Long[] array) throws SecurityException {
    try {
      dao.unpickTopic(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.UPDATE_FAILIED, e);
    }
  }

  @Override
  public int addUser(UserVO vo) throws SecurityException {
    User record = new User();
    record.setPasswordEncrypt(vo.getPassword());
    record.setType(vo.getType());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getName());
    return addUser(record);
  }

  @Override
  public User findUser(String username) throws SecurityException {
    UserExample example = new UserExample();
    example.createCriteria().andNameEqualTo(username);
    PageInfo<User> pageInfo = findUsers(example, new Pagination());
    List<User> list = pageInfo.getList();
    return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
  }

  @Override
  public boolean checkUser(String username) throws SecurityException {
    return findUser(username) != null;
  }

  @Override
  public void authenticate(String username, String password) throws SecurityException {
    try {
      UsernamePasswordToken token = new UsernamePasswordToken(username, password);
      Subject subject = SecurityUtils.getSubject();
      subject.login(token);
      String principal = (String) subject.getPrincipal();
      User currentUser = findUser(principal);
      Session session = subject.getSession();
      session.setAttribute(GlobalIDs.loginUser(), currentUser);
    } catch (AuthenticationException e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.AUTHZ_FAILIED, "用户授权失败");
    } catch (Exception e) {
      e.printStackTrace();
      throw new SecurityException(ErrorIDs.ACCOUNT_ERROR, "用户名或密码错误");
    }
  }

  @Override
  public void updateUser(Long id, UserVO vo) throws SecurityException {
    User record = new User();
    record.setId(id);
    record.setType(vo.getType());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    record.setDescription(vo.getDescription());
    updateUser(record);
  }

  @Override
  public void updateUser(Long id, String password) throws SecurityException {
    /* 更新密码，同时也更新盐值 */
    User record = new User();
    record.setId(id);
    record.setPasswordEncrypt(password);
    updateUser(record);
  }

  @Override
  public void addRecommend(Long uid, String url) throws SecurityException {
    ThreadUtil.execute(new Runnable() {
      public void run() {
        try {
          Recommend record = FetchFactory.get(url);
          /* 检测数据库, 如果已存在则直接设置为拒绝审核 */
          if (checkProductUrl(uid, url)) {
            record.setState((byte) 3); // 审核拒绝
            record.setRemark("系统检测：链接已存在");
          }
          addRecommend(record);
        } catch (SecurityException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override
  public void auditRecommendOk(Long id, ProductVO vo) throws SecurityException {
    Recommend record = findRecommend(id);
    record.setState((byte) 2); // 审核通过
    updateRecommend(record);
    addProduct(vo);
  }

  @Override
  public void auditRecommendRefuse(Long id, String remark) throws SecurityException {
    Recommend record = new Recommend();
    record.setId(id);
    record.setState((byte) 3); // 审核拒绝
    record.setRemark(remark);
    updateRecommend(record);
  }

  @Override
  public void updateRecommend(Long id, RecommendVO vo) throws SecurityException {
    Recommend record = new Recommend();
    record.setId(id);
    record.setDescription(vo.getDescription());
    record.setTitle(vo.getTitle());
    record.setKeywords(vo.getKeywords());
    updateRecommend(record);
  }

  @Override
  public int addProduct(ProductVO vo) throws SecurityException {
    Product record = new Product();
    record.setName(vo.getName());
    record.setTitle(vo.getTitle());
    record.setUrl(vo.getUrl());
    record.setDescription(vo.getDescription());
    record.setTags(vo.getTags());
    record.setState(vo.getState());
    record.setCreateBy(vo.getUid());
    record.setCreator(vo.getCreator());
    record.setReffer(GlobalIDs.reffer());
    return addProduct(record);
  }

  @Override
  public boolean checkProductUrl(Long uid, String url) throws SecurityException {
    ProductExample example = new ProductExample();
    example.createCriteria().andUrlEqualTo(url);
    PageInfo<Product> pageInfo = findProducts(uid, example, new Pagination());
    /* 总记录数大于0，说明url已经存在 */
    return pageInfo.getTotal() > 0;
  }

  @Override
  public boolean checkProductFollow(Long uid, Long fid) throws SecurityException {
    return findProductIds(uid).contains(fid);
  }

  @Override
  public void updateProduct(Long id, ProductVO vo) throws SecurityException {
    Product record = new Product();
    record.setId(id);
    record.setTitle(vo.getTitle());
    record.setUrl(vo.getUrl());
    record.setDescription(vo.getDescription());
    record.setTags(vo.getTags());
    record.setState(vo.getState());
    record.setReffer(vo.getReffer());
    updateProduct(record);
  }

  @Override
  public String updateProductHit(Long uid, Long id) throws SecurityException {
    Product record = findProduct(uid, id);
    record.setHit(record.getHit() + 1);
    
    ThreadUtil.execute(new Runnable() {
      
      @Override
      public void run() {
        try {
          updateProduct(record);
        } catch (SecurityException e) {
          e.printStackTrace();
        }
      }
    });
    
    return HttpUtil.appendQueryParams(record.getUrl(), record.getReffer());
  }

  @Override
  public int addMenu(MenuVO vo) throws SecurityException {
    Menu record = new Menu();
    record.setCreator(vo.getCreator());
    record.setDescription(vo.getDescription());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    record.setPath(vo.getPath());
    record.setWeight(vo.getWeight());
    record.setOrganization(vo.getOrganization());
    return addMenu(record);
  }

  @Override
  public void updateMenu(Long id, MenuVO vo) throws SecurityException {
    Menu record = new Menu();
    record.setId(id);
    record.setDescription(vo.getDescription());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    record.setPath(vo.getPath());
    record.setWeight(vo.getWeight());
    record.setOrganization(vo.getOrganization());
    updateMenu(record);
  }

  @Override
  public int addTopic(TopicVO vo) throws SecurityException {
    Topic record = new Topic();
    record.setCreateBy(vo.getUid());
    record.setCreator(vo.getCreator());
    record.setDescription(vo.getDescription());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    return addTopic(record);
  }

  @Override
  public void addTP(Long tid, Long[] pids) throws SecurityException {
    Arrays.asList(pids).forEach((pid) -> {
      try {
        addTP(tid, pid);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public boolean checkTopicFollow(Long uid, Long fid) throws SecurityException {
    return findTopicIds(uid).contains(fid);
  }

  @Override
  public void updateTopic(Long id, TopicVO vo) throws SecurityException {
    Topic record = new Topic();
    record.setId(id);
    record.setDescription(vo.getDescription());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    updateTopic(record);
  }

  /**
   * 处理用户是否关注产品
   * @param pageInfo
   * @return
   */
  private PageInfo<Product> followProductHandler(PageInfo<Product> pageInfo, Long uid) {
    List<Product> list = pageInfo.getList();
    list.stream().forEach((p) -> {
      if (isFollowProduct(uid, p.getId())) {
        p.setFollow(1);
      }
    });
    return pageInfo;
  }

  /**
   * 处理用户是否关注主题
   * @param pageInfo
   * @return
   */
  private PageInfo<Topic> followTopicHandler(PageInfo<Topic> pageInfo, Long uid) {
    List<Topic> list = pageInfo.getList();
    list.stream().forEach((t) -> {
      if (isFollowTopic(uid, t.getId())) {
        t.setFollow(1);
      }
    });
    return pageInfo;
  }

}
