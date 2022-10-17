package com.atguigu.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 1.方法使用static修饰
 * <p>
 * 2.@SentinelResource指定blockHandler和fallback方法所属的类
 */
public class MyHandler {
	//兜底方法
	//1.返回值类型相同
	//2.参数相同，并且最后一个参数必须是BlockException
	//违反规则时调用
	public static String byResourceHandler(String str, Integer i, BlockException blockException) {
		return "block " + str + "  " + i + "  " + blockException + "!!!!!";
	}

	//1.返回值类型相同
	//2.参数相同，并且最后一个参数必须是Throwable
	//出现异常时调用
	public static String fallBackHandler(String str, Integer i, Throwable throwable) {
		return "fallback " + str + "  " + i + "  " + throwable + "?????";
	}
}
