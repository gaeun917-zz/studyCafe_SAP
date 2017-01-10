<%@page import="com.studycafe.model.dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.studycafe.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
    <title></title>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
</head>
<body>

    <div id="pageContainer">

    	<c:import url="/WEB-INF/views/include/header.jsp" />
        
        <div id="content">
        	<br /><br />
        	<div style='text-align:center'>
        		[&nbsp;<a href="register.action">사용자 등록</a>&nbsp;]
        	</div>
        	<br /><br />        	
        	<table border="1" align="center" width="700px">
        		<tr style="height:30px;background-color:orange">
        			<td>아이디</td>
        			<td>이름</td>
        			<!-- <td>패스워드</td> -->
        			<td>이메일</td>
        			<td>등록일자</td>
        			<td>성별</td>
        			<!-- <td>페북아이디</td>
        			<td>카카오아이디</td>
        			<td>상태</td>
        			<td>등급</td> -->
        		</tr>        	
        	<c:forEach var="member" items="${ members }">
        		<tr style="height:30px">
        			<td>
        				<!-- view.action?memberid=...... 경로를 만들고 변수에 저장 -->
        				<c:url value="view.action" var="viewUrl">
        					<c:param name="memberid" value="${ member.memberId }" />
        				</c:url>
        				<a href="${ viewUrl }">${ member.memberId }</a>
        			</td>
        			<td>${ member.name }</td>
        			<%-- <td>${ member.passwd }</td> --%>
        			<td>${ member.email }</td>
        			<td>${ member.regDate }</td>
        			<td>${ member.gender }</td>
        			<%-- <td>${ member.facebookId }</td>
        			<td>${ member.kakaoId }</td>
        			<td>${ member.status }</td>
        			<td>${ member.gradeLevel }</td> --%>
        		</tr>
        	</c:forEach>
        	</table>
        	
        </div>
    </div>
    
</body>
</html>
