<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>사용자등록</title>
    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/js/bootstrap.min.js"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
    <style type="text/css">
        .error {
            color: red;
            font-weight: bold
        }
    </style>
</head>

<script type="text/javascript">
    /* -- 아이디 인증 기능 -- */
    $(function () {
        var idIsValid = false;

        $('#chkId').on('click', function (event) {
            $('#chkResult').text("");
            $.ajax({
                url: "/studyCafe/account/check-duplicate.action",
                type: "post",
                data: {
                    memberId: $('#memberId').val()
                },
                success: function (data, status, xhr) {
                    if (data == "success") {
                        $('#chkResult').text('사용가능한 아이디입니다.');
                        $('#chkResult').css('color', 'blue');
                        idIsValid = true;
                    } else {
                        $('#chkResult').text(' 존재하는 아이디입니다.');
                        $('#chkResult').css('color', 'red');
                        idIsValid = false;
                    }
                }
            });
        });

        $('#btnBack').on('click', function (event) {
            // location.href='term.action'; 와 같음
            history.back();
        });

        $('#btnRegister').on('click', function (event) {
            if (!idIsValid) {
                alert('invalid id');
                return;
            }
            $('form').submit();
        });
    })
</script>

<body>
<c:import url="/WEB-INF/views/include/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="inputsubtitle">


                <%--POST 이 data를 db에 register할 거임 --%>
            <form:form action="register.action" method="post" modelAttribute="member">
                <fieldset>
                    <legend style=" font-weight: bold">
                        <span class="glyphicon glyphicon-folder-tag"></span>&nbsp;기본 정보
                    </legend>

                    <div class="form-group">
                        <label for="name">이름 </label>
                        <%-- path 는 Member dto의 이름과 같아야함. 거기서 get/set해서 register()의 파라미터로 주입됨  --%>
                        <form:input path="name" class="form-control" placeholder="name"/>
                    </div>

                    <div class="form-group">
                        <label for="memberId">아이디 </label>
                        <form:input path="memberId" class="form-control" placeholder="ID"/>
                        <form:errors path="memberId" cssClass="error" element="div"/>
                        <input type="button" id="chkId" class="btn btn-default pull-left"
                               style="height: 30px " value="아이디 인증"/>&nbsp;&nbsp;&nbsp;
                        <span id="chkResult"></span><%--duplication check message will be placed here--%>
                    </div>

                    <div class="form-group">
                        <label for="email">이메일 </label>
                        <form:input path="email" class="form-control" type="text"
                                    placeholder="ex) abc@gmail.com"/>
                        <form:errors path="email" cssClass="error" element="div"/>
                    </div>

                    <div class="form-group">
                        <label for="passwd">비밀번호 </label>
                        <form:password path="passwd" name="passwd" class="form-control"
                                       placeholder="알파벳, 숫자 조합으로 6~15자리"/>
                        <form:errors path="passwd" cssClass="error" element="div"/>
                    </div>

                    <div class="form-group">
                        <label for="confirmPasswd">비밀번호 확인 </label>
                        <form:password path="confirmPasswd" class="form-control" name="confirmPasswd"/>
                        <form:errors path="confirmPasswd" cssClass="error" element="div"/>
                    </div>
                </fieldset>

                <br/><br/>
                <fieldset>
                    <legend style=" font-weight: bold">
                        <span class="glyphicon glyphicon-folder-tags"></span>&nbsp;상세 정보
                    </legend>

                    <div class="form-group">
                        <label>성별 </label>
                        <%--path, value, label--%>
                        <form:radiobutton path="gender" value="male" checked="checked" label="male"/>&nbsp;&nbsp;
                        <form:radiobutton path="gender" value="female" label="female"/>
                    </div>

                    <div class="form-group">
                        <label for="introduction">자기소개 </label>
                        <form:textarea path="introduction" cols="20" rows="3" class="form-control"
                                       placeholder="간단한 자기소개를 입력해 주세요"/>
                    </div>
                    <br/><br/><br/>

                    <div class="buttons" align="center">
                        <input id="btnBack" class="btn btn-info" type="button" value="뒤로 "/>&nbsp;&nbsp;
                        <input id="btnRegister" class="btn btn-info" type="button" value="등록"/>
                    </div>
                    <br/><br/>
                </fieldset>
            </form:form>



        </div>
    </div>
</div>
</body>
</html>