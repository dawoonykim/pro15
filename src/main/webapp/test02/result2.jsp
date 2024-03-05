<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	아래는 고양이 이미지입니다.
	<hr>
	<a href="/pro15/download.do?fileName=cat1.jpg"> 서버에 있는 고양이 내려받기</a>
	<!-- <img alt="" src="/pro15/download.do?fileName=cat1.jpg" width="400" height="200"> -->
	<hr>
	<a href="/pro15/download.do?fileName=판다.jpg"> 서버에 있는 판다 내려받기</a>
	<!-- <img alt="" src="/pro15/download.do?fileName=판다.jpg" width="400" height="200"> -->
	<hr>
	위에는 판다 이미지입니다.
</body>
</html>