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
/*********����json����*********/
	
	/**
	 * 1.����jar��
	 * 2.��дĿ�귽����ʹ�䷵��JSON��Ӧ�Ķ���򼯺�
	 * 3.�ڷ��������@ResponseBody
	 * @return
	 */
	@RequestMapping("testJson")
	@ResponseBody
	public User testJson(){
		return new User("lina","21",new Address("����","����"));
	}
	
	/**
	 * ʵ���ļ��ϴ�Ч�������Ƽ���
	 * @param body
	 * @return
	 */
	@ResponseBody
	@RequestMapping("testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body){
		//System.out.println(body);
		return "helloWorld"+new Date();
	}
	/******�쳣����******/
	@RequestMapping("testHandlerExceptionResolver")
	public String testHandlerExceptionResolver(@RequestParam int i){
		if(i == 10)
			throw new MathException();//�������ExceptionHandlerע�͵����ɿ������
		System.out.println(10/i);
		return "success";
	}
	
	/**
	 * �ڷ�����java@ResponseStatus ����������ҳ�����쳣ҳ��
	 * @return
	 */
	@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="��������ѧ�쳣�����޸���")
	@RequestMapping("testResponseStatus")
	public String testResponseStatus(){
		return "success";
	}

	/**
	 * 1.��@ExceptionHandler ��������п��Լ���Exception���͵Ĳ������ò�������Ӧ�������쳣����
	 * 2.@ExceptionHandler ��������β��ܴ���Map����Ҫ���䴫��jsp�����У���Ҫʹ��ModelAndView
	 * 3.@ExceptionHandler�з������ȼ����⣬Խ�ӽ��쳣�ķ������ȼ���
	 * 4.�����ֻʹ�ñ�Handler������handler�޷�ʹ��,���Զ���һ��@ControlAdvice������ȫ��Exception��Handler��
	 * �����ǰhandlerû�ж�Ӧ�쳣�����ȥ��ȫ�ֵģ����ȼ���handler��ߡ�
	 * 5.����ʹ��
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
	
	/***********�ļ��ϴ�***********/
	/**
	 * 1.����jar��commons-fileupload 2.��SpringMVC�����ļ�������MultipartFile��bean
	 * 3.����ʹ��MultipartFile
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
	 * ���ļ��ϴ�
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
