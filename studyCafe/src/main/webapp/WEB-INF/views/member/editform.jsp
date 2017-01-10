<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>회원 정보 변경 </title>
	<link rel="Stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" />
<link rel="Stylesheet" href="/studyCafe/resources/bootstrap/js/bootstrap.min.js" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
</head>


<body>
	<c:import url="/WEB-INF/views/include/header.jsp" />
	<div id="pageContainer">
		    <div id="inputmain">
		        <div class="inputsubtitle">회원 정보 변경 </div>
		        <form:form action="mypage.action" method="post" modelAttribute="member"><!-- 상대경로표시 -->
		      
		      <fieldset>
					<legend style=" font-weight: bold"><span class="glyphicon glyphicon-folder-tag"></span>&nbsp;기본 정보</legend>
		            
		            <div class="form-group">
						<label for="inputName">이름 </label>
						<form:input class="form-control" path="name" placeholder="name" readonly />
					</div>
		       </fieldset>   
		            
		          <%--   <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <form:input type="text" path="memberId"
		                    	cssStyle="width:280px;" readonly="true" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<form:password path="password" cssStyle="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호 확인</th>
		                <td>
		                    <input type="password" name="confirm" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>
		                	<form:input type="text" path="email" cssStyle="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>사용자구분</th>
		                <td>
		                	<form:radiobutton path="userType" value="user" />사용자
		                	<form:radiobutton path="userType" value="admin" />관리자
		                </td>
		            </tr>
		            <tr>
		                <th>활성화여부</th>
		                <td>
		                	<form:checkbox path="active" value="true" />활성사용자
		                </td>
		            </tr>		            		            
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="수정" style="height:25px" />
		        	<input type="button" value="취소" style="height:25px"
		        		onclick="location.href='view.action?memberid=${param.memberid}';" />
		        	<!-- location.href='path' : path로 이동하는 javascript 명령 -->
		        </div> --%>
		        </form:form>
		    </div>
		</div>   	

</body>
</html>