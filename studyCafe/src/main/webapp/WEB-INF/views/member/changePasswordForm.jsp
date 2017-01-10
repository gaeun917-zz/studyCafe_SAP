<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>


<!--새로운 비번을 저장하는 게 아니라, update하는 것임! -->
<!-- 마이페이지의 비밀번호 변경 탭 -> 비밀번호 변경  -->

 
	<title>비밀번호 변경 </title>
</head>
<body>
	
	<form:form action="edit.action" method="post" modelAttribute="member">
	<p>
		<label>현재 비밀번호 :<br>
		<form:password path="password" />
		<form:errors path="password"/>
		</label>
	</p>
	<p>
		<label>새 비밀번호 :<br>
		<form:password path="password" />
		<form:errors path="password"/>
		</label>
	</p>
	<input type="submit" value="비밀번호 변경 " />">
	</form:form>
	
	
	
</body>
</html>