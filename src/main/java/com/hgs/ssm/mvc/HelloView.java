package com.hgs.ssm.mvc;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
/**
 * ×Ô¶¨ÒåView
 * @author hgs
 */

@Component
public class HelloView implements View{

	public String getContentType() {
		return "text/html";
	}

	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.getWriter().print("hello view,time:"+new Date());
	}

}
