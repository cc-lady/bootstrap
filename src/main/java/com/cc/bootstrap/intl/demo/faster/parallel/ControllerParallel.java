package com.cc.bootstrap.intl.demo.faster.parallel;

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
 * 二：使用parallel()并行执行，提升性能
 * 此方法适用于计算密集型任务，不适用IO密集型任务
 * @author CC
 *
 */
@RestController
@RequestMapping(value="/test-faster2")
public class ControllerParallel {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ControllerParallel.class);

	@Autowired
	private Price2 price2;
	
	//http://localhost:9011/test-faster2/parallel
	@ResponseBody
	@RequestMapping(value = "/parallel", produces={"application/json; charset=UTF-8"})
	public void testMybatis() {
		long startTime = System.currentTimeMillis();

		List<String> products = Arrays.asList("Apple", "Banana", "jo", "foie");
		findPrices(products);

		long endTime = System.currentTimeMillis();
		LOGGER.info("共用了"+(endTime- startTime)+"毫秒！");
	}

	//2.使用parallel()并行执行，提升性能
	public List<String> findPrices(List<String> products) {
		return products.stream()
				.parallel() //--------------------并行调用，在不同内核上执行。
				.map(product -> String.format("%s price is %.2f", product, price2.getPriceValue(product)))
				.collect(Collectors.toList());
	}
}
