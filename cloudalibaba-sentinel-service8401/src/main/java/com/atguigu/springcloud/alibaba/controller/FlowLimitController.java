package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {
	@GetMapping("/testA")
	public String testA() {
		/*慢调用比例
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//int n = 10 / 0;//异常比例
		return "------testA";
	}

	@GetMapping("/testB")
	public String testB() {
		return "------testB";
	}

	/*
    1.配置热点key规则，只能使用资源名，不能使用url
     */
	@GetMapping("/testHotKey1")
	//Value是资源名
	@SentinelResource(value = "testHotKey2", blockHandler = "deal_testHotKey")
	public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
							 @RequestParam(value = "p2", required = false) String p2) {
		//int age = 10/0;
		return "------testHotKey";
	}

	//兜底方法
	public String deal_testHotKey(String p1, String p2, BlockException exception) {
		return "------deal_testHotKey,o(╥﹏╥)o";
	}
}
