
package com.zzx.humor.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * 说明 ：mybatisplus 配置类
 * 
 * @author zzx
 *
 * @date 2019年9月17日14:25:23
 */

@Configuration
@MapperScan(basePackages = { "com.**.dao" })
public class MybatisPlusConfig {

	/**
	 * mybatis plus 分页配置
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		return page;
	}

	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setFormat(true);
		return performanceInterceptor;
	}

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return i -> i.setObjectWrapperFactory(new MybatisMapWrapperFactory());
	}
}
