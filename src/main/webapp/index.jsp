<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
	<a href="SpringMVC/helloMVC">hello ssm</a>
	<br />
	<a href="SpringMVC/paramTest?user=hgs&age=11">paramAndHeadersTest
		ssm (get) 用火狐打开</a>
	<br />
	<form action="SpringMVC/postParamTest" method="post">
		<input type="submit" value="postTest" />
	</form>
	<br />
	<a href="SpringMVC/f/antPath">通配符测试</a>
	<br />
	<a href="SpringMVC/pathVariable/test">pathVariable Test</a>
	<br />
	<a href="SpringMVC/requestParam?username=hgs&password=asd">@RequestParam
		Test</a>
	<br />
	<a href="SpringMVC/requestHeader">@RequestHeader Test</a>
	<br />
	<a href="SpringMVC/cooksValue">@CooksValue Test</a>
	<br />
	<br />

	<form action="SpringMVC/pojoTest" method="post" accept-charset="UTF-8"
		accept="UTF-8">
		name:<input type="text" name="name" /> age:<input type="text"
			name="age" /> city:<input type="text" name="address.city" />
		province:<input type="text" name="address.province"> <input
			type="submit" value="submit" />
	</form>
	<br />
	<a href="SpringMVC/servletAPI">ServletAPI Test</a>
	<br />
	<a href="SpringMVC/modelAndView">ModelAndView Test</a>
	<br />
	<a href="SpringMVC/mapTest">Map Test</a>
	<br />
	<a href="SpringMVC/sessionAttribute">SessionAttribute Test</a>

	<br />
	<a href="SpringMVC/ModelAttributeTest">ModelAttribute Test</a>
	<br />
	<a href="SpringMVC/customView">自定义视图 Test</a>
	<br />
	<a href="SpringMVC/redirectTest">重定向 Test</a>
	
	<form action="SpringMVC/testConverter" method="post">
		<input id="user" type="text" name="user" />
		<input type="submit" value="提交"/>
	</form>
	<!-- 新篇章 -->
	<br/>
	<a href="testJson" id="getJson">获取Json</a>
	<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
		HttpMessageConverter上传文件：<input type="file" name="file"/>
		<input type="submit" value="开始上传"/>
	</form>
	<br/>
	<a href="testHandlerExceptionResolver?i=10">testHandlerExceptionResolver</a>
	<br/>
	<a href="testResponseStatus">testResponseStatus</a>
	<br/>
	<form action="testFileUpload" method="post" enctype="multipart/form-data">
		上传文件：<input type="file" name="file"/>
		<input type="submit" value="开始上传"/>
	</form>
	
	
	<form action="testFilesUpload" method="post" enctype="multipart/form-data">
		多文件上传：<input type="file" name="files"/>
		<input type="file" name="files"/>
		<input type="submit" value="开始上传"/>
	</form>
	
</body>
</html>