package com.kuaiba.site.core.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * @author larry.qi
 *
 */
public class ThreadPool {
	
	private static ExecutorService es;

	private ThreadPool() {
		super();
	}
	
	public static ExecutorService getInstance() {
		if (es == null) {
			es = Executors.newFixedThreadPool(5);
		}
		
		return es;
	}
	
}
