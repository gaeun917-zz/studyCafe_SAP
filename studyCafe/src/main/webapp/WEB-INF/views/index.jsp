<%@page import="com.studycafe.model.dto.Page"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>


<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/studyCafe/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/studyCafe/resources/js/carousel.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>


  <title> 스터디 카페 </title>
</head>
<body data-spy="scroll">
<c:import url="/WEB-INF/views/include/header.jsp"/>
<%-- <header>
  <nav class="navbar navbar-default" 
	  	data-spy="affix" 
		role="navigation">
    <div class="a">
      <div class="navbar-header">
        <a class="navbar-brand" href="/studyCafe/home.action">스터디 카페 <span class="subhead"></span></a>
        </div>
        
        
        <ul class="nav navbar-nav navbar-right">
          <li><a href="/studyCafe/upload/list.action">자료실</a></li>
          <li><a href="/studyCafe/board/write.action">스터디 등록 </a></li>
          <li><a href="/studyCafe/page/page.action">내 공부방 가기</a></li>
          <li><a href="/studyCafe/calendar/list.action">캘린더</a></li>
          
	        <c:choose>
				<c:when test="${ not empty sessionScope.loginuser }">
					<a href="#">${ loginuser.name }님 환영합니다.</a>
					<a href="/studyCafe/account/logout.action" id="logout">로그아웃</a>
				</c:when>
	
				<c:when test="${ not empty sessionScope.facebookLoginuser}">
					<a href="#">${ facebookLoginuser }님 환영합니다.</a>
					<a href="/studyCafe/account/logout.action" id="logout">로그아웃</a>
				</c:when>
	
				<c:otherwise>
					<a href="#" style="font-weight: bold;" data-toggle="modal" data-target="#myModal">로그인</a>
					<a href="/studyCafe/member/registerform2.action" style=" font-weight: bold;">회원가입</a>
				</c:otherwise>
			</c:choose>  
        </ul>
        
        
    </div><!-- container -->
  </nav>
</header> --%>	


	<!-- carousel START -->
		<div class= "carousel slide" id="featured" data-toggle="true" data-ride="carousel">				
			<ol class="carousel-indicators">
				<li data-target = "#featured" data-slide-to="0"></li>
				<li data-target = "#featured" data-slide-to="1"></li>
				<li data-target = "#featured" data-slide-to="2"></li>
				<li data-target = "#featured" data-slide-to="3"></li>
				<li data-target = "#featured" data-slide-to="4"></li>
			</ol>
	<!-- image list START -->					
		<div class="carousel-inner">								
			<div class="item active" >
				<img  style="height: 550px; width: 1600px" src="/studyCafe/resources/image/carousel/study1.jpg" alt="first">
				<div class="carousel-caption">
					<h1 style="font-weight: bold">함께 공부해요!</h1>
					<p style="font-size: large;">함께 공부해요!함께 공부해요!함께 공부해요!함께 공부해요!함께 공부해요!</p>
				</div>
			</div>					
			<div  class="item">
				<img style="height: 550px; width: 1600px" src="/studyCafe/resources/image/carousel/study2.jpg" alt="second">
				<div class="carousel-caption">
					<h1 style="font-weight: bold">다양한 관심사의 스터디 그룹!</h1>
					<p style="font-size: large;">다양한 스터디 그룹!다양한 스터디 그룹!다양한 스터디 그룹!다양한 스터디 그룹!다양한 스터디 그룹!</p>
				</div>
			</div>				
			<div class="item">
				<img style="height: 550px; width: 1600px" src="/studyCafe/resources/image/carousel/study3.JPG" alt="third">
				<div class="carousel-caption">
					<h1 style="font-weight: bold">스터디 자료는 내 공부방에서</h1>
					<p style="font-size: large;">스터디 자료는 내 공부방에서스터디 자료는 내 공부방에서스터디 자료는 내 공부방에서스터디 자료는 내 공부방에서</p>
				</div>
			</div>	
			<div class="item">
				<img style="height: 550px; width: 1600px" src="/studyCafe/resources/image/carousel/study4.jpg" alt="fourth">
				<div class="carousel-caption">
					<h1 style="font-weight: bold">체계적인 스터디 플랜 </h1>
					<p style="font-size: large;">체계적인 스터디 플랜 체계적인 스터디 플랜 체계적인 스터디 플랜 체계적인 스터디 플랜 체계적인 스터디 플랜 </p>
				</div>
			</div>	
			<div class="item">
				<img style="height: 550px; width: 1600px" src="/studyCafe/resources/image/carousel/study5.jpg" alt="fifth">
				<div class="carousel-caption">
					<h1 style="font-weight: bold">멘토 추천! </h1>
					<p style="font-size: large;">멘토 추천! 멘토 추천! 멘토 추천! 멘토 추천! 멘토 추천! 멘토 추천! 멘토 추천! 멘토 추천! </p>
				</div>			
			</div>	
		</div>
	<!-- image list END -->	
					
			<a class="left carousel-control" href ="#featured" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
					
			<a class="right carousel-control" href ="#featured" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
			</a>				
   		</div>
