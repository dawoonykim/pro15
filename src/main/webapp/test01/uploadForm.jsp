<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/pro15/upload.do" method="post"
		enctype="multipart/form-data">

		파일1 : <input type="file" name="file1">
		파일2 : <input type="file" name="file2">
		<input type="text" name="filewithcontent">
		<input type="submit" value="서버에 파일 올리기">


	</form>


</body>
</html>