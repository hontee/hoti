package com.kuaiba.site.core.exceptions;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.core.thread.ThreadPool;
import com.kuaiba.site.db.entity.Response;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.service.TrackService;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Resource
	private TrackService trackService;
	
	/**
	 * 业务处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	public @ResponseBody Response handle(Exception e) {
		ThreadPool.getInstance().execute(new Runnable() {
			@Override
			public void run() {
				try {
					Track record = new Track();
					record.setException(e.getClass().getName());
					record.setMessage(e.getMessage());
					record.setState((byte)1);
					trackService.add(record);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return new Response("1001", e.getMessage());
	}

}
