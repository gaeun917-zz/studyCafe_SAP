<%@page import="com.studycafe.model.dto.Calendar"%>
<%@page import="com.studycafe.model.dao.CalendarDao"%>
<%@page import="com.studycafe.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
<title>일정 상세 보여주기</title>
<!-- <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
   <link rel="Stylesheet" href="/studyCafe/resources/styles/input.css" /> -->
   
<style type="text/css">
th {
 	width: 20%; 
}
</style>
</head>
<script type="text/javascript">
function doDelete(calendarNo, pageNo) {
	yes = confirm('게시글을 삭제할까요?');
	if (yes) {
		location.href = 'delete.action?calendarno=' + calendarNo + '&memberpageno=' + pageNo;
	}
}
</script>
<body>
	<div>
		<c:import url="/WEB-INF/views/page/index.jsp" />
		<div class="col-xs-12 col-sm-9" style="text-align: center">
			<div class="panel panel-default">
			      <div class="panel-heading"><h4><strong>일정 상세 정보</strong></h4></div>			
					<table class="table">
						<tr>
							<th style="text-align: center">시작·마감일</th>
							<td style="text-align: left;"><span id=startDate2>${startDate2}</span>&nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;&nbsp;<span id=dueDate2>${dueDate2}</span></td>
						</tr>
						<tr>
							<th style="text-align: center">작성자</th>
							<td style="text-align: left;"><c:forEach var="member" items="${ calendar.memberId }">
				                   		${ member.memberId }	
				                 </c:forEach>
				            </td>
						</tr>
						<tr>
							<th style="text-align: center">제목</th>
							<td style="text-align: left;">${ calendar.title }</td>
						</tr>
						<tr>
							<th style="text-align: center; vertical-align: top" >내용</th>
							<td style="text-align: left;">${ calendar.content }</td>
						</tr>
					</table>
					<div class="buttons">
			           	<c:choose>
			           		<c:when test="${ sessionScope.loginuser.memberNo eq calendar.memberNo }">
			           		<button type="button" value="삭제" class="btn btn-primary"
			           		onclick="location.href='javascript:doDelete(${ calendar.calendarNo }, ${memberpageno})'">
							삭제
							</button>
							<button type="button" value="취소" class="btn btn-primary" onclick="location.href='list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}';" >
							목록
							</button>
			           		</c:when>
			           		<c:otherwise>
			              	<button type="button" value="취소" class="btn btn-primary" onclick="location.href='list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}';" >
							목록
							</button>
			           		</c:otherwise>
			           	</c:choose>	
		              </div>
		              <br/>
				</div>
			</div>
		</div>
	</div>
</div>
<br/><br/><br/><br/>
</body>
</html>