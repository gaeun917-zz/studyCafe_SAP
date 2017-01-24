<%@page import="com.studycafe.model.dto.Board" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <title>달력 보여주기</title>
    <!--
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%--bootstrap--%>
    <link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/studyCafe/resources/bootstrap/js/bootstrap.min.js"></script>

    <%--Jquery--%>
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- <script src='/studyCafe/resources/js/jquery.min.js'></script> -->

    <%--calendar api--%>
    <link href='/studyCafe/resources/styles/fullcalendar.css' rel='stylesheet' />
    <link href='/studyCafe/resources/styles/fullcalendar.print.css'	rel='stylesheet' media='print' />
    <script src='/studyCafe/resources/js/moment.min.js'></script>
    <script src='/studyCafe/resources/js/fullcalendar.min.js'></script>
    <script>
        $(document).ready(function () {
            <%-- alert(JSON.stringify(<%= request.getAttribute("calendarJson") %>)); --%>
            $('#calendar').fullCalendar({
                    defaultDate: '2016-06-13',
                    editable: true,
                    eventLimit: true, // allow "more" link when too many events
                    events: <%= request.getAttribute("calendarJson") %>
            });

            $('#addSchedule').on('click', function (event) {
                    location.href = '/studyCafe/calendar/writeform.action?memberpageno=' + ${memberpageno} +'&noticemenu=' + ${noticemenu.menuNo};
            });
        });
        <%--'${ calendar.calendarNo }' --%>
        <%-- $('.fc-content').on('click', function(event) {
            location.href = '/studyCafe/calendar/view.action?calendarno=' + ${calendar.calendarNo};
        })<%= request.getAttribute("c2") %> --%>
    </script>

    <style>
        body {
            margin: 40px 10px;
            padding: 0;
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }
    </style>

</head>




<body>
<c:import url="/WEB-INF/views/page/index.jsp"/>
<div>
    <div class="col-xs-12 col-sm-9" style="text-align: center">
        <button id="addSchedule" type="button" class="btn btn-danger">+
            일정 추가하기
        </button>
        <div id='calendar'></div>
    </div>
</div>
<br/><br/><br/><br/>
</body>
</html>