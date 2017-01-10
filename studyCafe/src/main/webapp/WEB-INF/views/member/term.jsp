<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>Terms of Use and Privacy Policy</title>
<!-- 	<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
	<link rel="Stylesheet" href="/studyCafe/resources/styles/input.css" /> -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
	<link rel="Stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" />
	<link rel="Stylesheet" href="/studyCafe/resources/bootstrap/js/bootstrap.min.js" />
</head>


<script type="text/javascript">
// 약관 동의에 체크 해야 넘어가는 기능 
	$(function() {
	
		var accepted = false;
	
			$('#chkAccept').on('click', function(event) {
				accepted = !accepted;
			});
			
			$('#btnNext').on('click', function(event) {				
				//if (accepted) {
				if($('#chkAccept').is(':checked')) {
					location.href ='register.action';
				} else {
					alert('약관에 동의하지 않았습니다.')
				}
			});
			});
	
</script>

<body>

	<div id="pageContainer">	
		<c:import url="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div class="jumbotron">
		        <div class="container">
		        <h2>Terms of Use and Privacy Policy</h2>
		         <br/><br/>
		        <!-- term에 동의하면 register.action으로 넘어감  -->
		        <form action="register.action" method="GET"/><!-- 상대경로표시 -->

		        	 <p> 약관 내용
		        	 Terms of Use and Privacy PolicyTerms of Use and Privacy 
		        	 Terms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy Policy
		        	 Terms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy Policy
		        	 Terms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy Policy
		        	 Terms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy Policy
		        	 Terms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy PolicyTerms of Use and Privacy Policy
		        	 PolicyTerms of Use and Privacy Policy
		        	 					 </p>
		         <br/><br/>
			    <div class="chkterm" align="center">
			        <label class="radio-inline">
			        	<input type="checkbox" id="chkAccept"/>약관에 동의함
			        </label>
			        
			        <label class="radio-inline">
			            <input type="checkbox" disabled/>약관에 동의하지 않음  
			        </label>
			    </div>
			    
			    <br/><br/><br/>    
			    <div class="button" align="center">
			        <button type="button" id="btnNext" class="btn btn-info center" value="등록" >등록</button>
			       <button type="button" class="btn btn-info center" value="취소">취소</button>
		        </div>
		        	<!-- location.href='path' : path로 이동하는 javascript 명령 -->
		        </div>		  
		    </div>
		</div>   	
	
	</div>

</body>
</html>