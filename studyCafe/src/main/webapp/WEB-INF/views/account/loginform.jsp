

<!--                    로그인 폼은 인텍스의 모달에 넣음 					  -->
<!--					로그인 폼은 인텍스의 모달에 넣음  				      -->
<!--					로그인 폼은 인텍스의 모달에 넣음   -->






<%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>로그인</title>
	    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
	
	<!-- <link rel="Stylesheet" href="/teamfive/resources/styles/default.css" />
	<link rel="Stylesheet" href="/teamfive/resources/styles/input.css" />
 -->	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>	
	<script type="text/javascript">
		//window.onload : javascript event (컨텐츠 로딩이 끝나면 호출)
		//window.onload = function() {
			
			/* var failedLoginId = ${ requestScope.loginid };
			
			if (failedLoginId) { 
				alert('로그인 실패');
				document.getElementById("memberId").value = failedLoginId;
			} */
			
			
		//};		
	</script>
</head>









<body>	
	<div id="pageContainer">
	
		<c:import url="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">로그인정보</div>
		        
		        <form action="/studyCafe/account/login.action" method="post">
		        
		        <!-- input type="hidden" : 사용자에게 보이지 않지만 서버로 전송되는 입력 요소 -->
		        <input type="hidden" name="returnurl" value='${ empty requestScope.returnurl ? "" : requestScope.returnurl }' />
		        <div style='text-align:center'>
	
	
		
	
	
	</div>
	
		<!-- <fb:login-button scope="public_profile,email" 
			onlogin="checkLoginState();" autologoutlink="true">
		</fb:login-button> -->
	
		        <table>
		        	<tr>
		        		  <th>페이스북 로그인</th>
		        		 <td>
		                    <input type="button" id="login" name="login" style="width:280px" value="로그인"/>
		        			  <input type="button" id="logout" name="logout" style="width:280px" value="로그아웃"/>
		        		  </td> 
		        	</tr>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" id="memberId" name="memberId" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" name="passwd" style="width:280px" />
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="로그인" style="height:25px" />
		        	<input type="button" value="취소" style="height:25px" />
		        </div>
		        </form>
		        
		        <div id="status"></div>
		        
		        
		    </div>
		</div>   	
	
	</div>

</body>
</html>



 --%>

































<%-- <!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>로그인</title>
	<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
	<link rel="Stylesheet" href="/studyCafe/resources/styles/input.css" />	
	<script type="text/javascript">
		//window.onload : javascript event (컨텐츠 로딩이 끝나면 호출)
		window.onload = function() {
			
			var failedLoginId = ${ requestScope.loginid };
			
			if (failedLoginId) { 
				alert('로그인 실패');
				document.getElementById("memberId").value = failedLoginId;
			}
			
		}
	</script>
</head>
<body>	
	<div id="pageContainer">
	
		<c:import url="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">로그인정보</div>
		        
		        <form action="/studyCafe/account/login.action" method="post">
		        
		        <!-- input type="hidden" : 사용자에게 보이지 않지만 서버로 전송되는 입력 요소 -->
		        <input type="hidden" name="returnurl" value='${ empty requestScope.returnurl ? "" : requestScope.returnurl }' />
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" id="memberId" name="memberId" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" name="passwd" style="width:280px" />
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="로그인" style="height:25px" />
		        	<input type="button" value="취소" style="height:25px" />
		        </div>
		        </form>
		        
		    </div>
		</div>   	
	
	</div>

</body>
</html> --%>