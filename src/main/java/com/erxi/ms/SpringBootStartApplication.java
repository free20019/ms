package com.erxi.ms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {
      DataSourceAutoConfiguration.class
})
public class SpringBootStartApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的MainApplication启动类
		return builder.sources(MainApplication.class);
	}
}
