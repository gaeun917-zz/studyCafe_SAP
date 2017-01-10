<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
	<title>사용자등록</title>

<link rel="Stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" />
<link rel="Stylesheet" href="/studyCafe/resources/bootstrap/js/bootstrap.min.js" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
<style type="text/css">
.error {
	color: red;
	font-weight: bold
}
</style>
</head>

<script type="text/javascript">
/* 아이디 인증 기능  */
$(function() {
	
	var idIsValid = false;
			
	$('#chkId').on('click', function(event) {
		
		$('#chkResult').text("");
		
		$.ajax({
			url: "/studyCafe/account/check-duplicate.action",
			type : "post",
			data : {
				memberId : $('#memberId').val()
			},
			success : function(data, status, xhr) {
				if (data == "success") {
					$('#chkResult').text('사용가능한 아이디입니다.');
					$('#chkResult').css('color', 'blue');
					idIsValid = true;
				} else {
					$('#chkResult').text(' 존재하는 아이디입니다.');
					$('#chkResult').css('color', 'red');
					idIsValid = false;
				}
			},
		});
		
	
	})		
			
	$('#btnBack').on('click', function(event) {
		//location.href='term.action';
		history.back();
	});
	
	$('#btnRegister').on('click', function(event) {
		if (!idIsValid) {
			alert('invalid id');
			return;
		}
		$('form').submit();
	});
})
</script>


<body>
<c:import url="/WEB-INF/views/include/header.jsp" />
	<div class="container">	
		<div class="row">
			<div class="inputsubtitle"></div>

			<form:form action="register.action" method="post" modelAttribute="member">

				<fieldset>
					<legend style=" font-weight: bold"><span class="glyphicon glyphicon-folder-tag"></span>&nbsp;기본 정보</legend>

					<div class="form-group">
						<label for="inputName">이름 </label>
						<form:input class="form-control" path="name" placeholder="name" />
					</div>

					<div class="form-group">
						<label for="inputName">아이디 </label>
						<form:input class="form-control" path="memberId" placeholder="ID" />
						<form:errors path="memberId" cssClass="error" element="div" />						
						<input class="btn btn-default pull-left" style="height: 30px " type="button" id="chkId" value="아이디 인증" /> 
						&nbsp;&nbsp;&nbsp;<span id="chkResult"></span>
					</div>

					<div class="form-group">
						<label for="inputName">비밀번호 </label> 
						<input class="form-control"
							type="password" name="passwd" placeholder="알파벳, 숫자 조합으로 6~15자리" />
						<form:errors path="passwd" cssClass="error" element="div" />
					</div>

					<div class="form-group">
						<label for="inputName">비밀번호 확인 </label> <input
							class="form-control" type="password" name="confirmPasswd" />
						<form:errors path="confirmPasswd" cssClass="error" element="div" />
					</div>

					<div class="form-group">
						<label for="inputName">이메일 </label>
						<form:input class="form-control" type="text" path="email"
							placeholder="ex) abc@gmail.com" />
						<form:errors path="email" cssClass="error" element="div" />
					</div>
				</fieldset>

				<br/><br/>
				<fieldset>
					<legend style=" font-weight: bold"><span class="glyphicon glyphicon-folder-tags"></span>&nbsp;상세 정보 </legend>

					<div class="form-group">
						<label for="inputName">성별   </label>
						&nbsp;&nbsp;&nbsp;<form:radiobutton path="gender" value="male" checked="checked" label="male" />&nbsp;&nbsp;
						<form:radiobutton path="gender" value="female" label="female" />
					</div>

					<div class="form-group">
						<label for="inputComments">자기소개 </label>
						<form:textarea class="form-control" path="introduction" placeholder="간단한 자기소개를 입력해 주세요" />
					</div>
					<br/><br/><br/>
					
					<div class="buttons" align="center">
						<input class="btn btn-info" type="button"
							id="btnBack" value="뒤로 "/>&nbsp;&nbsp;
						<input class="btn btn-info" type="button" id="btnRegister"
							value="등록"  />
					</div>
				<br/><br/>

				</fieldset>

			</form:form>
		</div>
	</div>

</body>
</html>