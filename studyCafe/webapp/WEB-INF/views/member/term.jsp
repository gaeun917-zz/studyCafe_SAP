<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Terms of Use and Privacy Policy</title>
    <!-- 	<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
        <link rel="Stylesheet" href="/studyCafe/resources/styles/input.css" /> -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="Stylesheet" href="/studyCafe/resources/bootstrap/js/bootstrap.min.js"/>

    <script type="text/javascript">
        // check the term is accepted
        $(function () {
            var accepted = false;

            $('#chkAccept').on('click', function (event) {
                accepted = !accepted; //true
            });

            $('#btnNext').on('click', function (event) {
                // if(accepted)
                if ($('#chkAccept').is(':checked')) {
                    location.href = 'register.action';
                    //accepted, go to <form action="register.action">
                } else {
                    alert('약관에 동의하지 않았습니다.')
                }
            });
            $('#btnBack').on('click', function (event) {
                history.back();
            });

        });
    </script>
</head>


<body>
    <div id="pageContainer">
        <c:import url="/WEB-INF/views/include/header.jsp"/>
        <div id="inputcontent">   <br/><br/>
            <div class="jumbotron">
                <div class="container">
                    <h2>Terms of Use and Privacy Policy</h2> <br/><br/>
                    <p>
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                        Terms of Use and Privacy Policy Terms of Use and Privacy Policy
                    </p> <br/><br/>



                    <form action="register.action" method="GET">
                        <div class="chkterm" align="center">
                            <label class="radio-inline">
                                <input id="chkAccept" type="checkbox" />Agree
                            </label>

                            <label class="radio-inline">
                                <input id="chkDisAgree" type="checkbox" disabled />Disagree
                            </label>
                        </div>

                        <br/><br/><br/>
                        <div class="button" align="center">
                            <input id="btnNext" type="button" class="btn btn-info center" value="등록">등록</input>
                            <input id="btnBack" type="button" class="btn btn-info center" value="취소">취소</input>
                        </div>
                    </form>



                </div>
            </div>
        </div>
    </div>
</body>
</html>