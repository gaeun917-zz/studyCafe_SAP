<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>${member.memberId} 회원 정보 변경 </title>
    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/js/bootstrap.min.js"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>

    <script type="text/javascript">

        $(function () {
            var idIsValid = false;
            $('#btn').on('click', function (event) {
                if (!idIsValid) {
                    alert('invalid id');
                    return;
                }
                $('form').submit();
            });

            $('#btnDelete').on('click', function (event) {
                location.href = "/studyCafe/member/delete.action"; //controller로 이동
            });

            $('#btnChangePasswd').on('click', function (event) {

                var oldPasswd = $('#oldPasswd').val(); //id의 input 값을 받아옴
                var newPasswd = $('#newPasswd').val();
                var confirmPasswd = $('#confirmPasswd').val();

                if (newPasswd != confirmPasswd) {
                    alert('새 비밀번호가 일치하지 않습니다.');
                    return; //여기서 함수 중단 (아래의 Ajax 요청은 수행하지 않습니다.)
                }

                $.ajax({
                    url: "/studyCafe/member/delete.action",
                    type: "post",
                    data: {oldPasswd: oldPasswd, newPasswd: newPasswd},
                    success: function (data, status, xhr) {
                        // controller의 changePw에서 return 값이 success, false임
                        if (data == 'success') {
                            alert('비밀번호를 변경했습니다.');
                        } else {
                            alert('비밀번호 변경 실패')
                        }
                    },
                    error: function (xhr, status, err) {
                        alert('비밀번호 변경 실패')
                    }
                });
            });
        });
    </script>
</head>

<body>
<c:import url="/WEB-INF/views/include/header.jsp"/>
<div class="container">
    <div class="row">
        <div id="container">
            <div class="row">
                <section class="col-xs-12">
                    <br><br> <br><br> <br><br> <br>
                    <!--  기본정보 폼 시작   -->
                    <form:form class="form-horizontal" action="delete.action"
                                method="post" modelAttribute="member">

                    <fieldset> <%--회원 정보 리스트 GET--%>
                        <legend style=" font-weight: bold">
                            <span class="glyphicon glyphicon-home"></span>
                            &nbsp;&nbsp;${member.memberId} 님의 회원 정보
                        </legend>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="inputName">이름</label>
                            <div class="col-sm-10" id="inputName">
                                <p class="form-control-static">${ member.name }</p>
                            </div>
                                <%-- <form:input class="form-control" path="name" placeholder="name" readonly="true" /> --%>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="inputEmail">이메일 </label>
                            <div class="col-sm-10" id="inputEmail">
                                <p class="form-control-static">${ member.email }</p>
                            </div>
                                <%-- <form:input class="form-control" path="name" placeholder="name" readonly="true" /> --%>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="inputRegdate">등록일자 </label>
                            <div class="col-sm-10" id="inputRegdate">
                                <p class="form-control-static">${ member.regDate}</p>
                            </div>
                                <%-- <form:input class="form-control" path="name" placeholder="name" readonly="true" /> --%>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="inputGender">성별</label>
                            <div class="col-sm-10" id="inputGender">
                                <p class="form-control-static">${ member.gender }</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="inputIntroduction">자기소개 </label>
                            <div class="col-sm-10" id="inputIntroduction">
                                <p class="form-control-static">${ member.introduction }</p>
                            </div>
                        </div>
                    </fieldset>
                        <br><br> <br><br>



                    <fieldset> <!--  changing pw 시작 POST  -->
                        <legend style=" font-weight: bold">
                            <span class="glyphicon glyphicon-flash"></span>&nbsp;&nbsp;
                            비밀번호 변경
                        </legend>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>

                            <div class="col-sm-10">
                                <div class="form-group">
                                    <label for="inputName">현재 비밀번호</label>
                                    <input id="oldPasswd" class="form-control" type="password"
                                           name="oldPasswd" placeholder="현재 패스워드"/>
                                    <form:errors path="passwd" cssClass="error" element="div"/>
                                </div>

                                <div class="form-group">
                                    <label for="inputName">새 비밀번호</label>
                                    <input id="newPasswd" class="form-control" type="password"
                                           name="newPasswd" placeholder="새 비밀번호 "/>
                                    <form:errors path="confirmPasswd" cssClass="error" element="div"/>
                                </div>

                                <div class="form-group">
                                    <label for="inputName">새 비밀번호 확인</label>
                                    <input id="confirmPasswd" class="form-control" type="password"
                                           name="confirmPasswd" placeholder="새 비밀번호 확인 "/>
                                    <form:errors path="confirmPasswd" cssClass="error" element="div"/>
                                </div>
                            </div>
                            <br><br><br><br>

                            <div class="button" align="center">
                                <input class="btn btn-info" type="button"
                                       id="btnChangePasswd" value="비밀번호 변경"/>
                            </div>
                            <br><br>
                        </div>

                    </fieldset> <!--  비밀번호 변경 끝  -->


                    <fieldset><!-- deactivate start -->
                        <legend style=" font-weight: bold">
                            <span class="glyphicon glyphicon-flash"></span>&nbsp;&nbsp;
                            회원탈퇴
                        </legend>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <div class="form-group">
                                    <label for="inputName"></label>
                                    <p style="font-size: large;">스터디 카페를 떠나려는 이유를 선택해주세요(필수)</p>
                                    <br><br>
                                    <section>
                                        <form:checkbox path="gender" value="male" checked="checked"
                                                       label=" 학습파트너를 구하기 어려워서요."/><br><br>
                                        <form:checkbox path="gender" value="female"
                                                       label=" 개인정보에 대한 우려가 있어서요."/><br><br>
                                        <form:checkbox path="gender" value="female"
                                                       label=" 이용방법을 잘 모르겠어요."/><br><br>
                                        <form:checkbox path="gender" value="female"
                                                       label=" 자주 방문하지 않아서요."/><br><br>
                                    </section>

                                    <div class="button" align="center">
                                        <input id="btnDelete" class="btn btn-danger"
                                               type="button"  value="탈퇴하기"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    </form:form>
                </section>
            </div>
        </div>
    </div>
</div>
</body>
</html>