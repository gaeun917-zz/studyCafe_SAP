<%@page import="com.studycafe.model.dto.Upload"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.studycafe.model.dao.UploadDao"%>
<%@page import="com.studycafe.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<title>자료 목록</title>
<!-- <meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
</head>

<body>

			<div>
				<%-- <c:import url="/WEB-INF/views/include/header.jsp" /> --%>
				<c:import url="/WEB-INF/views/page/index.jsp" />
				<div class="col-xs-12 col-sm-9" style="text-align: center">
					<div class="panel panel-default">
						<div class="panel-heading"><h4><strong>자료실</strong></h4></div>
							<table class="table">
								<thead>
									<tr>
										<th style="text-align: center">#</th>
										<th style="">제목</th>
										<th style="text-align: center">작성자</th>
										<th style="text-align: center">작성일</th>
									</tr>
								</thead>
				
								<c:forEach var="upload" items="${ requestScope.uploads }">
									<c:choose>
										<c:when test="${ upload.status eq '0' }">
										</c:when>
										<c:otherwise>
											<tr style="height: 30px">
												<td style="text-align: center">${ upload.uploadNo }</td>
												<td style="text-align: left; padding-left: 10px">
													<a href='view.action?uploadno=${ upload.uploadNo }&memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}'> ${ upload.title }</a>
												</td>
												<td>
													<c:forEach var="member" items="${ upload.memberId }">
								                   		${ member.memberId }
								                   	</c:forEach>
												</td>
												<td>${ upload.regDate }</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</table>
							
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-4">
									<br/>
									<button type="button" value="자료등록" class="btn btn-primary btn-sm" 
										onclick="location.href='write.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}';" >
										자료 등록
									</button>
									<!-- [ <a href="/studyCafe/upload/write.action">자료 등록</a> ] -->
								</div>
							</div>
							<hr>
							<ul class="pager">
							    <li><a href="#">이전 </a></li>
							    <li><a href="#">다음 </a></li>
						  	</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	<br/><br/><br/><br/>
</body>
</html>











