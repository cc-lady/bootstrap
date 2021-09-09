package com.cc.bootstrap.page.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.demo.mockito.MockitoTestService;
import com.cc.bootstrap.page.feign.CustomConfigurationFeign;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cc.bootstrap.BootStrapApplication;
import com.cc.bootstrap.common.schema.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;


@RunWith(SpringJUnit4ClassRunner.class)//引入spring对JUnit4的支持
//@SpringApplicationConfiguration(classes = SpringbootDemoApplication.class) springboot1.4后被替换使用以下
@SpringBootTest(classes = BootStrapApplication.class)//指定springboot的启动类
@WebAppConfiguration//开启web应用的配置，用于模拟ServletContext
public class TestUser {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TestUser.class);
	
	//用于模拟调用Controller的接口发起请求，在@Test定义的hello测试用例中。perferm函数执行一次请求调用，accept用于执行接受的数据类型
	//andExpect用于判断接口返回的期望值
	private MockMvc mvc;
	private String userString;//测试数据
	
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private ObjectMapper objectMapper;//用于解析json格式数据，spring中的bean可以通过@Autowired进行注入获取
	
	@Before//JUnit定义在测试用例@Test内容执行前预加载的内容
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.webAppContextSetup(context).build();//初始化对HelloController的模拟
		//这样写也可以
//		mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
		User user = new User();
		user.setUserName("cc");
		user.setMobilePhone("18810424140");
		user.setAddress("beijing");
		user.setRole(1);
		user.setPassword("1212");
//		body----->{"userName":"cc","mobilePhone":"18810424140","address":"beijing","role":1,"password":"1212"}
		userString = objectMapper.writeValueAsString(user);//初始化测试数据
	}
	
	/**
	 * @Title: testInsertUser
	 * @Description: 测试实例：testInsertUser 测试新增User
	 * @throws Exception
	 * @return: void
	 */
	@Test
	@Transactional
	@Rollback(true)// 事务自动回滚，默认是true。可以不写
	public void testInsertUser() {
		try{
			MvcResult result = this.insertUser();//提取新增功能的MockMvc代码，供其他功能共用
			String responseString = result.getResponse().getContentAsString();
			HashMap<String,Object> map = (HashMap<String,Object>)objectMapper.readValue(responseString, HashMap.class);
			LOGGER.debug(map.toString());
			TestCase.assertEquals("新增用户失败", Boolean.TRUE, (Boolean)map.get("success"));
		}catch (Exception e) {
			LOGGER.error("测试用例：新增User失败！", e);
			TestCase.fail("新增用户失败");
		}
	}

	//调用新增用户，增删改查共用方法
	private MvcResult insertUser() throws Exception{
		MvcResult result = null;
		try{
			result = mvc.perform(
					MockMvcRequestBuilders.post("/test/insertUser")
							.contentType(MediaType.APPLICATION_JSON)
							.content(userString)
			).andExpect(status().isOk()).andReturn();
		}catch (Exception e) {
			LOGGER.debug("新增用户调用失败");
			throw e;
		}
		return result;
	}

	//将字符串转为User对象
	private User convertStringToUser(String userString) throws IOException {
		return objectMapper.readValue(userString, User.class);
	}

	//测试修改用户
	@Test
	@Transactional
	@Rollback(true)// 事务自动回滚，默认是true。可以不写
	public void testUpdateUser() throws Exception{
		//新增用户
		MvcResult userResult = this.insertUser();
		String userResponseString = userResult.getResponse().getContentAsString();
		HashMap<String,Object> userResponseMap = (HashMap<String,Object>)objectMapper.readValue(userResponseString,
				HashMap.class);
		User user = convertStringToUser(objectMapper.writeValueAsString(userResponseMap.get("data")));

		//模拟修改的http请求
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post("/test/updateUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user))
		).andExpect(status().isOk()).andReturn();

		String responseString = result.getResponse().getContentAsString();
		HashMap<String,Object> map = (HashMap<String,Object>)objectMapper.readValue(responseString, HashMap.class);
		LOGGER.debug(map.toString());
		TestCase.assertEquals("修改用户失败", Boolean.TRUE, (Boolean)map.get("success"));
	}

	//测试删除用户
	@Test
	@Transactional
	@Rollback(true)// 事务自动回滚，默认是true。可以不写
	public void testDeleteUser() throws Exception{
		//新增用户
		MvcResult userResult = this.insertUser();
		String userResponseString = userResult.getResponse().getContentAsString();
		HashMap<String,Object> userResponseMap = (HashMap<String,Object>)objectMapper.readValue(userResponseString,
				HashMap.class);
		User user = convertStringToUser(objectMapper.writeValueAsString(userResponseMap.get("data")));

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.delete("/test/deleteUser")
						.contentType(MediaType.APPLICATION_JSON)
						.param("id", String.valueOf(user.getId()))
		).andExpect(status().isOk()).andReturn();

		String responseString = result.getResponse().getContentAsString();
		HashMap<String,Object> map = (HashMap<String,Object>)objectMapper.readValue(responseString, HashMap.class);
		LOGGER.debug(map.toString());
		TestCase.assertEquals("删除用户失败", Boolean.TRUE, (Boolean)map.get("success"));
	}


	/**
	 * @Title: 测试实例：testUserList
	 * @Description: 测试实例：testUserList
	 * @throws Exception
	 * @return: void
	 */
	@Test
	@Transactional
	@Rollback(true)// 事务自动回滚，默认是true。可以不写
	public void testUserList() throws Exception{
		//新增用户
		MvcResult userResult = this.insertUser();
		String userResponseString = userResult.getResponse().getContentAsString();
		HashMap<String,Object> userResponseMap = (HashMap<String,Object>)objectMapper.readValue(userResponseString,
				HashMap.class);
		User user = convertStringToUser(objectMapper.writeValueAsString(userResponseMap.get("data")));

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post("/test/findPageUser")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))
		).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		
		String responseString = result.getResponse().getContentAsString();
		boolean isSuccess = objectMapper.readTree(responseString).get("success").asBoolean();
		TestCase.assertSame("查询用户失败", Boolean.TRUE, isSuccess);
	}


	//不能这样解析，ResponseResult没有无参构造方法
