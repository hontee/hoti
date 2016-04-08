package com.kuaiba.site.core.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.kuaiba.site.core.exception.CacheException;
import com.kuaiba.site.core.exception.SecurityException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

@Component
public class MemcachedUtil {
  
  @Resource
  private ApplicationProps props;
  private static MemcachedUtil mu;
  
  /**
   * 初始化静态方法
   */
  @PostConstruct
  public void init() {
    mu = this;
    mu.props = this.props;
  }
  
  /**
   * 获取连接
   * 
   * @return
   * @throws IOException
   */
  private static MemcachedClient getMemcachedClient() throws IOException {
    return new MemcachedClient(new BinaryConnectionFactory(),
        AddrUtil.getAddresses(mu.props.getHost() + ":" + mu.props.getPort()));
  }

  /**
   * 销毁连接
   * 
   * @param client
   */
  private static void destory(MemcachedClient client) {
    if (client != null) {
      client.shutdown();
    }
  }

  /**
   * 设置缓存对象
   * 
   * @param key
   * @param expire
   * @param value
   */
  public static void set(String key, int expire, Object value) throws SecurityException {
    MemcachedClient client = null;
    try {
      client = getMemcachedClient();
      OperationFuture<Boolean> future = client.set(key, expire, value);
      future.get(); // 确保之前(mc.set())操作已经结束
    } catch (Exception e) {
      throw new CacheException("设置缓存失败: " + key, e);
    } finally {
      destory(client);
    }
  }

  /**
   * 获取缓存对象
   * 
   * @param key
   * @param expire
   * @param value
   */
  public static Object get(String key) throws SecurityException {
    MemcachedClient client = null;
    try {
      client = getMemcachedClient();
      return client.get(key);
    } catch (IOException e) {
      throw new CacheException("获取缓存失败: " + key, e);
    } finally {
      destory(client);
    }
  }

  /**
   * 删除缓存对象
   * 
   * @param key
   * @param expire
   * @param value
   */
  public static void delete(String key) throws SecurityException {
    ThreadUtil.execute(new Runnable() {
      public void run() {
        MemcachedClient client = null;
        try {
          client = getMemcachedClient();
          OperationFuture<Boolean> future = client.delete(key);
          future.get();
        } catch (Exception e) {
        } finally {
          destory(client);
        }
      }
    });
  }

  /**
   * 刷新所有缓存
   * 
   * @throws CacheException
   */
  public static void flush() throws CacheException {
    MemcachedClient client = null;
    try {
      client = getMemcachedClient();
      OperationFuture<Boolean> future = client.flush();
      future.get();
    } catch (Exception e) {
      throw new CacheException("刷新缓存失败: ", e);
    } finally {
      destory(client);
    }
  }

}
