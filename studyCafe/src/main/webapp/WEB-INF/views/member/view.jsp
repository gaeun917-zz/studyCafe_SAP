<%@page import="com.studycafe.model.dto.Member"%>
<%@page import="com.studycafe.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>사용자 정보</title>
	<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
	<link rel="Stylesheet" href="/studyCafe/resources/styles/input.css" />		
</head>
<body>

	<div id="pageContainer">
		
		<c:import url="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>

		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <%-- ((Member)request.getAttribute("member")).getMemberId() --%>
		                <td>${ requestScope.member.memberId }</td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>${ requestScope["member"].email }</td>
		            </tr>
		            <tr>
		                <th>등록일</th>
		                <td>${ member.regDate }</td>
		            </tr>		            
		          <%--   <tr>
		                <th>사용자구분</th>
		                <td>${ member.userType eq 'uesr' or member.userType eq 'user' ? "사용자" : "관리자" }</td>
		            </tr>
		            <tr>
		                <th>활성화여부</th>
		                <td>${ member.active eq true ? "활성사용자" : "비활성사용자" }</td>
		            </tr> --%>		            		            
		        </table>
		        <div class="buttons">
		        	<a href="list.action">목록</a>
		        	&nbsp;&nbsp;
		        	<a href="edit.action?memberid=${ member.memberId }">정보수정</a>
		        </div>
		    </div>
		</div>   		
		
	</div>

</body>
</html>