//		ResponseResult<IPage<User>> responseResult = objectMapper.readValue(responseString, ResponseResult.class);
//		LOGGER.debug(responseResult.getSuccess().toString());
//		TestCase.assertEquals("查询用户失败", Boolean.TRUE, responseResult.getSuccess());

//	@Autowired
//	private MockitoTestService mockitoTestService;
//
//	@Test
//	public void testMockitoTestService() {
//		try {
//			Boolean result = mockitoTestService.isExistConfig();
//			LOGGER.info("result = {}", result);
//		} catch (Exception e) {
//			LOGGER.error("测试异常！", e);
//			TestCase.fail("测试用例testMockitoTestService发生异常！");
//		}
//	}


		 /** MockMvc用法：
			* 一、简介
	为何使用MockMvc？
	对模块进行集成测试时，希望能够通过输入URL对Controller进行测试，如果通过启动服务器，建立http client进行测试，这样会使得测试变得很麻烦，比如，启动速度慢，测试验证不方便，依赖网络环境等，所以为了可以对Controller进行测试，我们引入了MockMVC。
	MockMvc实现了对Http请求的模拟，能够直接使用网络的形式，转换到Controller的调用，这样可以使得测试速度快、不依赖网络环境，而且提供了一套验证的工具，这样可以使得请求的验证统一而且很方便。

	二、测试逻辑

	MockMvcBuilder构造MockMvc的构造器；

	mockMvc调用perform，执行一个RequestBuilder请求，调用controller的业务处理逻辑；

	perform返回ResultActions，返回操作结果，通过ResultActions，提供了统一的验证方式；

	使用StatusResultMatchers对请求结果进行验证；

	使用ContentResultMatchers对请求返回的内容进行验证；

	三、MockMvcBuilder
	MockMvc是spring测试下的一个非常好用的类，他们的初始化需要在setUp中进行。
	MockMvcBuilder是用来构造MockMvc的构造器，其主要有两个实现：StandaloneMockMvcBuilder和DefaultMockMvcBuilder，前者继承了后者。
			① MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
			② MockMvcBuilders.standaloneSetup(Object... controllers)：通过参数指定一组控制器，这样就不需要从上下文获取了，比如this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
	这些Builder还提供了其他api，可以自行百度

	四、MockMvcRequestBuilders
	从名字可以看出，RequestBuilder用来构建请求的，其提供了一个方法buildRequest(ServletContext servletContext)用于构建MockHttpServletRequest；其主要有两个子类MockHttpServletRequestBuilder和MockMultipartHttpServletRequestBuilder（如文件上传使用），即用来Mock客户端请求需要的所有数据。
	主要API：
	MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的RequestBuilder，如果在controller的方法中method选择的是RequestMethod.GET，那在controllerTest中对应就要使用MockMvcRequestBuilders.get。
	post(String urlTemplate, Object... urlVariables)：同get类似，但是是POST方法；
	put(String urlTemplate, Object... urlVariables)：同get类似，但是是PUT方法；
	delete(String urlTemplate, Object... urlVariables) ：同get类似，但是是DELETE方法；
	options(String urlTemplate, Object... urlVariables)：同get类似，但是是OPTIONS方法；

	五、ResultActions
		    调用MockMvc.perform(RequestBuilder requestBuilder)后将得到ResultActions，对ResultActions有以下三种处理：

	ResultActions.andExpect：添加执行完成后的断言。添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；

	ResultActions.andDo：添加一个结果处理器，比如此处使用.andDo(MockMvcResultHandlers.print())输出整个响应结果信息，可以在调试的时候使用。

	ResultActions.andReturn：表示执行完成后返回相应的结果

	备注：
	ResultHandler用于对处理的结果进行相应处理的，比如输出整个请求/响应等信息方便调试，Spring mvc测试框架提供了MockMvcResultHandlers静态工厂方法，该工厂提供了ResultHandler print()返回一个输出MvcResult详细信息到控制台的ResultHandler实现

		*/
}