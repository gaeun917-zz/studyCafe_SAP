<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
<title>스터디 카페</title>

<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
<link href="/studyCafe/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/studyCafe/resources/js/bootstrap.min.js"></script>


<script type="text/javascript">
	$(function() {

		$('body').on('click', function(event) {
			$('#divAutoCom').remove();
		})

		$('#memberid').on('keyup', function(event) {
			var key = $(this).val();
			if (key.length == 0) {
				$('#divAutoCom').remove();
				return;
			}

			$.ajax({
				url : '/studyCafe/search.action', //'/ajaxweb/getsuggestions.action&id='+id
				type : 'get',
				data : {
					id : key
				}, //위에서 id를 queryString으로 보내주므로 여기서 id라는 이름을 씀
				success : function(data, status, xhr) {
					showResult(data);
				}
			})
		})

		/***************************************************/

		function showResult(data) {
			$('#divAutoCom').remove();

			if (data.length == 0)
				return;

			var nameArray = data.split(";");

			//외부 박스 만들기
			var divList = $('<div></div>').attr('id', 'divAutoCom').css({
				"border" : "1px	black solid",
				"background-color" : "white",
				"width" : "230px",
				"position" : "absolute",
				"left" : getLeft() + "px",
				"top" : getTop() + "px"
			}).appendTo('body');

			//내부 박스 만들기
			$.each(nameArray, function(index, data) {
				$('<div></div>').css({
					"padding-left" : "5px",
					"padding-top" : "2px",
					"padding-bottom" : "2px",
					"width" : "97%"
				}).text(data).on("mousedown", function(event) {
					$('#memberid').val($(this).text());
					divList.css("display", "none");
				}).on("mouseover", function(event) {
					$(this).css("background-color", "#efefef");
				}).on("mouseout", function(event) {
					$(this).css("background-color", "");
				}).appendTo(divList)
			})
		}

		function getTop() {
			var t = $('#memberid')[0];

			var topPos = 2 + t.offsetHeight;     //2를 왜 해주는 거지???
			while (t.tagName.toLowerCase() != "body" && //이것도 ??
			t.tagName.toLowerCase() != "html") {
				topPos += t.offsetTop;       //offsetTop : 상위 요소와의 거리
				t = t.offsetParent;             //상위 요소를 현재 요소에 대입
			}
			return topPos;
		}

		function getLeft() {
			var t = $('#memberid')[0];

			var leftPos = 0;
			while (t.tagName.toLowerCase() != "body"
					&& t.tagName.toLowerCase() != "html") {
				leftPos += t.offsetLeft;//t와 t좌측 요소 사이의 거리  이거는 좌측 요소인데??
				t = t.offsetParent;//t의 좌측 요소
			}
			return leftPos;
		}

	})
	
	
	////////////////////////    밑에는 페이스북 로그아웃/일반사용자 버튼기능     ////////////////////////////////////////

				
		function statusChangeCallback(response) {
			console.log('statusChangeCallback');
			console.log(response);
			// The response object is returned with a status field that lets the
			// app know the current login status of the person.
			// Full docs on the response object can be found in the documentation
			// for FB.getLoginStatus().


			if (response.status === 'connected') {//로그인 된 경우
				// Logged into your app and Facebook.
				
								
			} else if (response.status === 'not_authorized') {
				// The person is logged into Facebook, but not your app.
				/* document.getElementById('status').innerHTML = 'Please log '
						+ 'into this app.'; */
					
			} else {
				// The person is not logged into Facebook, so we're not sure if
				// they are logged into this app or not.
	/* 			document.getElementById('status').innerHTML = 'Please log '
						+ 'into Facebook.'; */
				
			}
		}

		// This function is called when someone finishes with the Login
		// Button.  See the onlogin handler attached to it in the sample
		// code below.
		function checkLoginState() {
			//로그인 상태를 확인하는 API 호출 (FB.getLoginStatus)
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		}

		//facebook login api library 초기화
		window.fbAsyncInit = function() {
			FB.init({
				appId : '732766060197583',
				cookie : true, // enable cookies to allow the server to access 
				// the session
				xfbml : true, // parse social plugins on this page
				version : 'v2.6' // use version 2.2
			});

			// Now that we've initialized the JavaScript SDK, we call 
			// FB.getLoginStatus().  This function gets the state of the
			// person visiting this page and can return one of three states to
			// the callback you provide.  They can be:
			//
			// 1. Logged into your app ('connected')
			// 2. Logged into Facebook, but not your app ('not_authorized')
			// 3. Not logged into Facebook and can't tell if they are logged into
			//    your app or not.
			//
			// These three cases are handled in the callback function.

			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});

		};

		// Load the SDK asynchronously
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		// Here we run a very simple test of the Graph API after login is
		// successful.  See statusChangeCallback() for when this call is made.
	
		
		$(function() {
			$('#login').on('click', function(event) {
				FB.login(function(response) {
					if (response.status === 'connected') {
						//서버에 로그인 처리
						FB.api('/me',//현재 로그인된 사용자 정보 요청
						   function(response2) {
								
								$.ajax({
									url : '/studyCafe/account/facebookLogin.action',
									type : 'post',
									data : { name : response2.name },
									success : function(data, status, xhr) {
										location.href = "/studyCafe/home.action";
									}
								})

						   });

					} else if (response.status === 'not_authorized') {						
					} else {						
					}
				})
			})
			
			$('#logout').on('click', function(event) {
				FB.logout(function(result) {
					//서버에 로그아웃 처리
					 $.ajax({
						url : '/studyCafe/account/logout.action',
						type : 'get',
						success : function(data, status, xhr) {
					 		checkLoginState();
						}
					})
				})
			})
			
		})
			
		
		
