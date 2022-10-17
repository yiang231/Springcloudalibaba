package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.alibaba.handler.MyHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
	/*
	1.使用路径作为规则名，如果违反规则，返回Sentinel规定的默认字符串，Blocked by Sentinel (flow limiting)
	2.使用资源名作为规则名，如果违反规则，返回blockHandler = "byResourceHandler"的指定的方法返回值，属于用户自定义信息
	3.建议使用资源名作为规则的名称，返回信息可以自定义
	4.所有的规则都可以使用资源名作为规则名称，其中热点key只能使用资源名（热点key不可以使用路径作为规则名）
	5.哪怕是使用资源名做规则名，对于出现的异常，blockHandler也是无能为力。
	  如果希望对出现的异常进行自定义响应信息，使用fallback属性
	6.目前blockHandler和fallback的处理方法和正常的业务在一起，可以分开
	 */
	@RequestMapping("/byUrl")
	@SentinelResource(value = "byResource",
			blockHandlerClass = MyHandler.class,
			blockHandler = "byResourceHandler",
			fallbackClass = MyHandler.class,
			fallback = "fallBackHandler")
	public String byResource(@RequestParam(value = "name", required = false) String str,
							 @RequestParam(value = "age", required = false) Integer i) {
		int n = 10 / 0;
		return "name=" + str + ",age=" + i;
	}

	/*//兜底方法
	//1.返回值类型相同
	//2.参数相同，并且最后一个参数必须是BlockException
	//违反规则时调用
	public String byResourceHandler(String str, Integer i, BlockException blockException) {
		return "block " + str + "  " + i + "  " + blockException;
	}

	//1.返回值类型相同
	//2.参数相同，并且最后一个参数必须是Throwable
	//出现异常时调用
	public String fallBackHandler(String str, Integer i, Throwable throwable) {
		return "fallback " + str + "  " + i + "  " + throwable;
	}*/
}
