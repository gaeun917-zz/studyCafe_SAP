<%@ page import="com.studycafe.model.dto.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.studycafe.model.dao.MemberDao" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>Welcome to StudyCafe</title>

    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/js/bootstrap.min.js"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>

</head>
<body>
    <div id="pageContainer">
        <c:import url="/WEB-INF/views/include/header.jsp"/>
        <br/><br/><br/><br/>
        <div id="content">

            <div style='text-align:center'>
                <div>
                    <section class="col-sm-4">
                        <img class="img-circle" src="/studyCafe/resources/image/teacher1.jpg"
                             height="250px" width="250px" align="middle" alt="Icon"><br/><br/><br/>
                    </section>
                </div>  <br/><br/><br/>
                <div>
                    <section>
                        <c:if test="${ not empty member }">
                            <a style="font-size: 50px">${ member.name } 님 사용자 등록이 완료되었습니다 .</a>
                        </c:if>
                    </section>
                </div>
            </div>  <br/><br/><br/><br/>


            <div class="button" align="center">
                <input id="btnHome" type="button" class="btn btn-info center" value="HOME"
                       onclick="location.href='/studyCafe/home.action';"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input id="btnmypage" type="button"  class="btn btn-info center" value="MY PAGE"
                       onclick="location.href='/studyCafe/member/mypage.action';"/>
            </div>

        </div>
    </div>
</body>
</html>
