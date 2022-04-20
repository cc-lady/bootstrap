package com.cc.bootstrap.page.api;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.common.util.ObjectFactory;
import com.cc.bootstrap.page.enums.UserEnum;
import com.cc.bootstrap.page.dao.UserMapper;
import com.cc.bootstrap.page.service.UserService;
/**
 * 测试代码
 * @author CC
 *
 */
@Controller//不用RestController因为可能会跳转页面
@RequestMapping(value="/test")
public class UserController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserService userService;

	
	//1.项目刚构建完，测试mybatis连接查询 http://localhost:9011/test/testMybatis
	@ResponseBody//不加会返回xml格式，还是json格式方便使用，尤其是前端调用的数据
	@RequestMapping(value = "/testMybatis", produces={"application/json; charset=UTF-8"})
	public List<User> testMybatis() {
		long startTime = System.currentTimeMillis();
		List<User> list = userMapper.findAllUsers();
		long endTime = System.currentTimeMillis();
		LOGGER.info("查询所有用户共用了"+(endTime- startTime)+"毫秒！");
		return list;
		
	}
	
	//2.跳转到cssDemo.jsp页面 http://localhost:9011/test/cssDemo
	@RequestMapping(value = "/cssDemo")
	public ModelAndView cssDemo() {
		long startTime = System.currentTimeMillis();
		List<User> users = userMapper.findAllUsers();
		long endTime = System.currentTimeMillis();
		LOGGER.info("查询所有用户共用了"+(endTime - startTime)+"毫秒！");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cssDemo");
		mav.addObject("list", users);
		return mav;
	}
	
	//3.测试mybatis plus的@MapperScan注解增加，观察@Mapper是否能正常查询。
	/* http://localhost:9011/test/testMybatisPlus
	 * 此方法是使用mybatis plus方式，此时看看1.是否能正确执行。
	 * 结果发现不可以，需要将配置文件的mybatis改为mybatis-plus
	 */
	@ResponseBody
	@RequestMapping(value = "/testMybatisPlus", produces={"application/json; charset=UTF-8"})
	public List<User> testMybatisPlus() {
		long startTime = System.currentTimeMillis();
		List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
		long endTime = System.currentTimeMillis();
		LOGGER.info("查询所有用户共用了"+(endTime- startTime)+"毫秒！");
		return userList;
		
	}
	
	//==============================Mybatis Plus CRUD以及分页功能====================================================
	//4.测试mybatis plus的增加
	/*
	 * POST http://localhost:9011/test/insertUser
	 * {"userName":"xx","password":"123456","mobilePhone":"18701391392","address":"beijing","role":1,"note":"no.2","email":"2212878370@qq.com"}
	 */
	@ResponseBody
	@RequestMapping(value = "/insertUser", produces={"application/json; charset=UTF-8"})
	public ResponseResult<User> insertUser(@RequestBody(required = true) User user) {
		
		//必要系列校验，此处简单写一个
		if(StringUtils.isEmpty(user.getUserName())) {
			return ResponseResult.fail(UserEnum.FAIL_USER);
		}
		
		//add
		try {
			userService.save(user);
		} catch (Exception e) {
			LOGGER.error("用户信息新增失败，用户名称：" + user.getUserName(), e);
			return ResponseResult.fail(UserEnum.FAIL_USER_ADD);
		}
		
		LOGGER.info("用户信息新增成功，用户信息：" + user);
		return ResponseResult.success(UserEnum.SUCCESS_USER, user);
	}

	//5.测试mybatis plus的删除
	/*
	 * DELETE http://localhost:9011/test/deleteUser?id=2
	 */
	@ResponseBody
	@DeleteMapping(value = "/deleteUser", produces={"application/json; charset=UTF-8"})
	public ResponseResult<User> deleteUser(@RequestBody(required = true) @RequestParam(name = "id") Integer userId) {

		//必要系列校验，此处简单写一个
		if(null == userId) {
			return ResponseResult.fail(UserEnum.FAIL_USER);
		}

		//delete
		try {
			userService.removeById(userId);
		} catch (Exception e) {
			LOGGER.error("用户信息删除失败，用户ID：" + userId, e);
			return ResponseResult.fail(UserEnum.FAIL_USER_DEL);
		}

		LOGGER.info("用户信息删除成功，用户ID：" + userId);
		return ResponseResult.success();
	}

	//6.测试mybatis plus的修改
	/*
	 * POST http://localhost:9011/test/updateUser
	 * {"id":1,"userName":"cc","password":"123456","mobilePhone":"18810424140","address":"beijing","role":1,"note":"no.1","email":"chenchen_hardwork@163.com"}
	 */
	@ResponseBody
	@PostMapping(value = "/updateUser", produces={"application/json; charset=UTF-8"})
	public ResponseResult<User> updateUser(@RequestBody(required = true) User user) {

		//必要系列校验，此处简单写一个
		if(null == user.getId()) {
			return ResponseResult.fail(UserEnum.FAIL_USER_ALTER_USERID);
		}

		//update
		try {
			//先根据id查询用户实体
			User user_getById = userService.getById(user.getId());
			//再根据前端传来信息进行修改
			if(StringUtils.isNotEmpty(user.getEmail())){
				user_getById.setEmail(user.getEmail());
			}
			if(StringUtils.isNotEmpty(user.getUserName())){
				user_getById.setUserName(user.getUserName());
			}
			if(StringUtils.isNotEmpty(user.getNote())){
				user_getById.setNote(user.getNote());
			}
			if(StringUtils.isNotEmpty(user.getAddress())){
				user_getById.setAddress(user.getAddress());
			}
			if(StringUtils.isNotEmpty(user.getMobilePhone())){
				user_getById.setMobilePhone(user.getMobilePhone());
			}
			if(StringUtils.isNotEmpty(user.getPassword())){
				user_getById.setPassword(user.getPassword());
			}
			if(null != user.getRole()){
				user_getById.setRole(user.getRole());
			}
			//修改数据byId
			userService.updateById(user_getById);
		} catch (Exception e) {
			LOGGER.error("用户信息修改失败，用户ID：" + user.getId(), e);
			return ResponseResult.fail(UserEnum.FAIL_USER_ALTER, e.getMessage());
		}

		LOGGER.info("用户信息修改成功，用户信息：" + user);
		return ResponseResult.success();
	}

	//7.测试mybatis plus的分页查询，其中查询使用Wrapper条件构造器
	/*
	 * POST http://localhost:9011/test/findPageUser
	 * {"userName":"cc","mobilePhone":"18810424140","address":"beijing","role":1}
	 */
	@ResponseBody
	@PostMapping(value = "/findPageUser", produces={"application/json; charset=UTF-8"})
	public ResponseResult<IPage<User>> findPageUser(Page<User> page, @RequestBody(required = true) User user) {

		//查询条件构造器+
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		if(StringUtils.isNotEmpty(user.getUserName())) {
			queryWrapper.like("user_name", user.getUserName());
		}
		/*if(StringUtils.isNotEmpty(user.getMobilePhone())){
			queryWrapper.lambda().eq( User::getMobilePhone, user.getMobilePhone());//此版本方法写法还不支持lambda表达式，也简单看官网即可。
		}*/
		if(StringUtils.isNotEmpty(user.getAddress())){
			queryWrapper.eq("address", user.getAddress());
		}
		if(null != user.getRole()){
			queryWrapper.eq("role", user.getRole());
		}
		queryWrapper.orderByAsc("user_name");//排序

		//根据条件分页查询user数据
		List<User> userList = userMapper.selectPage(page, queryWrapper).getRecords();
		try {
			//转变为VOList对象返回
			page.setRecords(ObjectFactory.toVOList(userList, User.class));
		} catch (Exception e) {
			LOGGER.error("用户信息查询后转换失败，用户查询条件：" + user, e);
		}
		LOGGER.info("用户信息查询成功，用户信息：" + user);
		return ResponseResult.success(UserEnum.SUCCESS_USER, page);
	}
}
