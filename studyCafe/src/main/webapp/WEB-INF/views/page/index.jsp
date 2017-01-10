<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.studycafe.model.dto.Page"%>
<%@page import="com.studycafe.model.dto.PageMenu"%>
<%@page import="com.studycafe.model.dto.Member"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
img {
	width: inherit;
	max-width: 100%;
	height: auto;
}
</style>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
<!-- <link href='/studyCafe/resources/styles/fullcalendar.css'
	rel='stylesheet' />
<link href='/studyCafe/resources/styles/fullcalendar.print.css'
	rel='stylesheet' media='print' />
<script src='/studyCafe/resources/js/moment.min.js'></script>
<script src='/studyCafe/resources/js/fullcalendar.min.js'></script> -->

<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" ></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js" /></script> -->

<!-- <script>
	$(function() {
	    $( "#testDatepicker" ).datepicker({
	    	 showOn: "both", 
	         /* buttonImage: "button.png", */
	         buttonImageOnly: true,
	         changeMonth: true,
	         changeYear: true,
	         nextText: '다음 달',
	         prevText: '이전 달',
	         showButtonPanel: true,
	         currentText:'오늘 날짜',
	         closeText: '닫기',
	         minDate: 0
	         //maxDate: 14
	    });
	    $( "#testDatepicker2" ).datepicker({
	   	 	showOn: "both", 
	        // buttonImage: "button.png",
	        buttonImageOnly: true,
	        changeMonth: true,
	        changeYear: true,
	        nextText: '다음 달',
	        prevText: '이전 달',
	        showButtonPanel: true,
	        currentText:'오늘 날짜',
	        closeText: '닫기',
	        minDate: 0
	        //maxDate: 7
	        
	   });
	});
	</script> -->

<!-- <nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Project name</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" placeholder="Email" class="form-control">
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control">
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
			</div>
			/.navbar-collapse
		</div>
	</nav> -->


<!-- Main jumbotron for a primary marketing message or call to action -->
<!-- <div class="jumbotron"> -->
<div class="container">
	<a href="/studyCafe/page/board/list.action?menuno=${noticemenu.menuNo}
							&pageno=1&memberpageno=${memberpageno}"> <img
		src="/studyCafe/resources/uploadimage/${memberpageno}.jpg"
		style="width: 100%; height: 250px;" />
	</a>
	<!-- <a href="/studyCafe/page/page.action"><h1>Hello, world!</h1></a>
			<p>This is a template for a simple marketing or informational
				website. It includes a large callout called a jumbotron and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.</p>
			<p> -->
	<a class="navbar-form navbar-right">
	<c:choose>
		<c:when test="${not empty swich}"> 
		<button type="button" class="btn btn-defaultbtn-slg btn-primary"
			data-toggle="modal" data-target="#myModal">
			<span class="glyphicon glyphicon-camera"></span>&nbsp;대표 이미지 변경
		</button>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	</a>
	</p>
</div>
<!-- </div> -->


	<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">대표 이미지 변경</h4>
		      </div>
		      <form action="/studyCafe/page/image.action?menuno=${noticemenu.menuNo}
							&pageno=1&memberpageno=${memberpageno}" method="post" enctype="multipart/form-data">
		      <div class="modal-body">
		      	<img id="preview" src="" style="width: auto;" alt="로컬에 있는 이미지가 보여지는 영역" />
				<input type="file" id="getfile" name="image" accept="image/*">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		        <button type="submit" class="btn btn-primary" onclick="document.forms[0].submit();">변경 사항 저장</button>
		      </div>
		      </form>
		    </div> <!-- 모달 콘텐츠 -->
		  </div> <!-- 모달 다이얼로그 -->
		</div> <!-- 모달 전체 윈도우 -->
	
<script type="text/javascript">
	var file = document.querySelector('#getfile');

	file.onchange = function() {
		var fileList = file.files;

		// 읽기
		var reader = new FileReader();
		reader.readAsDataURL(fileList[0]);

		//로드 한 후
		reader.onload = function() {
			document.querySelector('#preview').src = reader.result;
		};
	};

	$(function() {
		$('#modalBtn').on('click', function(event) {
			var name = $("input[id=ip1]").val();
			var memberpageno = ${memberpageno};
			if (name == '') {
				alert('검색어를 입력하세요.');
				return;
			}

			$.ajax({
				url : '/studyCafe/page/board/plus.action',
				type : 'get',
				data : {
					name : name,
					memberpageno : memberpageno
				},
				success : function(data, status, xhr) {
					showResult(data);
					var kkk = ${menu.menuNo}1;
					var ooo;
					if(kkk > 1){
						ooo = '&menuno=${menu.menuNo}';
					}
					else{
						ooo = "";
					}
					var url = 'list.action?&memberpageno=' + ${memberpageno} + ooo;
					$(location).attr('href', url);
					/* $('#pageModal').modal('hide'); */
				}
			})
		})
		function showResult(data) {
			if (data.length == 0)
				return;
		}
		
		$('#collapseOne').collapse('hide');
	})
</script>

<div class="container">
	<div class="row row-offcanvas row-offcanvas-right">

		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
			<div class="list-group" style="text-align: center">
				<h4 class="panel-title"><a href='/studyCafe/page/board/list.action?menuno=${noticemenu.menuNo}
							&pageno=1&memberpageno=${memberpageno}' class="list-group-item">
							공지 사항
					</a></h4>
				<h4 class="panel-title"><a href="/studyCafe/upload/list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}" class="list-group-item">자료실</a></h4>
				<h4 class="panel-title"><a href="/studyCafe/calendar/list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}" class="list-group-item">일정</a></h4>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"> 게시판
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">
							<%List<PageMenu> pageMenus = (List<PageMenu>) request.getAttribute("pagemenus");%>
							<%for (PageMenu m : pageMenus) {%>
							<a	href='/studyCafe/page/board/list.action?menuno=<%=m.getMenuNo()%>
							&pageno=<%=request.getAttribute("pageno")%>&memberpageno=${memberpageno}'
								class="list-group-item"> <%=m.getName()%>
							</a>
							<%}%>
						</div>
					</div>
				</div>
				<a class="list-group-item" id="plus" data-toggle="modal" data-target="#pageModal">게시판 추가</a> 
			</div>
		</div>

			<!-- Modal -->
			<div class="modal fade" id="pageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			        <h4 class="modal-title" id="myModalLabel">게시판 만들기 </h4>
			      </div>
			      <div class="modal-body">
			      	<input id="ip1" type="text" placeholder=" Input Board Name" />
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			        <button id="modalBtn" type="button" class="btn btn-primary">생성</button>
			      </div>
			    </div> <!-- 모달 콘텐츠 -->
			  </div> <!-- 모달 다이얼로그 -->
			</div> <!-- 모달 전체 윈도우 -->
		<!-- </div>
	</div> -->
	
