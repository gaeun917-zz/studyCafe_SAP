<%@page import="com.studycafe.model.dto.UploadFile"%>
<%@page import="com.studycafe.model.dto.Upload"%>
<%@page import="com.studycafe.model.dao.UploadDao"%>
<%@page import="com.studycafe.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<title>자료업로드</title>
<!-- <meta charset="utf-8" />
<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
<link rel="Stylesheet" href="/studyCafe/resources/styles/input.css" /> -->

	<script type="text/javascript">
        function doDelete(uploadNo, pageNo) {
            var yes = confirm('게시글을 삭제할까요?');
            if (yes) {
                location.href = 'delete.action?' +
								'uploadno=' + uploadNo +
								'&memberpageno=' + pageNo;
            }
        }
	</script>
</head>

<body>
<c:import url="/WEB-INF/views/page/index.jsp" />
		<div class="col-xs-12 col-sm-9" style="text-align: center">
			<div id="inputcontent">
				<div class="panel panel-default">

					<div class="panel-heading">
						<h4><strong>자료 상세 정보</strong></h4>
					</div>

					<table class="uploadview_table">
						<thead>
						   <tr>
							   <th>제목</th>
							   <td>${ upload.title }</td>
						   </tr>
						   <tr>
							   <th>작성자</th>
							   <td>
								   <c:forEach var="member" items="${ upload.memberId }">
									   ${ member.memberId }
								   </c:forEach>
							   </td>
						   </tr>
						   <tr>
							   <th>등록일자</th>
							   <td>
							   	${ upload.regDate }
							   </td>
						   </tr>

						   <tr>
							   <th>첨부자료</th>
							   <td>
								   <c:forEach var="file" items="${ upload.files }">
									   <c:if test="${ not empty file.userFileName }">
										   <a href='download.action?uploadfileno=${ file.uploadFileNo }'>
					                         ${ file.userFileName }
					                       </a> &nbsp;
									   </c:if>
								   </c:forEach>
							   </td>
						   </tr>

						   <tr>
							   <th style="vertical-align: top">자료설명</th>
							   <td>
							   	${ upload.content }
							   </td>
						   </tr>
						</thead>
					</table>
				</div>
			</div>

			<div class="buttons">
				<c:choose>
					<c:when test="${ sessionScope.loginuser.memberNo eq upload.memberNo }">
						<button type="button" value="삭제" class="btn btn-primary"
								onclick="location.href='javascript:doDelete(${ upload.uploadNo }, <%= request.getAttribute("memberpageno") %>)'">
							삭제
						</button>
						<button type="button" value="취소" class="btn btn-primary"
								onclick="location.href='list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}';" >
							목록
						</button>
					</c:when>

					<c:otherwise>
						<button type="button" value="취소" class="btn btn-primary"
								onclick="location.href='list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}';" >
							목록
						</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>  <br/><br/><br/><br/>
</body>
</html>