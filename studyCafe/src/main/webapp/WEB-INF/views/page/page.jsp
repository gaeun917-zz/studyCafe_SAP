<%@page import="java.util.List"%>
<%@page import="com.studycafe.model.dto.Page"%>
<%@page import="com.studycafe.model.dto.PageMenu"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>

<head>
<title>Insert title here</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


<style>
img {
  width: inherit;
  max-width: 100%;
  height: auto;
}
</style>
</head>

<body>
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
	<div class="jumbotron">
		<div class="container">
			<a href="/studyCafe/page/page.action"><h1>Hello, world!</h1></a>
			<p>This is a template for a simple marketing or informational
				website. It includes a large callout called a jumbotron and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.</p>
			<p>
				<a class="navbar-form navbar-right">
					<button type="button" class="btn btn-defaultbtn-slg btn-primary" data-toggle="modal" data-target="#myModal">
	         			<span class="glyphicon glyphicon-camera"></span>&nbsp;대표 이미지 변경
	       			</button>
       			</a>
			</p>
		</div>
	</div>


	<!-- Modal -->
	<form action="write.action" method="post" enctype="multipart/form-data">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">대표 이미지 변경</h4>
		      </div>
		      <div class="modal-body">
		      	<img id="preview" src="" style="width: auto;" alt="로컬에 있는 이미지가 보여지는 영역" />
				<input type="file" id="getfile" name="attach" accept="image/*">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		        <button type="submit" class="btn btn-primary" onclick="document.forms[0].submit();">변경 사항 저장</button>
		      </div>
		    </div> <!-- 모달 콘텐츠 -->
		  </div> <!-- 모달 다이얼로그 -->
		</div> <!-- 모달 전체 윈도우 -->
	</form>
	
	<script type="text/javascript">
	var file = document.querySelector('#getfile');
	
	file.onchange = function() { 
	    var fileList = file.files ;
	    
	    // 읽기
	    var reader = new FileReader();
	    reader.readAsDataURL(fileList [0]);
	
	    //로드 한 후
	    reader.onload = function() {
	        document.querySelector('#preview').src = reader.result ;
	    }; 
	}; 
	</script>


	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">

			<div class="col-xs-6 col-sm-2 col-md-offset-1 sidebar-offcanvas"
				id="sidebar">
				<div class="list-group">
					<a href="/studyCafe/upload/list.action" class="list-group-item">자료실</a>
					<a href="/studyCafe/calendar/list.action" class="list-group-item">일정</a>
					<% List<PageMenu> pageMenus = (List<PageMenu>) request.getAttribute("pagemenus"); %>
					<% for (PageMenu m : pageMenus) { %>
						<a href='board/list.action?menuno=<%=m.getMenuNo()%>' class="list-group-item" >
							<%=m.getName()%>
						</a>
					<%-- <td style="width:150px"><%= b.get %></td> --%>
					<%-- <td style="width:120px"><%= b.getBoarddate() %></td> --%>
					<%-- <td style="width:80px"><%= b.getReadCount() %></td> --%>
					<% } %>
				</div>
			</div>
			<!--/.sidebar-offcanvas-->

			<!-- <div class="col-xs-12 col-sm-7">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="jumbotron">
					<h1>Hello, world!</h1>
					<p>This is an example to show the potential of an offcanvas
						layout pattern in Bootstrap. Try some responsive-range viewport
						sizes to see it in action.</p>
				</div>
				<div class="row">
					<div class="col-xs-6 col-lg-4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce
							dapibus, tellus ac cursus commodo, tortor mauris condimentum
							nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
							malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
					/.col-xs-6.col-lg-4
					<div class="col-xs-6 col-lg-4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce
							dapibus, tellus ac cursus commodo, tortor mauris condimentum
							nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
							malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
					/.col-xs-6.col-lg-4
					<div class="col-xs-6 col-lg-4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce
							dapibus, tellus ac cursus commodo, tortor mauris condimentum
							nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
							malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>

					</div>
				</div>
			</div> -->
			
		</div>
	</div>


	
	
	
	<%-- <div id="menu">
		<div>
			<table border="1" align="center">
				<tr style="background-color: beige; height: 25px">
					<th style="width: 50px">번호</th>
					<th style="width: 300px">제목</th>
					<!-- <th style="width:150px">작성자</th> -->
					<!-- 	<th style="width:120px">작성일</th> -->
					<!-- <th style="width:80px">조회수</th> -->
				</tr>
				<%
					List<PageMenu> pageMenus = (List<PageMenu>) request.getAttribute("pagemenus");
				%>
				<%
					for (PageMenu m : pageMenus) {
				%>
				<tr style="background-color: white; height: 25px">
					<td style="width: 50px"><%=m.getMenuNo()%></td>
					<td style="width: 300px; text-align: left; padding-left: 5px">
						<a
						href='board/list.action?menuno=<%=m.getMenuNo()%>
							&pageno=<%=request.getAttribute("pageno")%>'>
							<%=m.getName()%>
					</a>
					</td>
					<td style="width:150px"><%= b.get %></td>
					<td style="width:120px"><%= b.getBoarddate() %></td>
					<td style="width:80px"><%= b.getReadCount() %></td>
				</tr>
				<%
					}
				%>

			</table>
			<div style='text-align: center'>
				<a href='#'>추가</a>
			</div>

		</div>
	</div> --%>
	
</body>
</html>