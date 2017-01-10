<%@page import="com.studycafe.model.dto.BoardComment"%>
<%@page import="com.studycafe.model.dto.Member"%>
<%@page import="com.studycafe.model.dto.PageBoard"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>

<meta charset="utf-8" />
<title>글쓰기</title>
<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
<link rel="Stylesheet" href="/studyCafe/resources/styles/input2.css" />

<style type="text/css">
th {
 	width: 20%; 
}
</style>
</head>
<body>
	<div class="">
		
		<div id="pageContainer">
			<c:import url="/WEB-INF/views/page/index.jsp" />
			<div class="col-xs-12 col-sm-9" style="text-align: center">
					<%PageBoard board = (PageBoard) request.getAttribute("board");%>
					<%Member member = (Member) request.getAttribute("member");%>
					<div class="panel panel-default">
				      <div class="panel-heading"><h4><strong>${menu.name}</strong></h4></div>
							<table class="table">
								<tr>
									<th style="text-align: center">제목</th>
									<td id="title" style="text-align: left;"><%=board.getTitle()%></td>
								</tr>
								<tr>
									<th style="text-align: center">작성자</th>
									<td style="text-align: left;"><%=member.getMemberId()%></td>
								</tr>
								<tr>
									<th style="text-align: center">작성일</th>
									<td style="text-align: left;"><%=board.getBoarddate()%></td>
								</tr>
								<%-- <tr>
					                <th>조회수</th>
					                <td><%= board.getReadCount() %></td>
					            	</tr> --%>
								<tr>
									<th style="text-align: center; vertical-align: top">내용</th>
									<td style="text-align: left;"><%=board.getContent()%>
									</td>
								</tr>
							</table>
						</div>
						<%-- 아래는 페이지 번호 출력 --%>
						<%-- <%= request.getAttribute("pager").toString() %> --%>
						<hr>
						<div class="buttons">
							<c:choose>
			           		<c:when test="${ sessionScope.loginuser.memberNo eq board.memberNo }">
			           		<div class="row">
				    	<div class="col-md-9"></div>
				    	<div class="col-md-3">
				    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			           			<%-- <button type="button" value="삭제" class="btn btn-primary"
								onclick="location.href='javascript:doDelete(${baord.PBoardNo}, ${pageno})'">
							삭제
						</button> --%>
						<button id="btn" type="button" value="삭제" class="btn btn-primary">
							삭제
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" value="취소" class="btn btn-primary" onclick="location.href='list.action?menuno=${menu.menuNo}&memberpageno=${memberpageno}';" >
							목록
						</button>
						</div>
						</div>
			           		</c:when>
			           		<c:otherwise>
			           		<div class="row">
				    	<div class="col-md-8"></div>
				    	<div class="col-md-4">
			              	<button type="button" value="취소" class="btn btn-primary" onclick="location.href='list.action?menuno=${menu.menuNo}&memberpageno=${memberpageno}';" >
							목록
						</button>
						</div>
						</div>
			           		</c:otherwise>
			           	</c:choose>	

						</div>

				<br /> <br />

				<!------------------ comment 쓰기 영역 시작 -------------------->
			</div>
		</div>
	</div>
</div>
</div>
<br /><br /><br /><br />
<script type="text/javascript">

	 $(document).ready(function () {
		    $('#btn').on('click',function(event){
		    	var title = ${board.PBoardNo};
		    	var yes = confirm(title + ' 번 글을 삭제할까요?');
				if (yes) {
		    	var url = 'delete.action?boardno=' + title + '&pageno=' + ${pageno} + '&menuno=' + ${menu.menuNo} + "&memberpageno=" + ${memberpageno};  
		    	$(location).attr('href', url);
				}
		    	/* location.href="writeform.action?menuno=" + ${menu.menuNo}; */
		    })
	    });
</script>
</body>
</html>