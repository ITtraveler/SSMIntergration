package com.hgs.ssm.mvc;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes(value = {"user"},types={String.class})
@RequestMapping("/SpringMVC")
@Controller
public class HelloMVC {
	private static final String SUCCESS = "success";
	
	
	/**
	 * 用@InitBinder标识的方法，可以对WebDataBinder对象进行初始化。
	 * WebDataBinder是DataBinder的子类，用于完成由表单字段到JavaBean属性的绑定
	 * 
	 * @InitBinder标识的方法不能有返回值，必须void
	 * @InitBinder标识的方法参数通常为WebDataBinder
	 * 当设置其，之后发现user再也不能正常显示了
	 * @param binder
	 */
	//@InitBinder
	public void initBind(WebDataBinder binder){
		binder.setDisallowedFields("user");//设置不允许赋值
	}
	
	@RequestMapping("redirectTest")
	public String redirectTest(){
		System.out.println("redirect");
		return "redirect:/index.jsp";
	}
	//自定义视图
	@RequestMapping("/customView")
	public String customViewTest(){
		return "helloView";
	}
	
	/**
	 * 1.有@ModelAttribute 标记的方法，会在每个目标方法执行前被SpringMVC调用
	 * 2.@ModelAttribute 注解也可以来修饰目标方法POJO类型的入参，其value属性值有如下作用：
	 * 1》SpringMVC会使用value属性值在implicitModel中查找对应的对象，若存在则会直接传入到目标方法的入参中。
	 * 2》SpringMVC会以value为key，POJO类型的对象为value，存入到request中。
	 * @param age
	 * @param map
	 */
	@ModelAttribute
	public void modelAttribute(@RequestParam(value="age",required=false,defaultValue="1") int age,Map<String,Object> map){
		if(age > 0){
			User user = new User("zhangsan",age);
			System.out.println("ModelAttribute:"+user);
			map.put("abc", user);
		}
	}
	
	/**
	 * 运行流程：
	 * 1.执行@ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放到了Map中，键为user
	 * 2.SpringMVC 从Map中取出User对象，并把表单的请求参数赋给该User对象的对应属性
	 * 3.SpringMVC把上述对象传入目标方法的参数。
	 * 
	 * NOTE：在@ModelAttribute 修饰的方法中，放入到Map的键需要与目标方法入参类型的第一个字母小写的字符串一致
	 * eg： User   则key为user
	 * 也可以用@ModelAttribute进行声明名字  去map中的key匹配，如abc
	 * @param user
	 * @return
	 */
	@RequestMapping("ModelAttributeTest")
	public String modelAttributeTest(@ModelAttribute("abc") User user){
		System.out.println("user->"+user);
		return SUCCESS;
	}

	@RequestMapping("sessionAttribute")
	public String sessionAttributeTest(Map<String,Object> map) throws IOException{
		map.put("user", new User("hgs",22));
		map.put("log", "hello SessionAttributes");
		System.out.println(map.get("list"));
		return SUCCESS;
	}
	/**
	 * 目标方法可以添加Map类型（事实上也可以是Model类型或ModelMap类型）的参数
	 * @param map
	 * @return
	 */
	@RequestMapping("mapTest")
	public String mapTest(Map<String,Object> map){
		map.put("list", Arrays.asList("a","b","c"));
		System.out.println("mapTest:"+map.get("user"));
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
	public String paramTest(HttpServletRequest request,HttpServletResponse response) {
		String user = (String)request.getParameter("user");
		String age = (String)request.getParameter("age");
		System.out.println(user+"---"+age);
		return SUCCESS;
	}

	// hello MVC
	@RequestMapping("helloMVC")
	public String hello() {
		return SUCCESS;
	}
	
	
	
	
	/************************/
	
	@RequestMapping("/testConverter")
	public String testConverter(@RequestParam("user") User user){
		System.out.println("user: "+user);
		return "success";
	}
}