</script>


<style type="text/css">
#pretzel-video {
	bottom: 0;
	position: absolute;
	width: 100%;
}

video {
	display: inline-block;
}

#hero {
	z-index: -1;
	position: absolute;
	right: 0;
	left: 0;
	top: 0;
	height: 600px;
	overflow: hidden;
	min-width: 1045px;
}

.overlay {
	position: absolute;
	top: 30%;
	left: 35%;
	z-index: 1;
}

.searchform {
	position: absolute;
	top: 90%;
	left: 10%;
	z-index: 1;
}

.videotext {
	color: #ffffff;
	font-weight: bold;
	text-decoration: none;
	font-family: "Trebuchet MS", Sans-Serif, Verdana;
	text-align: center;
}

#maintext {
	font-size: 60px;
}

#subtext {
	font-size: 18px;
}

img {
	width: 300px;
	height: 300px;
}

.container {
	padding-top: 700px;
	padding-bottom: 100px;
}

#footer {
	border: solid black 2px;
	width: 100%;
	height: 500px;
}

.col-sm-4 {
	padding-bottom: 60px;
}

h2 {
	position: absolute;
	color: #ffffff;
	top: 35%;
	left: 35%;
}

#navtab {
	padding-bottom: 50px;
}
</style>


</head>
<body>
<div id="header">
	<div class="title">
		<a href="/studyCafe/home.action" >&nbsp;&nbsp;Airbnb</a>
	</div>
	<div class="links">
		

			<a href="/studyCafe/rooms/list.action" style="font-weight: bold;">호스팅하기</a> 
			<a href="/studyCafe/upload/uploadlist.action" style="font-weight: bold;">자료실</a> <a
			href="/studyCafe/board/list.action" style="font-weight: bold;">게시판</a> <a
			href="/studyCafe/info/info.action" style="font-weight: bold;">test-sw</a>



		<c:choose>
			<c:when test="${ not empty sessionScope.loginuser }">
				<a href="#">${ loginuser.name }님 환영합니다.</a>
				<a href="/teamfive/account/logout.action" id="logout">로그아웃</a>
			</c:when>

			<c:when test="${ not empty sessionScope.facebookLoginuser}">
				<a href="#">${ facebookLoginuser }님 환영합니다.</a>
				<a href="/teamfive/account/logout.action" id="logout">로그아웃</a>
			</c:when>

			<c:otherwise>
				<a href="#" style="font-weight: bold;" data-toggle="modal" data-target="#myModal">로그인</a>
				<a href="/teamfive/member/register.action" style=" font-weight: bold;">등록</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>



	<div id="hero">
		<video id="pretzel-video" loop="loop" preload="auto"
			class="video-playing" autoplay="autoplay" controls>
			<source src="/teamfive/resources/video/airbnb1.mp4" type="video/mp4">
		</video>
		<div class="overlay">
			<p id="maintext" class="videotext">사는건 여행인거야</p>
			<p id="subtext" class="videotext">호스트의 집을 예약하고 살아보는 여행을 경험해 보세요.</p>
		</div>
		<div class="searchform">
			<input type="text" /> <input type="text" /> <input type="text" />
		</div>
	</div>
	<div class="container">
		<div id="navtab">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#home" data-toggle="tab">인기 숙소</a></li>
				<li><a href="#tab1" data-toggle="tab">특별 할인</a></li>
				<li><a href="#tab2" data-toggle="tab">추천 숙소</a></li>
				<li><a href="#tab3" data-toggle="tab">최근 등록</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<h3>인기 숙소</h3>
				</div>
	
				<div class="tab-pane" id="tab1">
					<h3>특별 할인</h3>
				</div>
	
				<div class="tab-pane" id="tab2">
					<h3>추천 숙소</h3>
				</div>
	
				<div class="tab-pane" id="tab3">
					<h3>최근 등록</h3>
				</div>
			</div>
		</div>
		
		
	
		<div class="col-sm-4">
			<h2>서울</h2>
			<img alt="seoul" src="/teamfive/resources/image/seoul.jpg"
				class="img-rounded">
		</div>

		<div class="col-sm-4">
			<h2>강원도</h2>
			<img alt="jongno" src="/teamfive/resources/image/gangwon.jpg"
				class="img-rounded">
		</div>

		<div class="col-sm-4">
			<h2>경기도</h2>
			<img alt="myeong-dong" src="/studyCafe/resources/image/gyonggi.jpg"
				class="img-rounded">
		</div>

		<div class="col-sm-4">
			<h2>충청도</h2>
			<img alt="gangnam" src="/studyCafe/resources/image/choongchung.jpg"
				class="img-rounded">
		</div>

		<div class="col-sm-4">
			<h2>경상도</h2>
			<img alt="gangnam" src="/studyCafe/resources/image/kyongsang.jpg"
				class="img-rounded">
		</div>

		<div class="col-sm-4">
			<h2>전라도</h2>
			<img alt="hongdae" src="/studyCafe/resources/image/jeonra.jpg"
				class="img-rounded">
		</div>

		<div class="col-sm-4">
			<h2>제주도</h2>
			<img alt="hongdae" src="/studyCafe/resources/image/jeju.jpg"
				class="img-rounded">
		</div>
	</div>
	<div id="footer"></div>
	
