<%@page import="com.studycafe.model.dto.SmallCategory"%>
<%@page import="com.studycafe.model.dto.BoardComment"%>
<%@page import="com.studycafe.model.dto.Member"%>
<%@page import="com.studycafe.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- **jstl "core" tag library를 c접두사로 사용하는 설정 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- **functions : el에서 사용할 수 있는 함수를 포함하는 태그집합 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>


<html>
<head>
<title>글쓰기</title>
<meta charset="utf-8" />

<!-- Bootstrap Core CSS -->
<link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="/studyCafe/resources/bootstrap/css/small-business.css"
	rel="stylesheet">
<link rel="Stylesheet" href="/studyCafe/resources/styles/list.css" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js" charset="utf-8"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="/studyCafe/resources/bootstrap/js/bootstrap-select.min.js"></script>
<script type="text/javascript">
	function doDelete(boardNo) {
		yes = confirm(boardNo + '번 모집을 마감합니까?');
		if (yes) {
			location.href = 'delete.action?boardno=' + boardNo;

		}
	}
	/* function register_Confirm() {
		$("#register").click(function() {
			alert("참가신청 되었습니다.!!");
		});
	} */
</script>

</head>
<body style="background: gray(230, 126, 34)">

<c:import url="/WEB-INF/views/include/header.jsp" />

<div id="pageContainer">
		<div style="padding-top: 100px; text-align: center">

	<!-- Page Content -->
	<div class="container">
		<!-- Heading Row -->
		<div class="row">
			<div class="col-md-8">
				<img class="img-responsive img-rounded"
					src="/studyCafe/resources/boardimage/${board.boardNo}.jpg"
										alt="" />
			</div>
			<!-- /.col-md-8 -->
			<div class="col-md-4" style="text-align: left; ">
				<h1 style="margin-bottom: 30px">${ board.title }</h1>
				<h4>모임 정보</h4>
					<ul style="list-style: none; margin: 0; padding: 0;">
						<li><strong>개설자</strong> <span style="margin-left: 130px"><c:forEach
												var="member" items="${ board.memberId }">				
											${ member.memberId }
									</c:forEach></span></li></br>
						<li><strong>카테고리</strong><span style="margin-left: 110px"><c:forEach var="categoryBoard"
									items="${ board.smallCategoryName }">
							${categoryBoard.smallCategoryName}
							</c:forEach></span></li></br>				
						<li><strong>목표</strong><span style="margin-left: 130px">${ board.purpose }</span></li></br>
						<li><strong>장소</strong><span style="margin-left: 140px">${ board.place }</span></li></br>
						<li><strong>기간</strong><span style="margin-left: 150px">${ board.period }</span></li></br>
						<li><strong>횟수</strong><span style="margin-left: 150px">${ board.frequency }</span></li></br>
						<li><strong>모집 시작일</strong> <span style="margin-left: 80px">${ board.startDate }</span></li></br>
						<li><strong>모집 마감일</strong> <span style="margin-left: 80px">${ board.closeDate }</span></li></br>
						<li><strong>모집 인원 수</strong> <span id="members" style="margin-left: 100px">${membercount} / ${board.memberCount} 명</span></li></br>
					
					</ul>
			
			</div>
			<!-- /.col-md-4 -->
		</div>

		<div class="col-md-4" style="width: 950px; height: 350px;">
			<h2>소개&하고 싶은 말</h2>
			<p>${board.content }</p>
			<div class="buttons">
				<c:choose>
					<c:when
						test="${ sessionScope.loginuser.memberNo eq board.memberNo }">
						<button type="button" value="삭제" class="btn btn-primary"
							onclick="location.href='javascript:doDelete(${ board.boardNo })'">
							모집 마감</button>
						<button type="button" value="취소" class="btn btn-primary"
							onclick="location.href='list.action';">목록보기</button>
					</c:when>
					<c:when test="${not empty swich}"> 
						<button type="button" value="취소" class="btn btn-primary"
							onclick="location.href='list.action';">목록보기</button>
					</c:when>
					<c:otherwise>
					<div>
						<div class="col-md-5"></div>
						<div id="register" class="col-md-1">
							<button type="button" value="스터디참여"
								class="btn btn-primary">스터디참여</button>
						</div>
						<div class="col-md-3">
							<button type="button" value="취소" class="btn btn-primary"
								onclick="location.href='list.action';">목록보기</button>
						</div>
					</div>
					</c:otherwise>
				</c:choose>
			</div>
			
			
			<script type="text/javascript">
			$('#register').on('click', function(event) {
				var membercount = ${membercount};
				var boardno = ${board.boardNo};
				$.ajax({
					url : '/studyCafe/board/enjoy.action',
					type : 'get',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
					data : { count : membercount,
							 boardno : boardno
						},
					success : function(data, status, xhr) {
						showResult(data);
					}
				})
			})
			function showResult(data) {
				if (data.length == 0)
					return;
				$('#register').css('display', 'none');
				alert("참가신청 되었습니다.!!");
				$('#members').html(data + '/ ${board.memberCount} 명');
			}
			</script>
			</div>
			</div>
			</div>
			</div>
			
			<!-- /.row -->
</body>
</html>


