<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.studycafe.model.dto.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.studycafe.model.dao.MemberDao" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>Member profile</title>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css"/>
</head>

<body>
<c:import url="/WEB-INF/views/include/header.jsp"/>
<div id="pageContainer">
    <div id="content">
        <br/><br/>
        <div style='text-align:center'>
            [&nbsp;<a href="register.action">사용자 등록</a>&nbsp;]
        </div>
        <br/><br/>

        <table border="1" align="center" width="700px">
            <tr style="height:30px;background-color:orange;">
                <td>아이디</td>
                <td>이름</td>
                <td>이메일</td>
                <td>등록일자</td>
                <td>성별</td>
            </tr>

            <%-- 소포받기 GET! 접속한멤버 정보 받기 ${members}는
                 model.addAttribute("members",members)에서 지정한 id--%>
            <c:forEach var="member" items="${ members }">
                <tr style="height:30px">
                    <td>  <!-- url만들고 parameter 주기: view.action?memberid=..
                               controller에서 memberid주고, member data 받기 -->
                        <c:url var="viewUrl" value= "view.action" >
                            <c:param name="memberid" value="${ member.memberId }"/>
                        </c:url>
                        <a href="${ viewUrl }">${ member.memberId }</a>
                    </td>
                    <td>${ member.name }</td>
                    <td>${ member.email }</td>
                    <td>${ member.regDate }</td>
                    <td>${ member.gender }</td>

            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
