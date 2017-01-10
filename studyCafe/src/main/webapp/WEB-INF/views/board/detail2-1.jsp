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

<meta charset="utf-8" />
<title>글쓰기</title>
<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
<link rel="Stylesheet" href="/studyCafe/resources/styles/input2.css" />

<script type="text/javascript">
	function doDelete(boardNo) {
		yes = confirm(boardNo + '번 글을 삭제할까요?');
		if (yes) {
			location.href = 'delete.action?boardno=' + boardNo;

		}
	}
	function register_Confirm() {
		$("#register").click(function() {
			alert("참가신청 되었습니다.!!");
		});
	}
</script>

</head>
<body>

	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">
			<div id="inputcontent">
				<div id="inputmain">
					<div class="inputsubtitle">게시판 글 내용</div>
					<table>
						<%-- 	<tr>
							<th>BOARD_NO</th>
							<td>${board.boardNo}</td>
						</tr> --%>
						<tr>
							<th>TITLE</th>
							<td>${ board.title }</td>
						</tr>
						<tr>
							<th>개설자</th>
							<td><c:forEach var="member" items="${ board.memberId }">
							${member.memberId}
							</c:forEach></td>
						</tr>
						<tr>
							<th>Category</th>
							<td><c:forEach var="categoryBoard"
									items="${ board.smallCategoryName }">
							${categoryBoard.smallCategoryName}
							</c:forEach></td>
						</tr>
						<tr>
							<th>CLOSEDATE</th>
							<td>${board.closeDate}</td>
						</tr>
						<tr>
							<th>STARTDATE</th>
							<td>${board.startDate}</td>
						</tr>
						<tr>
							<th>MEMBERCOUNT</th>
							<td>${board.memberCount}</td>
						</tr>
						<tr>
							<th>STATUS</th>
							<td>${board.status}</td>
						</tr>
						<tr>
							<th>PLACE</th>
							<td>${board.place}</td>
						</tr>
						<tr>
							<th>PURPOSE</th>
							<td>${board.purpose}</td>
						</tr>
						<tr>
							<th>PERIOD</th>
							<td>${board.period}</td>
						</tr>
						<tr>
							<th>FREQUENCY</th>
							<td>${board.frequency}</td>
						</tr>
				<%-- 		<tr>
							<th>이미지</th>
							<td>${board.images}</td>
						</tr> --%>
						<tr>
							<th>CONTENT</th>
							<td>${board.content}</td>
						</tr>

					</table>
				</div>
			</div>
			<br /> <br />

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
					<c:otherwise>
					
					<button id="register" type="button" value="스터디참여" class="btn btn-primary"
						onclick="alert('참여 신청되었습니다.');">스터디참여</button>
						<button type="button" value="취소" class="btn btn-primary"
							onclick="location.href='list.action';">목록보기</button>
					
					</c:otherwise>
				</c:choose>
			</div>.
		</div>
	</div>

</body>
</html>