<!-- carousel END-->
  <br/>     <br/>   
  <div class="jumbotron" style="font-weight: bold;" >
  
   <h2 align="center" style="font-weight: bold;">Our Mission</h2>   
   <br/>   
   <div align="center">
		     	 <p> pply the wisdom garnered in the centuries old tradition of veterinary medicine, to find the safest treatments and&nbsp;cures.</p>
		      	 <p> receives the most appropriate care at crucial milestones. We want to give your pet a long and healthy&nbsp;life.</p>
    <p> receives the most appropriate care at crucial milestones. We want to give your pet a long and healthy&nbsp;life.</p>
   	</div>
   	   </div>


<br/><br/><br/>

<div class="container">
	<div id="pageContainer">
  <div class="row" id="studyCategory">
    <section class="page col-md-12">
      <h2 style="font-weight: bold;">스터디 카테고리</h2>
      <div  class="row" >
        
	        <section data-category="1" class="col-md-4">
	        <img id="life"  class="icon" src="/studyCafe/resources/image/lifestyle.png" height="170px" width="170px" align="center" alt="Icon">
	          <h3 >라이프 스타일 </h3> 
	          
	               
	          <p>We offer specialized care forther exotic pets.</p>
	        </section>        
	        
	        <section data-category="2" class="col-md-4">
	        <img id="english"  class="icon" src="/studyCafe/resources/image/a.png" height="170px" width="170px" align="middle" alt="Icon">
	          <h3  >영어 </h3>
	          <p>Our therapeutic grooming treatments hhallenging skin conditions.</p>
	        </section>       
	        
	        <section data-category="3" class="col-md-4">
	        <img id="foreign"  class="icon" src="/studyCafe/resources/image/foreignL.png" height="170px" width="170px" align="middle" alt="Icon">
	          <h3 >외국어 </h3>
	          <p>Our  therapeutic grooming treatments helpkin conditions.</p>
	        
	        </section>         
      </div>
<br/><br/><br/><br/>   
     
      <div class="row">
      
	        <section data-category="4"  class="col-md-4">
	        <img id="computer"  class="icon" src="/studyCafe/resources/image/computer.png" height="170px" width="170px" align="middle" alt="Icon">
	          <h3 >컴퓨터 </h3>
	          <p>Wellness and senior exams, ultrasound, x-ray,  few of our general health services.</p>
	        </section>    
	            
	        <section data-category="5" class="col-md-4">
	        <img id="design"   class="icon" src="/studyCafe/resources/image/design.png" height="170px" width="170px" align="middle" alt="Icon">
	          <h3 >디자인 </h3>
	          <p>Let our nutrition experts review your pet's diet and prescribeth.</p>
	        </section>  
	          
	        <section data-category="6" class="col-md-4">
	        <img id="certification"   class="icon" src="/studyCafe/resources/image/certification.png" height="170px" width="170px" align="middle" alt="Icon">
	          <h3 >국가고시 </h3>
	          <p>Let our nutrition experts review and disease prevention.</p>
	        </section> 
	             
	      </div>
	    <br/><br/><br/><br/> 
	      
      <div class="row">
        
        <section  data-category="7" class="col-md-4">
	        <img id="job"  class="icon" src="/studyCafe/resources/image/job.png" height="170px" width="170px" align="middle" alt="Icon">
          <h3>취업 </h3>
          <p>We offer the latest advances in safe and effective </p>
        </section>        
        
        <section data-category="8" class="col-md-4">
	        <img  id="music"   class="icon" src="/studyCafe/resources/image/music.png" height="170px" width="170px" align="middle" alt="Icon">
          <h3>음악공연 </h3>
          <p>Our veterinarians are experienced in modern vaccination .</p>
        </section>        
        
        <section data-category="9" class="col-md-4">
	        <img id="sports"    class="icon" src="/studyCafe/resources/image/sports.png" height="170px" width="170px" align="middle" alt="Icon">
          <h3>스포츠 </h3>
          <p>Our veterinarians are experienced in modern </p>
        </section> 
               
      </div>
    </section>
  </div>
