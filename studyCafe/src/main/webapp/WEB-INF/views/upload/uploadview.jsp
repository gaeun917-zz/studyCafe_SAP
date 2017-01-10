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

<style type="text/css">
th {
 	width: 20%; 
}
</style>
</head>

<script type="text/javascript">
function doDelete(uploadNo, pageNo) {
	yes = confirm('게시글을 삭제할까요?');
	if (yes) {
		location.href = 'delete.action?uploadno=' + uploadNo + '&memberpageno=' + pageNo;
	}
}
</script>


<body>

		<div>
		   <c:import url="/WEB-INF/views/page/index.jsp" />
			<div class="col-xs-12 col-sm-9" style="text-align: center">
				<div id="inputcontent">
			       <div class="panel panel-default">
						<div class="panel-heading"><h4><strong>자료 상세 정보</strong></h4></div>
				           <table class="table">
					           	<thead>
					               <tr>
					                   <th style="text-align: center">제목</th>
					                   <td style="text-align: left;">${ upload.title }</td>
					               </tr>
					               <tr>
					                   <th style="text-align: center">작성자</th>
					                   <td style="text-align: left;">
					                   	<c:forEach var="member" items="${ upload.memberId }">
					                   		${ member.memberId }	
					                   	</c:forEach>
					                   </td>
					               </tr>
					               <%--  
					               <tr>
					                  <th>조회수</th>
					                  <td>${ upload.readCount + 1 }</td>
					               </tr>
					               --%>
					               <tr>
					                  <th style="text-align: center">등록일자</th>
					                  <td style="text-align: left;">${ upload.regDate }</td>
					               </tr>
					               <tr>
					                   <th style="text-align: center">첨부자료</th>
					                   <td style="text-align: left;">
					                      <c:forEach var="file" items="${ upload.files }">
					                      <c:if test="${ not empty file.userFileName }">
					                      <a href='download.action?uploadfileno=${ file.uploadFileNo }'>
					                         ${ file.userFileName }
					                      </a>
					                      &nbsp;
					                      </c:if>                         
					                      </c:forEach>
					                   </td>
					               </tr>
					               <tr>
					                   <th style="text-align: center; vertical-align: top" valign="top">자료설명</th>
					                   <td style="text-align: left;">${ upload.content }</td>
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
			          </div>
				</div>      
			</div>
		</div>
	</div>
</div>
<br/><br/><br/><br/>
</body>
</html>