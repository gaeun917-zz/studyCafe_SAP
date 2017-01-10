<%@ page import="com.studycafe.model.dto.Member"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <!-- 헤더에서는 제이쿼리 부트스트랩을 쓰지 않음  -->
<!-- 
<link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/studyCafe/resources/bootstrap/js/bootstrap.min.js"></script> -->
<body data-spy="scroll">


<!-- 헤더 사용 설명서!!! 헤더는 반드시 <BODY> "바로 밑에"   -->
<%-- <c:import url="/WEB-INF/views/include/header.jsp"/>, 이 코드를 붙여 줍니다.  --%>

<!--  -----------------------HEADER STARTS--------------------------------- -->
<!--  -----------------------HEADER STARTS--------------------------------- -->


		<header id="header" >
		  <nav class="navbar navbar-default"  role="navigation">
		    <div class="a">
		      <div class="navbar-header">
		      <!-- <a><img class="icon" style="margin-left: 30px; margin-top:10px; border: medium;" src="/studyCafe/resources/image/Bus.png" height="40px" width="40px" alt="Icon"></a> -->
		         <a class="navbar-brand" href="/studyCafe/home.action" style="font-weight: bolder; font-float:none; font-size: large;">스터디 카페</a>
		        </div>       

				<script type="text/javascript">
				$(function() {
					$('#mystudyroom').on('click', function(event) {
						$.ajax({
							url : '/studyCafe/account/pageList.action',
							type : 'get',
							contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
							data : {},
							success : function(data, status, xhr) {
								showResult(data);
							}
						})
					})
					function showResult(data) {
						if (data.length == 0)
							return;
						$('#dspan').html(data);
					}
				})
				</script>
				
		        <ul class="nav navbar-nav navbar-right">
		          <li><a href="/studyCafe/board/write.action"><span class="glyphicon glyphicon-pushpin"></span>&nbsp;&nbsp;스터디 등록</a></li>
		          <li><a href="/studyCafe/board/list.action"><span class="glyphicon glyphicon-tree-deciduous"></span>&nbsp;&nbsp;스터디 참여</a></li>
		          <li class="dropdown">
		          <a id="mystudyroom" class="dropdown-toggle" data-toggle="dropdown" href="javascript:window.open('/studyCafe/page/board/list.action?menuno=${pagemenu.menuNo}', '${ userpage.getName() }', 'width=1000, height=1200, toolbar=no, menubar=no, scrollbars=no, resizable=yes');">
			      <span class="glyphicon glyphicon-lamp"></span>&nbsp;&nbsp;내 공부방 가기<b class="caret"></b></a>
			      <ul class="dropdown-menu">
		              <span id="dspan"><hr><li style='text-align: center'>No Search Your Room</li><hr></span>
            	  </ul>
			      </li>
		          <!-- <li><a href="/studyCafe/calendar/list.action">캘린더</a></li> -->
		          <li><a href="#"></a></li>
		          <li>
					
					  <c:choose>
							<c:when test="${ not empty sessionScope.loginuser }">
								<a id="btnLogin1" href="#" style="font-size: large;">${ loginuser.name }&nbsp;&nbsp;</a>						
							</c:when>
				
							<c:when test="${ not empty sessionScope.facebookLoginuser}">
								<a id="btnLogin1" href="#">${ facebookLoginuser }</a>							
							</c:when>

							<c:otherwise>						
								<a id="btnLogin1" href="#" style="font-weight: bold;" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;로그인</a>								                                                                                                                                     
							</c:otherwise>

					  </c:choose> 
					<button style="margin-top: 10px; size: 40px"  
					type="button" data-toggle="dropdown" id="pettype" 
					aria-haspopup="true" aria-expanded="true" class="btn btn-info dropdown-toggle">${loginuser.memberId}</button>


					<ul class="dropdown-menu" aria-labelledby="pettype">
						<li class="dropdown-header"></li>
						  <li><a href="/studyCafe/member/mypage.action"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;마이페이지</a></li>
						  <li><a href="#">Fish</a></li>
						  <li><a href="#">Amphibian</a></li>
						  <li><a href="#">Reptile</a></li>
						  <li><a href="/studyCafe/account/logout.action" id="logout"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;로그아웃</a></li>
					</ul>
     					</li>
					 <li><a href="#"></a></li>
     		      </ul>
				<script type="text/javascript">
					$('#pettype').css('display', "${ empty loginuser ? 'none' : 'block' }");
					$('#btnLogin1').css('display', "${ not empty loginuser ? 'none' : 'block' }");
				</script>
		    </div><!-- container -->
		  </nav>
		</header>
	
<!--  -----------------------HEADER ENDS--------------------------------- -->
<!--  -----------------------HEADER ENDS--------------------------------- -->
	

