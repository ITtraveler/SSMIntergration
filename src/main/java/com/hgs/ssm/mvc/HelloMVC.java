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
	 * ��@InitBinder��ʶ�ķ��������Զ�WebDataBinder������г�ʼ����
	 * WebDataBinder��DataBinder�����࣬��������ɱ��ֶε�JavaBean���Եİ�
	 * 
	 * @InitBinder��ʶ�ķ��������з���ֵ������void
	 * @InitBinder��ʶ�ķ�������ͨ��ΪWebDataBinder
	 * �������䣬֮����user��Ҳ����������ʾ��
	 * @param binder
	 */
	//@InitBinder
	public void initBind(WebDataBinder binder){
		binder.setDisallowedFields("user");//���ò�����ֵ
	}
	
	@RequestMapping("redirectTest")
	public String redirectTest(){
		System.out.println("redirect");
		return "redirect:/index.jsp";
	}
	//�Զ�����ͼ
	@RequestMapping("/customView")
	public String customViewTest(){
		return "helloView";
	}
	
	/**
	 * 1.��@ModelAttribute ��ǵķ���������ÿ��Ŀ�귽��ִ��ǰ��SpringMVC����
	 * 2.@ModelAttribute ע��Ҳ����������Ŀ�귽��POJO���͵���Σ���value����ֵ���������ã�
	 * 1��SpringMVC��ʹ��value����ֵ��implicitModel�в��Ҷ�Ӧ�Ķ������������ֱ�Ӵ��뵽Ŀ�귽��������С�
	 * 2��SpringMVC����valueΪkey��POJO���͵Ķ���Ϊvalue�����뵽request�С�
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
	 * �������̣�
	 * 1.ִ��@ModelAttribute ע�����εķ����������ݿ���ȡ�����󣬰Ѷ���ŵ���Map�У���Ϊuser
	 * 2.SpringMVC ��Map��ȡ��User���󣬲��ѱ����������������User����Ķ�Ӧ����
	 * 3.SpringMVC������������Ŀ�귽���Ĳ�����
	 * 
	 * NOTE����@ModelAttribute ���εķ����У����뵽Map�ļ���Ҫ��Ŀ�귽��������͵ĵ�һ����ĸСд���ַ���һ��
	 * eg�� User   ��keyΪuser
	 * Ҳ������@ModelAttribute������������  ȥmap�е�keyƥ�䣬��abc
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
	 * Ŀ�귽���������Map���ͣ���ʵ��Ҳ������Model���ͻ�ModelMap���ͣ��Ĳ���
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
	 * Ŀ�귽���ķ���ֵ������ModelAndView
	 *  ���� ���п��԰�����ͼ��ģ����Ϣ��
	 * SpringMVC ���ModelAndView��model�����ݷ��뵽request�������
	 * @param out
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modelAndView")
	public ModelAndView modelAndViewTest() throws IOException {
		//out.write("time: ${requestScope.time}");
		ModelAndView mav = new ModelAndView(SUCCESS);
		// ���ģ�����ݵ�ModelAndView��
		mav.addObject("time", new Date());
		return mav;
	}

	/**
	 * ֧��ԭ��servletAPI ����8��
	 * HttpServletRequest��HttpServletResponse��HttpSession��java.security.Principal
	 * Locale InputStream��OutputStream��Reader��Writer
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
	 * POJO ֧�ּ���
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/pojoTest")
	public String pojoTest(User user) {
		System.out.println(user);
		return SUCCESS;
	}

	// ��ȡcookieֵ
	@RequestMapping(value = "cooksValue")
	public String cookieValueTest(@CookieValue("JSESSIONID") String cookie) {
		System.out.println("JSESSIONID��cookieֵ��" + cookie);
		return SUCCESS;
	}

	/**
	 * ӳ������ͷ��Ϣ
	 * 
	 * @param ae
	 * @return
	 */
	@RequestMapping(value = "requestHeader")
	public String requestHeaderTest(@RequestHeader("Accept-Encoding") String ae) {
		System.out.println("����ͷ ---���ܵı��룺" + ae);
		return SUCCESS;
	}

	/**
	 * ӳ�����������Ϣ
	 * 
	 * @RequestParam ��value����һ��������name������params�еĲ������а󶨡�ֻҪ�������������params�еĶ�Ӧ�ϼ���
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "requestParam", params = { "username", "password" })
	public String requestParamTest(@RequestParam(value = "username", defaultValue = "admin") String userName,
			@RequestParam(name = "password") String password) {
		System.out.println("�û�����" + userName + "  ���룺" + password);
		return SUCCESS;
	}

	/**
	 * @PathVariable ���Խ�URL��ռλ�������󶨵��������������������
	 * @param id
	 * @return
	 */
	@RequestMapping("/pathVariable/{id}")
	public String pathVariableTest(@PathVariable("id") String id) {
		System.out.println("pathVariableTets id = " + id);
		return SUCCESS;
	}

	/**
	 * ͨ��� ��֧������ ����ƥ���ļ��е�һ���ַ���*��ƥ���ļ����е������ַ���**��ƥ����·��
	 */
	@RequestMapping("**/antPath")
	public String antPathTest() {
		return SUCCESS;
	}

	// post��ʽ
	@RequestMapping(value = "postParamTest", method = RequestMethod.POST)
	public String postTest() {
		return SUCCESS;
	}

	// Ĭ����get and headers����
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