<form action="/teamfive/account/login.action" method="post">
<!-- -----------------------------------Modal----------------------------------- -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><!-- 모달 제목 --> </h4>
      </div>
      <div class="modal-body" align="center">
      <p> <!-- 여기는 내용이 들어 가는 곳 --> <input type="button" class="btn btn-primary" id="login" style="width:350px; height:60px" value="페이스북 계정으로 로그인"> </p>
      <div class="signup-or-separator">
      	<span style="position:'absolute';top:500;" >or</span>
      	<hr>
      </div>
      <p><!-- 여기는 내용이 들어 가는 곳 --><input class="form-control" name="email" type="text" style="width:350px; height:60px"  value="이메일 주소"> </p>
      <p><!-- 여기는 내용이 들어 가는 곳 --><input class="form-control" name="passwd" type="text" style="width:350px; height:60px"  value="비밀번호"> </p>
      </div>
      <div class="modal-footer" >
        <button type="submit" class="btn btn-danger"><!-- 닫기 -->로그인</button>
        <input type="button"  class="btn btn-warning" value="회원가입"><!-- 변경 사항 저장 -->
      </div>
    </div> <!-- 모달 콘텐츠 -->
  </div> <!-- 모달 다이얼로그 -->
</div> <!-- 모달 전체 윈도우 -->
</form>
	
	
</body>
</html>