<br/><br/><br/><br/><br/>





  <div class="row">
    <section id="staff" class="page col-sm-12">
      <h2 style="font-weight: bold">추천 스터디 멘토</h2>
      <div class="row" align="center">
        <br/><br/><br/>
        <section class="col-sm-4">
        <img class="img-circle" src="/studyCafe/resources/image/teacher1.jpg"  height="170px" width="170px" align="middle" alt="Icon">
          <br/>
          <h3>Dr. Winthrop</h3>
          <br/><br/>
          <p>Dr. Winthrop is the guardian of Missy, a three-year old Llaso mix, who he adopted at the shelter. Dr. Winthrop is passionate about spay and neuter and pet adoption, and works tireless hours outside the clinic, performing free spay and neuter surgeries for the shelter.</p>
        </section>
        <section class="col-sm-4">
         <img class="img-circle" src="/studyCafe/resources/image/teacher2.jpeg"  height="170px" width="170px" align="middle" alt="Icon">
          <br/>
          <h3>Dr. Chase</h3>
          <br/><br/>
          <p>Dr. Chase spends much of her free time helping the local rescue organization find homes for animals, such as Kibbles - a Maine Coon Cat who is part of the large Chase household, including two dogs, three cats, and a turtle.</p>
        </section>
        <section class="col-sm-4" >
         <img class="img-circle" src="/studyCafe/resources/image/teacher3.jpg"  height="170px" width="170px" align="middle" alt="Icon">
          <br/>
          <h3>Dr Sanders</h3>
          <br/><br/>
          <p>Leroy walked into Dr. Sanders front door when he was moving into a new house. After searching for weeks for Leroy's guardians, he decided to make Leroy a part of his pet family, and now has three cats.</p>
        </section>
      </div>
    </section>
  </div>

<br/><br/><br/>



  <div class="row">
    <section id="testimonials" class="page col-sm-12">
      <h2>Testimonials</h2>
      <div class="row">
        <section class="col-sm-6">
          <blockquote>
          <p>During the summer, our rabbit, Tonto, began to have severe redness and itching on his belly and feet. Through diagnostic testing, we learned that Tonto is severely allergic to over a dozens kinds of grass pollens. We've now been doing allergy injections for several months, and his itching and redness have nearly gone away.</p>
          <footer>Jane H.</footer>
          </blockquote>
        </section>
        <section class="col-sm-6">
          <blockquote>
            <p>When Samantha, our Siamese cat, began sleeping all the time and urinating excessively, we brought her to see the specialists at Wisdom. After running a blood test, Dr. Winthrop confirmed what we all feared - Samantha was showing signs of diabetes. The doctor put us on a daily routine to provide Samantha insulin injections, and showed us how to administer the shots.</p>
            <footer>The McPhersons</footer>
          </blockquote>
        </section>
      </div>
      <div class="row">
        <section class="col-sm-6">
          <blockquote>
            <p>The staff at Wisdom worked tirelessly to determine why our three-year old Golden Retriever, Roxie, started going into sudden kidney failure. They stabilized her and provided fluids until her kidneys were again functioning normally. We learned just how toxic grapes and raisins are to pets, and Roxie is no longer allowed to roam unattended in the orchard.</p>
            <footer>John B</footer>
          </blockquote>
        </section>
        <section class="col-sm-6">
          <blockquote>
            <p>Wisdom Pet Medicine is the only clinic around that will even book pet fish for appointments. When our 13-year old Comet goldfish, McAllister, turned from silvery white to an angry red, we called around, urgently trying to find a veterinarian who could help. Wisdom not only got us in the same day, but also was able to diagnose McAllister as having a severe case of septicemia.</p>
            <footer>Lorraine S</footer>
          </blockquote>
        </section>
      </div>
    </section>
  </div>

  </div><!-- row -->   
</div><!-- content container -->
<script type="text/javascript">

