package com.hgs.ssm.mvc;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Hello2MVC {
/*********返回json数据*********/
	
	/**
	 * 1.加入jar包
	 * 2.编写目标方法，使其返回JSON对应的对象或集合
	 * 3.在方法上添加@ResponseBody
	 * @return
	 */
	@RequestMapping("testJson")
	@ResponseBody
	public User testJson(){
		return new User("lina","21",new Address("福建","福州"));
	}
	
	/**
	 * 实现文件上传效果（不推荐）
	 * @param body
	 * @return
	 */
	@ResponseBody
	@RequestMapping("testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body){
		//System.out.println(body);
		return "helloWorld"+new Date();
	}
	/******异常界面******/
	@RequestMapping("testHandlerExceptionResolver")
	public String testHandlerExceptionResolver(@RequestParam int i){
		if(i == 10)
			throw new MathException();//将下面的ExceptionHandler注释到，可看到结果
		System.out.println(10/i);
		return "success";
	}
	
	/**
	 * 在方法上java@ResponseStatus 可以让正常页面变成异常页面
	 * @return
	 */
	@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="发生了数学异常，请修复。")
	@RequestMapping("testResponseStatus")
	public String testResponseStatus(){
		return "success";
	}

	/**
	 * 1.在@ExceptionHandler 方法入参中可以加入Exception类型的参数，该参数即对应发生的异常对象
	 * 2.@ExceptionHandler 方法的入参不能传入Map，若要将其传到jsp界面中，需要使用ModelAndView
	 * 3.@ExceptionHandler有方法优先级问题，越接近异常的方法优先级高
	 * 4.这里的只使用本Handler，其他handler无法使用,可以定义一个@ControlAdvice，用于全局Exception的Handler，
	 * 如果当前handler没有对应异常，则会去找全局的，优先级本handler最高。
	 * 5.可以使用
	 * @param ex
	 * @return
	 */
	
	//@ExceptionHandler(value={RuntimeException.class})
	public ModelAndView excepterHandler(Exception ex){
		System.out.println("RuntimeException:"+ex);
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("exception", ex);
		return mav;
	}
	
	/***********文件上传***********/
	/**
	 * 1.加入jar包commons-fileupload 2.在SpringMVC配置文件中配置MultipartFile的bean
	 * 3.参数使用MultipartFile
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		String basePath = request.getSession().getServletContext().getRealPath("/folder/");
		System.out.println(basePath + file.getOriginalFilename());
		System.out.println(file.getOriginalFilename());
		File dest = new File(basePath + file.getOriginalFilename());
		if (!dest.exists()) {
			dest.mkdirs();
		}
		file.transferTo(dest);

		return "success";
	}

	/**
	 * 多文件上传
	 * @param files
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/testFilesUpload")
	public String testFileUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request)
			throws IOException {
		for (MultipartFile file : files) {
			String basePath = request.getSession().getServletContext().getRealPath("/folder/");
			System.out.println(basePath + file.getOriginalFilename());
			System.out.println(file.getOriginalFilename());
			File dest = new File(basePath + file.getOriginalFilename());
			if (!dest.exists()) {
				dest.mkdirs();
			}
			file.transferTo(dest);
		}
		return "success";
	}
	
	
}
