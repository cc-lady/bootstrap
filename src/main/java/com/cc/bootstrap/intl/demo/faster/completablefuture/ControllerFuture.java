package com.cc.bootstrap.intl.demo.faster.completablefuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 二：使用CompletableFuture组合式异步编程，提升性能
 * 此方法适用于IO密集型任务，不适用计算密集型任务
 * @author CC
 *
 */
@RestController
@RequestMapping(value="/test-faster3")
public class ControllerFuture {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ControllerFuture.class);

	@Autowired
	private Price3 price3;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;//项目中配置好的线程池
	
	//http://localhost:9011/test-faster3/future
	@ResponseBody
	@RequestMapping(value = "/future", produces={"application/json; charset=UTF-8"})
	public void testMybatis() {
		long startTime = System.currentTimeMillis();

		//同样滴，若不是web项目，需控制线程池关闭
		List<String> products = Arrays.asList("Apple", "Banana", "jo", "foie");
		findPrices(products);//默认执行器
		findPrices2(products);//定制执行器

		long endTime = System.currentTimeMillis();
		LOGGER.info("共用了"+(endTime- startTime)+"毫秒！");
	}

	//3.使用CompletableFuture组合式异步编程，提升性能
	public List<String> findPrices(List<String> products) {
		List<CompletableFuture<String>> priceFutures = products.stream()
				.map(product ->
						CompletableFuture.supplyAsync(() -> product+" price is " + price3.getPriceValue(product)))
				.collect(Collectors.toList());

		//若无返回值，则执行 CompletableFuture.runAsync()

		//注意到这里才进行CompletableFuture::join调用，提升性能
		return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	//3.1 使用更好的方式：定制的执行器（调整线程池大小），这里主要结合线程池配置
	public List<String> findPrices2(List<String> products) {
		List<CompletableFuture<String>> priceFutures = products.stream()
				.map(product ->
						CompletableFuture.supplyAsync(() -> product+" price is " + price3.getPriceValue(product),
								threadPoolTaskExecutor))// 定制线程池
				.collect(Collectors.toList());

		//注意到这里才进行CompletableFuture::join调用，提升性能
		return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}
}
