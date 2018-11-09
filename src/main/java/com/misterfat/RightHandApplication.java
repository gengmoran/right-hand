package com.misterfat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spring4all.swagger.EnableSwagger2Doc;

@EnableSwagger2Doc
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = { "com.misterfat.righthand.dao" })
public class RightHandApplication {

	public static void main(String[] args) {
		SpringApplication.run(RightHandApplication.class, args);
	}
}
