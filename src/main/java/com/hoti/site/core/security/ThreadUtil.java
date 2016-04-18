package com.hoti.site.core.security;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 
 * @author larry.qi
 * @serial 1. 使用Executors#newCachedThreadPool可以快速创建一个拥有自动回收线程功能且没有限制的线程池。<br>
 *         2. 使用Executors#newFixedThreadPool可以用来创建一个固定线程大小的线程池。<br>
 *         3. 使用Executors#newSingleThreadExecutor可以用来创建一个单线程的执行器。<br>
 */
public class ThreadUtil {

  private static ExecutorService es;

  private ThreadUtil() {
    super();
  }

  /**
   * 执行线程任务
   * 
   * @return
   */
  public static void execute(Runnable command) {
    getInstance().execute(command);
  }

  /**
   * 创建一个拥有自动回收线程功能的线程池
   * 
   * @return
   */
  private static ExecutorService getInstance() {
    if (es == null) {
      es = Executors.newCachedThreadPool();
    }

    return es;
  }

}
