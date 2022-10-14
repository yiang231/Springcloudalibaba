package com.atguigu.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {
	@Resource
	private RestTemplate restTemplate;

	@Value("${service-url.nacos-user-service}")
	private String serverURL;

	@GetMapping(value = "/consumer/payment/nacos/{id}")
	public String paymentInfo(@PathVariable("id") Long id) {
		//http://nacos-payment-provider/payment/nacos/13 负载均衡
		//http://localhost:9001/payment/nacos/13 没有负载均衡
		return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, String.class);
	}
}
