package com.hoti.site.rest;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.AuthzException;
import com.hoti.site.core.exception.CountException;
import com.hoti.site.core.exception.CreateException;
import com.hoti.site.core.exception.DeleteException;
import com.hoti.site.core.exception.FindException;
import com.hoti.site.core.exception.FollowException;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.exception.UpdateException;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.core.security.MemcachedUtil;
import com.hoti.site.core.security.ThreadUtil;
import com.hoti.site.db.api.BaseDao;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;
import com.hoti.site.db.entity.FetchFactory;
import com.hoti.site.db.entity.GlobalIDs;
import com.hoti.site.db.entity.HttpUtil;
import com.hoti.site.db.entity.Menu;
import com.hoti.site.db.entity.MenuExample;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;
import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.db.entity.TopicExample;
import com.hoti.site.db.entity.TopicProduct;
import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserExample;
import com.hoti.site.front.vo.CategoryVO;
import com.hoti.site.front.vo.MenuVO;
import com.hoti.site.front.vo.ProductVO;
import com.hoti.site.front.vo.RecommendVO;
import com.hoti.site.front.vo.TopicVO;
import com.hoti.site.front.vo.UserVO;

@Service
public class BaseServiceImpl implements BaseService {

  @Resource
  private BaseDao dao;

  @Override
  public int countCategory(CategoryExample example) throws SecurityException {
    try {
      return dao.countCategory(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CountException(e);
    }
  }

  @Override
  public int countMenu(MenuExample example) throws SecurityException {
    try {
      return dao.countMenu(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CountException(e);
    }
  }

  @Override
  public int countProduct(ProductExample example) throws SecurityException {
    try {
      return dao.countProduct(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CountException(e);
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
      throw new CountException(e);
    }
  }

  @Override
  public int countTopic(TopicExample example) throws SecurityException {
    try {
      return dao.countTopic(example);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CountException(e);
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
      throw new CountException(e);
    }
  }

  @Override
  public void deleteCategory(Long id) throws SecurityException {
    try {
      dao.deleteCategory(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DeleteException(e);
    }
  }

  @Override
  public void deleteMenu(Long id) throws SecurityException {
    try {
      dao.deleteMenu(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DeleteException(e);
    }
  }

  @Override
  public void deleteProduct(Long id) throws SecurityException {
    try {
      dao.deleteProduct(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DeleteException(e);
    }
  }

  @Override
  public void deleteRecommend(Long id) throws SecurityException {
    try {
      dao.deleteRecommend(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DeleteException(e);
    }
  }

  @Override
  public void deleteTopic(Long id) throws SecurityException {
    try {
      dao.deleteTopic(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DeleteException(e);
    }
  }

  @Override
  public void deleteTopicProduct(Long tid, Long pid) throws SecurityException {
    try {
      dao.deleteTopicProduct(tid, pid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DeleteException(e);
    }
  }

  @Override
  public void deleteUser(Long id) throws SecurityException {
    try {
      dao.deleteUser(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DeleteException(e);
    }
  }

  @Override
  public void addCategory(Category record) throws SecurityException {
    try {
      dao.addCategory(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CreateException(e);
    }
  }

  @Override
  public void addMenu(Menu record) throws SecurityException {
    try {
      dao.addMenu(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CreateException(e);
    }
  }

  @Override
  public void addProduct(Product record) throws SecurityException {
    try {
      dao.addProduct(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CreateException(e);
    }
  }

  @Override
  public void addRecommend(Recommend record) throws SecurityException {
    try {
      dao.addRecommend(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CreateException(e);
    }
  }

  @Override
  public void addTopic(Topic record) throws SecurityException {
    try {
      dao.addTopic(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CreateException(e);
    }
  }

  @Override
  public void addTopicProduct(Long tid, Long pid) throws SecurityException {
    try {
      dao.addTopicProduct(tid, pid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CreateException(e);
    }
  }

  @Override
  public void addUser(User record) throws SecurityException {
    try {
      dao.addUser(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CreateException(e);
    }
  }

  @Override
  public Category findCategory(Long id) throws SecurityException {
    try {
      return dao.findCategory(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public PageInfo<Category> findCategories(CategoryExample example, Pagination p)
      throws SecurityException {
    try {
      return dao.findCategories(example, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public List<Category> findAllCategories() throws SecurityException {
    try {
      return dao.findAllCategories();
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public Menu findMenu(Long id) throws SecurityException {
    try {
      return dao.findMenu(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public PageInfo<Menu> findMenus(MenuExample example, Pagination p) throws SecurityException {
    try {
      return dao.findMenus(example, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public List<Menu> findAllMenus() throws SecurityException {
    try {
      return dao.findAllMenus();
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public Product findProduct(Long id) throws SecurityException {
    try {
      /* 判断是否关注 */
      Product product = dao.findProduct(id);

      if (isFollowProduct(product.getId())) {
        product.setFollow(1);
      }

      return product;
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    } finally {
      MemcachedUtil.delete("follow.product");
    }
  }

  @Override
  public PageInfo<Product> findProducts(ProductExample example, Pagination p)
      throws SecurityException {
    try {
      PageInfo<Product> pageInfo = dao.findProducts(example, p);
      return followProductHandler(pageInfo); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    } finally {
      MemcachedUtil.delete("follow.product");
    }
  }

  @Override
  public PageInfo<Product> findTopicProducts(TopicProduct tp, Pagination p)
      throws SecurityException {
    try {
      PageInfo<Product> pageInfo = dao.findTopicProducts(tp, p);
      return followProductHandler(pageInfo); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    } finally {
      MemcachedUtil.delete("follow.product");
    }
  }

  @Override
  public PageInfo<Product> findUserProducts(Long uid, Pagination p) throws SecurityException {
    try {
      PageInfo<Product> pageInfo = dao.findUserProducts(uid, p);
      return followProductHandler(pageInfo); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
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
      throw new FindException(e);
    }
  }

  @Override
  public PageInfo<Recommend> findRecommends(RecommendExample example, Pagination p)
      throws SecurityException {
    try {
      return dao.findRecommends(example, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public Topic findTopic(Long id) throws SecurityException {
    try {
      Topic t = dao.findTopic(id);

      if (isFollowTopic(t.getId())) {
        t.setFollow(1);
      }

      return t;
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    } finally {
      MemcachedUtil.delete("follow.topic");
    }
  }

  @Override
  public PageInfo<Topic> findTopics(TopicExample example, Pagination p) throws SecurityException {
    try {
      PageInfo<Topic> pageInfo = dao.findTopics(example, p);
      return followTopicHandler(pageInfo); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    } finally {
      MemcachedUtil.delete("follow.topic");
    }
  }

  @Override
  public PageInfo<Topic> findUserTopics(Long uid, Pagination p) throws SecurityException {
    try {
      PageInfo<Topic> pageInfo = dao.findUserTopics(uid, p);
      return followTopicHandler(pageInfo); /* 判断是否关注 */
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
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
      throw new FindException(e);
    }
  }

  @Override
  public PageInfo<User> findUsers(UserExample example, Pagination p) throws SecurityException {
    try {
      return dao.findUsers(example, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public PageInfo<User> findProductUsers(Long fid, Pagination p) throws SecurityException {
    try {
      return dao.findProductUsers(fid, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }



  @Override
  public PageInfo<User> findProductUsers(Long fid, String name, Byte type, Byte state, Pagination p)
      throws SecurityException {
    try {
      return dao.findProductUsers(fid, name, type, state, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public PageInfo<User> findTopicUsers(Long fid, Pagination p) throws SecurityException {
    try {
      return dao.findTopicUsers(fid, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public PageInfo<User> findTopicUsers(Long fid, String name, Byte type, Byte state, Pagination p)
      throws SecurityException {
    try {
      return dao.findTopicUsers(fid, name, type, state, p);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

  @Override
  public boolean isFollowProduct(Long pid) {
    try {
      Object object = MemcachedUtil.get("follow.product");

      if (object == null) {
        MemcachedUtil.set("follow.product", 3600, dao.findProductIds(AuthzUtil.getUserId()));
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
  public boolean isFollowTopic(Long tid) {
    try {
      Object object = MemcachedUtil.get("follow.topic");

      if (object == null) {
        MemcachedUtil.set("follow.topic", 3600, dao.findTopicIds(AuthzUtil.getUserId()));
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
  public void updateCategory(Category record) throws SecurityException {
    try {
      dao.updateCategory(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void updateMenu(Menu record) throws SecurityException {
    try {
      dao.updateMenu(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void updateProduct(Product record) throws SecurityException {
    try {
      dao.updateProduct(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void updateRecommend(Recommend record) throws SecurityException {
    try {
      dao.updateRecommend(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void updateTopic(Topic record) throws SecurityException {
    try {
      dao.updateTopic(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void updateUser(User record) throws SecurityException {
    try {
      dao.updateUser(record);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public List<Long> followProductIds() throws SecurityException {
    try {
      return dao.followProductIds(AuthzUtil.getUserId());
    } catch (Exception e) {
      e.printStackTrace();
      throw new FollowException(e);
    }
  }

  @Override
  public List<Long> followTopicIds() throws SecurityException {
    try {
      return dao.followTopicIds(AuthzUtil.getUserId());
    } catch (Exception e) {
      e.printStackTrace();
      throw new FollowException(e);
    }
  }

  @Override
  public void followProduct(Long fid) throws SecurityException {
    try {
      dao.followProduct(AuthzUtil.getUserId(), fid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FollowException(e);
    }
  }

  @Override
  public void unfollowProduct(Long fid) throws SecurityException {
    try {
      dao.unfollowProduct(AuthzUtil.getUserId(), fid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FollowException(e);
    }
  }

  @Override
  public void followTopic(Long fid) throws SecurityException {
    try {
      dao.followTopic(AuthzUtil.getUserId(), fid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FollowException(e);
    }
  }

  @Override
  public void unfollowTopic(Long fid) throws SecurityException {
    try {
      dao.unfollowTopic(AuthzUtil.getUserId(), fid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FollowException(e);
    }
  }

  @Override
  public void pickProduct(Long[] array) throws SecurityException {
    try {
      dao.pickProduct(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void unpickProduct(Long[] array) throws SecurityException {
    try {
      dao.unpickProduct(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void pickTopic(Long[] array) throws SecurityException {
    try {
      dao.pickTopic(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void unpickTopic(Long[] array) throws SecurityException {
    try {
      dao.unpickTopic(array);
    } catch (Exception e) {
      e.printStackTrace();
      throw new UpdateException(e);
    }
  }

  @Override
  public void addUser(UserVO vo) throws SecurityException {
    User record = new User();
    record.setPasswordEncrypt(vo.getPassword());
    record.setType(vo.getType());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getName());
    addUser(record);
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
      session.setAttribute(GlobalIDs.adminUser(), AuthzUtil.isAdmin());
    } catch (AuthenticationException e) {
      e.printStackTrace();
      throw new AuthzException("用户授权失败", e);
    } catch (Exception e) {
      e.printStackTrace();
      throw new AccountException("用户名或密码错误", e);
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
  public void addRecommend(String url) throws SecurityException {
    ThreadUtil.execute(new Runnable() {
      public void run() {
        try {
          Recommend record = FetchFactory.get(url);
          /* 检测数据库, 如果已存在则直接设置为拒绝审核 */
          if (checkProductUrl(url)) {
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
    Recommend record = new Recommend();
    record.setId(id);
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
  public void addProduct(ProductVO vo) throws SecurityException {
    Product record = new Product();
    record.setName(vo.getName());
    record.setTitle(vo.getTitle());
    record.setUrl(vo.getUrl());
    record.setDescription(vo.getDescription());
    record.setTags(vo.getTags());
    record.setState(vo.getState());
    record.setCreateBy(AuthzUtil.getUserId());
    record.setCreator(AuthzUtil.getUsername());
    record.setCid(vo.getCid());
    record.setCategory(findCTitle(vo.getCid()));
    record.setReffer(GlobalIDs.reffer());
    addProduct(record);
  }

  @Override
  public boolean checkProductUrl(String url) throws SecurityException {
    ProductExample example = new ProductExample();
    example.createCriteria().andUrlEqualTo(url);
    PageInfo<Product> pageInfo = findProducts(example, new Pagination());
    /* 总记录数大于0，说明url已经存在 */
    return pageInfo.getTotal() > 0;
  }

  @Override
  public boolean checkProductFollow(Long fid) throws SecurityException {
    return followProductIds().contains(fid);
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
    record.setCid(vo.getCid());
    record.setCategory(findCTitle(vo.getCid()));
    updateProduct(record);
  }

  @Override
  public String updateProductHit(Long id) throws SecurityException {
    Product record = findProduct(id);
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
  public void addMenu(MenuVO vo) throws SecurityException {
    Menu record = new Menu();
    record.setCreator(AuthzUtil.getUsername());
    record.setDescription(vo.getDescription());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    record.setPath(vo.getPath());
    record.setWeight(vo.getWeight());
    record.setOrganization(vo.getOrganization());
    addMenu(record);
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
  public void addCategory(CategoryVO vo) throws SecurityException {
    Category record = new Category();
    record.setName(vo.getName());
    record.setTitle(vo.getTitle());
    record.setDescription(vo.getDescription());
    record.setState(vo.getState());
    record.setParent(vo.getParent());
    record.setWeight(vo.getWeight());
    record.setCreator(AuthzUtil.getUsername());
    addCategory(record);
  }

  @Override
  public boolean checkCategory(String name) throws SecurityException {
    CategoryExample example = new CategoryExample();
    example.createCriteria().andNameEqualTo(name);
    PageInfo<Category> pageInfo = findCategories(example, new Pagination());
    /* 结果记录数大于 0，说明类别名称已经存在 */
    return pageInfo.getTotal() > 0;
  }

  @Override
  public void updateCategory(Long id, CategoryVO vo) throws SecurityException {
    Category record = new Category();
    record.setId(id);
    record.setName(vo.getName());
    record.setTitle(vo.getTitle());
    record.setDescription(vo.getDescription());
    record.setState(vo.getState());
    record.setParent(vo.getParent());
    record.setWeight(vo.getWeight());
    updateCategory(record);
  }

  @Override
  public void deleteTopicProduct(Long tid, Long[] pids) throws SecurityException {
    Arrays.asList(pids).forEach((pid) -> {
      try {
        deleteTopicProduct(tid, pid);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void addTopic(TopicVO vo) throws SecurityException {
    Topic record = new Topic();
    record.setCid(vo.getCid());
    record.setCategory(findCTitle(vo.getCid()));
    record.setCreateBy(AuthzUtil.getUserId());
    record.setCreator(AuthzUtil.getUsername());
    record.setDescription(vo.getDescription());
    record.setName(vo.getName());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    addTopic(record);
  }

  @Override
  public void addTopicProduct(Long tid, Long[] pids) throws SecurityException {
    Arrays.asList(pids).forEach((pid) -> {
      try {
        addTopicProduct(tid, pid);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public boolean checkTopicFollow(Long fid) throws SecurityException {
    return followTopicIds().contains(fid);
  }

  @Override
  public void updateTopic(Long id, TopicVO vo) throws SecurityException {
    Topic record = new Topic();
    record.setId(id);
    record.setCid(vo.getCid());
    record.setCategory(findCTitle(vo.getCid()));
    record.setDescription(vo.getDescription());
    record.setState(vo.getState());
    record.setTitle(vo.getTitle());
    updateTopic(record);
  }

  /**
   * 处理用户是否关注产品
   * 
   * @param pageInfo
   * @return
   */
  private PageInfo<Product> followProductHandler(PageInfo<Product> pageInfo) {
    List<Product> list = pageInfo.getList();
    list.stream().forEach((p) -> {
      if (isFollowProduct(p.getId())) {
        p.setFollow(1);
      }
    });
    return pageInfo;
  }

  /**
   * 处理用户是否关注主题
   * 
   * @param pageInfo
   * @return
   */
  private PageInfo<Topic> followTopicHandler(PageInfo<Topic> pageInfo) {
    List<Topic> list = pageInfo.getList();
    list.stream().forEach((t) -> {
      if (isFollowTopic(t.getId())) {
        t.setFollow(1);
      }
    });
    return pageInfo;
  }

  /**
   * 查询类别名称
   * 
   * @param cid
   * @return
   * @throws SecurityException
   */
  private String findCTitle(Long cid) throws SecurityException {
    try {
      Category category = dao.findCategory(cid);
      return category.getTitle();
    } catch (Exception e) {
      e.printStackTrace();
      throw new FindException(e);
    }
  }

}