/* 로그인 실패 때, 로그인 실패 메시지를 전달해 주는 제이쿼리 ,아작스   */
/* 로그인 실패 때, 로그인 실패 메시지를 전달해 주는 제이쿼리 ,아작스   */
 /* 로그인 실패 때, 로그인 실패 메시지를 전달해 주는 제이쿼리 ,아작스   */

	$(function() {
		$('#btnLogin').on('click', function(event){
			$( '#loginResult' ).text('');
			$.ajax({
				url:"/studyCafe/account/login.action",
				type:"POST",
				data:$( '#frmLogin' ).serialize(),
				
				success: function(data,status,xhr){
					if(data=="success"){
						location.href="/studyCafe/home.action";
					}else if(data=="fail"){
						$( '#loginResult' ).text('로그인 실패하셨습니다. 다시 시도해 주세요');
					}
				}
				
			});
		})	
		
		$('#btnRegister').on('click', function(event){
			location.href='/studyCafe/member/term.action';
		})

		$('#studyCategory section[data-category]').on('mouseover', function(event){
			$(this).css('border','black solid 1px')
		})
		$('#studyCategory section[data-category]').on('mouseout', function(event){
			$(this).css('border','0')
		})
		$('#studyCategory section[data-category]').on('click', function(event){
			
			location.href='/studyCafe/board/list2.action?bigCategory='+$(this).attr('data-category');
		})
	});
	
	
	 /* 로그인 실패 때, 로그인 실패 메시지를 전달해 주는 제이쿼리 ,아작스   */
	/* 로그인 실패 때, 로그인 실패 메시지를 전달해 주는 제이쿼리 ,아작스   */

	
</script>


<div id="footer"></div>
	
<form id="frmLogin" action="/studyCafe/account/login.action" method="post">
<!-- -----------------------------------Modal----------------------------------- -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel" style="font-weight:bolder;color:black;" align="left" ><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;로그인</h4>
      </div>
      <div class="modal-body" align="center">
      <p> <!-- 여기는 내용이 들어 가는 곳 --> <input type="button" class="btn btn-primary" id="login" style="width:200px; height:60px" value="페이스북 계정으로 로그인"> </p>
      <a id="kakao-login-btn"></a>
		<!-- <a href="#" onclick="ktout()">카카오 로그아웃</a> -->
      <div class="signup-or-separator">
      	<span style="position:'absolute';top:500;" >or</span>
      	<hr>
      </div>
      <p><!-- 여기는 내용이 들어 가는 곳 --><input class="form-control"  name="memberId" type="text" style="width:350px; height:60px"  placeholder="아이디"> </p>
      <p><!-- 여기는 내용이 들어 가는 곳 --><input class="form-control" name="passwd" type="password" style="width:350px; height:60px"  placeholder="비밀번호"> </p>
      <p> <input type="checkbox" value="로그인 유지 " id="keepLogin">로그인 유지</p>
      
      </div>
      <div id="loginResult" style="text-align: center; color:red"></div>
      <div class="modal-footer" >
        <button type="button" id="btnLogin" class="btn btn-danger">로그인</button>
        <input type="button" id="btnRegister" class="btn btn-warning" value="회원가입"><!-- 변경 사항 저장 -->
      </div>
    </div> <!-- 모달 콘텐츠 -->
  </div> <!-- 모달 다이얼로그 -->
</div> <!-- 모달 전체 윈도우 -->
</form>


<script type='text/javascript'>
  
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('9bd78588adb541ca67caac077c742ab9');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
        // 로그인 성공시, API를 호출합니다.
        Kakao.API.request({
          url: '/v1/user/me',
          success: function(res) {
            alert(JSON.stringify(res));
          },
          fail: function(error) {
            alert(JSON.stringify(error));
          }
        });
      },
      fail: function(err) {
        alert(JSON.stringify(err));
      }
    });
  //]]>
  function ktout(){
	  
	  kakao.Auth.logout(function(){
		setTimeout(function(){
			location.href= "/studyCafe/home.action"
		},1000);  
	  });
  }
  
</script>
	
	
</body>
</html>


<%-- <%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<%-- <!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
    <title></title>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
</head>
<body>

    <div id="pageContainer">
    
    	<c:import url="/WEB-INF/views/include/header.jsp" />
        
        <div id="content">
        	<br /><br /><br /><br /><br />
        	<h2 style='text-aling:center'>
				Hello Demo Web Site Main Page !!!
			</h2>
        </div>
    </div>
    
</body>
</html> --%>