package com.hgs.ssm.mvc;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes(value = {"user"},types={String.class})
@RequestMapping("/SpringMVC")
@Controller
public class HelloMVC {
	private static final String SUCCESS = "success";
	
	@RequestMapping("sessionAttribute")
	public String sessionAttributeTest(Map<String,Object> map) throws IOException{
		map.put("user", new User("hgs",22));
		map.put("log", "hello SessionAttributes");
		return SUCCESS;
	}
	/**
	 * 目标方法可以添加Map类型（世界上也可以是Model类型或ModelMap类型）的参数
	 * @param map
	 * @return
	 */
	@RequestMapping("mapTest")
	public String mapTest(Map<String,Object> map){
		map.put("list", Arrays.asList("a","b","c"));
		return SUCCESS;
	}
	/**
	 * 目标方法的返回值可以是ModelAndView
	 *  类型 其中可以包含视图和模型信息。
	 * SpringMVC 会把ModelAndView的model中数据放入到request域对象中
	 * @param out
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modelAndView")
	public ModelAndView modelAndViewTest() throws IOException {
		//out.write("time: ${requestScope.time}");
		ModelAndView mav = new ModelAndView(SUCCESS);
		// 添加模型数据到ModelAndView中
		mav.addObject("time", new Date());
		return mav;
	}

	/**
	 * 支持原生servletAPI 包括8中
	 * HttpServletRequest、HttpServletResponse、HttpSession、java.security.Principal
	 * Locale InputStream、OutputStream、Reader、Writer
	 * 
	 * @throws IOException
	 *
	 **/
	@RequestMapping("servletAPI")
	public void servletAPITest(HttpServletRequest request, HttpServletResponse response, Writer out)
			throws IOException {
		System.out.println(request + "   " + response);
		out.write("hello I am write");
		//return SUCCESS;
	}

	/**
	 * POJO 支持级联
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/pojoTest")
	public String pojoTest(User user) {
		System.out.println(user);
		return SUCCESS;
	}

	// 获取cookie值
	@RequestMapping(value = "cooksValue")
	public String cookieValueTest(@CookieValue("JSESSIONID") String cookie) {
		System.out.println("JSESSIONID的cookie值：" + cookie);
		return SUCCESS;
	}

	/**
	 * 映射请求头信息
	 * 
	 * @param ae
	 * @return
	 */
	@RequestMapping(value = "requestHeader")
	public String requestHeaderTest(@RequestHeader("Accept-Encoding") String ae) {
		System.out.println("请求头 ---接受的编码：" + ae);
		return SUCCESS;
	}

	/**
	 * 映射请求参数信息
	 * 
	 * @RequestParam 中value是起一个别名，name则是与params中的参数进行绑定。只要别名或参数名与params中的对应上即可
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "requestParam", params = { "username", "password" })
	public String requestParamTest(@RequestParam(value = "username", defaultValue = "admin") String userName,
			@RequestParam(name = "password") String password) {
		System.out.println("用户名：" + userName + "  密码：" + password);
		return SUCCESS;
	}

	/**
	 * @PathVariable 可以将URL中占位符参数绑定到控制器处理方法的入参中
	 * @param id
	 * @return
	 */
	@RequestMapping("/pathVariable/{id}")
	public String pathVariableTest(@PathVariable("id") String id) {
		System.out.println("pathVariableTets id = " + id);
		return SUCCESS;
	}

	/**
	 * 通配符 ，支持三种 ？：匹配文件中的一个字符；*：匹配文件名中的任意字符；**：匹配多层路径
	 */
	@RequestMapping("**/antPath")
	public String antPathTest() {
		return SUCCESS;
	}

	// post方式
	@RequestMapping(value = "postParamTest", method = RequestMethod.POST)
	public String postTest() {
		return SUCCESS;
	}

	// 默认是get and headers方法
	@RequestMapping(value = "/paramTest", params = { "user", "age!=10" }, headers = {
			"Accept-Language=zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3" })
	public String paramTest() {

		return SUCCESS;
	}

	// hello MVC
	@RequestMapping("helloMVC")
	public String hello() {
		return SUCCESS;
	}
}
