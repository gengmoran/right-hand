package com.misterfat.righthand.web.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.context.annotation.Configuration;

/**
 * siteMesh 配置
 *
 */
@Configuration
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

	private List<String> excluedPaths = new ArrayList<>();

	public void setExcluedPaths() {
		// 可通过配置文件获取
		String excluedPath = "/api/**";
		excluedPath = excluedPath + ",/common/**";
		if (StringUtils.isNotEmpty(excluedPath)) {
			excluedPaths = Arrays.asList(excluedPath.split(","));
		}
	}

	public WebSiteMeshFilter() {
		setExcluedPaths();
	}

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// include 请求
		builder.addDecoratorPath("/main", "/decorator/main");
		for (String excluedPath : excluedPaths) {
			builder.addExcludedPath(excluedPath);
		}
	}
}
