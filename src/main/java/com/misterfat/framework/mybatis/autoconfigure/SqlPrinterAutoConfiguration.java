package com.misterfat.framework.mybatis.autoconfigure;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.misterfat.framework.mybatis.interceptor.SqlPrintInterceptor;

/**
 * 自定注入SQL打印插件
 *
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class SqlPrinterAutoConfiguration {

	@Autowired
	private List<SqlSessionFactory> sqlSessionFactoryList;

	@PostConstruct
	public void addPageInterceptor() {
		SqlPrintInterceptor interceptor = new SqlPrintInterceptor();
		for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
			sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
		}
	}

}
