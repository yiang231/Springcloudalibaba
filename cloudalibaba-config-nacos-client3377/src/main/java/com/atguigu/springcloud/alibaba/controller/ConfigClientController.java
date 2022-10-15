package com.atguigu.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   //通过SpringCould原生注解@RefreshScope实现配置自动更新
//Nacos配置中方发生了变化，会实现自动刷新
public class ConfigClientController {
	@Value("${config.info}") //对应nacos配置:nacos-config-client-dev.yaml
	private String configInfo;

	@GetMapping("/config/info")
	public String getConfigInfo() {
		return configInfo;
	}
}
