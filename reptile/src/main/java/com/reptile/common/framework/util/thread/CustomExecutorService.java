package com.reptile.common.framework.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * *
 * 类名称：		CustomExecutorService   
 * 类描述：		线程池配置   
 * 创建人：		liuxing   
 * 创建时间：	2014-11-21 下午4:45:26   
 * 修改人：		liuxing   
 * 修改时间：	2014-11-21 下午4:45:26   
 * 修改备注：   
 * @version
 */
public class CustomExecutorService {

	private ExecutorService executorService; 		// 线程池
	private static final int POOL_MULTIPLE = 10 ; 	// 线程池中工作线程的数目
	private static CustomExecutorService instance;

	private CustomExecutorService() {
		// 创建一个线程池                            可用处理器数*线程池工作数
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
	}

	public synchronized static CustomExecutorService getInstance() {
		if (instance == null) {
			instance = new CustomExecutorService();
		}
		return instance;
	}

	public void execute( Runnable thread ) {
		executorService.execute(thread);
	}

}
