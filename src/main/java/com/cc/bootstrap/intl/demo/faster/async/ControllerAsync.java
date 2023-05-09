package com.cc.bootstrap.intl.demo.faster.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一：使用@Async("asyncThreadPool")异步调用方式，提升性能
 * @author CC
 *
 */
@RestController
@RequestMapping(value="/test-faster1")
public class ControllerAsync {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ControllerAsync.class);

	@Autowired
	private Price price;
	
	//http://localhost:9011/test-faster1/async
	@ResponseBody
	@RequestMapping(value = "/async", produces={"application/json; charset=UTF-8"})
	public void testMybatis() {
		long startTime = System.currentTimeMillis();

		//若不是web项目，需注意所有线程都执行完才可以关闭线程池，控制所有线程都执行完可以使用CountDownLatch
		//CountDownLatch countDownLatch = new CountDownLatch(size); -- 这里是products.size
		//每个getPriceValue方法内部最后finally标记执行完毕：countDownLatch.countDown();
		//最后findPrices(products);执行完毕后阻塞控制所有执行完毕：countDownLatch.await();
		//然后关闭线程池
		List<String> products = Arrays.asList("Apple", "Banana", "jo", "foie");
		findPrices(products);

		long endTime = System.currentTimeMillis();
		LOGGER.info("共用了"+(endTime- startTime)+"毫秒！");
	}

	//1.使用@Async("asyncThreadPool")异步调用方式，提升性能
	public List<String> findPrices(List<String> products) {
		return products.stream()
				.map(product -> String.format("%s price is %.2f", product, price.getPriceValue(product)))
				.collect(Collectors.toList());
	}
}
