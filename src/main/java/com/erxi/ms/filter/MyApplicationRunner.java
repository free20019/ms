package com.erxi.ms.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.erxi.ms.config.DataSourceContextHolder;
import com.erxi.ms.service.GisService;
import com.erxi.ms.service.SbwxService;
import com.erxi.ms.service.ZlService;

/**
 * 
 * 容器启动 所有车辆
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

	@Autowired
	GisService gisService;
	
	
	@Autowired
	SbwxService sbwxService;
	
	@Autowired
	ZlService zlService ;

	@Override
	public void run(ApplicationArguments aa) throws Exception {
		System.out.println("MyApplicationRunner begin");
		DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_DS);
		gisService.task();
		Thread.sleep(5000);
		zlService.zl();
		sbwxService.task();
	}
}
