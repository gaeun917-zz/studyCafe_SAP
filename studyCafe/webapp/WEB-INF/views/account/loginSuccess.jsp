<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ----------------------------------------------------------------
Below we include the Login Button social plugin.
This button uses the JavaScript SDK to present a graphical Login button
that triggers the FB.login() function when clicked.
-------------------------------------------------------------------- -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>로그인 성공 </title>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css"/>
    <%--<link rel="Stylesheet" href="/teamfive/resources/styles/input.css" />--%>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"/>
</head>

<body>
<div id="pageContainer">
    <c:import url="/WEB-INF/views/include/header.jsp"/>
    <div id="inputcontent">
        <br/><br/>
        <div id="inputmain">
            <!-- 세션에 저장한 페이스북 유저는 name. 이고, 일반 로그인 유저는 member  -->
            <c:choose>
                <c:when test="${ not empty sessionScope.loginuser }">
                    <a href="#">${ loginuser.name }님 환영합니다.</a>
                    <a href="/studyCafe/account/logout.action" id="logout">로그아웃</a>
                </c:when>

                <c:when test="${ not empty sessionScope.facebookLoginuser}">
                    <a href="#">${ facebookLoginuser }님 환영합니다.</a>
                    <a href="/studyCafe/account/logout.action" id="logout">로그아웃</a>
                </c:when>

                <c:otherwise>
                    <a href="#" style="font-weight: bold;" data-toggle="modal" data-target="#myModal">로그인</a>
                    <a href="/studyCafe/member/registerform2.action" style=" font-weight: bold;">회원가입</a>
                </c:otherwise>
            </c:choose>


            <div class="inputsubtitle" style='text-align:center'>${ member.name } 님, 로그인 되셨습니다!</div>
            <br/><br/>
                <a href="/tudyCafe/account/home.action" id="logout">홈으로</a>

            <form action="/studyCafe/account/login.action" method="post">
                <!-- <fb:login-button scope="public_profile,email"
                    onlogin="checkLoginState();" autologoutlink="true">
                </fb:login-button> -->
            </form>

        </div> <div id="status"></div>
    </div>
</div>
</div>
</body>
</html>
