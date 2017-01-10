<%@page import="com.studycafe.model.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<title>게시물 목록</title>
<meta charset="utf-8" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js" charset="utf-8"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="/studyCafe/resources/bootstrap/js/bootstrap-select.min.js"></script>

<link rel="Stylesheet" href="/studyCafe/resources/styles/list.css" />


<script type="text/javascript">
	$(function(){

		/* 여기는 인덱스에서 선택한 카테고리를 보여줍니다!! 2016-06-17 */
		var bigCategory = '${bigCategory}';
		if(bigCategory != null && bigCategory != ""){
			var listItem=$('.list__item');
			listItem.css('display', 'none');
			
			$.each(listItem, function(index, data) {
				var smallCategory = $(this).attr('data-category2');
				if(smallCategory.substring(0,1) == bigCategory){
					$(this).css('display', 'block');					
				}
			})
		}	
		/* 여기까지 인덱스에서 선택한 카테고리를 보여줍니다!!   */
		
		$(".filter-button").on("click",function(event) {
			
			var value = $(this).attr('data-title');
			
			$('.list__item').css('display', 'block');
			if (value != "all") {
				$('.list__item[data-purpose != ' + value + ']').css('display', 'none');
			}

			
		});
		
		$('#smallCategory').on('change', function(event) {

			$('.list__item').css('display', 'none');
			
			var selectedOptions = $(this).find('option:selected');
			
			if (selectedOptions.length == 0) {
				$('.list__item').css('display', 'block');
			}
			
			$.each(selectedOptions, function(index, data) {
			
				$('.list__item[data-category = ' + $(data).text() + ']').css('display', 'block');
				
			})
			
		})
		
	})
	

</script>

</head>
<body>

<c:import url="/WEB-INF/views/include/header.jsp" />

	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">

			<div class="container">
				<div class="">
					<h2>FILTERING SEARCH</h2>
					<strong>당신에게 맞는 스터디 모임을 찾아보세요</Strong>
				</div>
				<br />
				<div class="double__elem">
					<div class="left__elem">
						<button class="btn btn-primary filter-button" data-title="all">ALL</button>
						<button class="btn btn-primary filter-button" data-title="자기개발">자기개발</button>
						<button class="btn btn-primary  filter-button" data-title="자격증준비">자격증준비</button>
						<button class="btn btn-primary  filter-button" data-title="취미생활">취미생활</button>
						<button class="btn btn-primary  filter-button" data-title="업무시킬UP">업무시킬UP</button>
						<button class="btn btn-primary  filter-button" data-title="건강/다이어트">건강/다이어트</button>
						<button class="btn btn-primary  filter-button" data-title="기타">기타</button>
					</div>
					<div class="left__right">
						<select id="smallCategory" name="smallCategory" class="selectpicker" data-style="btn-success" multiple data-max-option="3"
				data-live-search="true">
							<optgroup label="라이프스타일">
								<option value="101">육아</option>
								<option value="102">반려동물</option>
								<option value="103">금융/재테크</option>
								<option value="104">상담/컨설팅</option>
								<option value="105">건강/웰빙</option>
								<option value="106">사주/타로</option>
								<option value="107">독서/글쓰기</option>
								<option value="114">라이프스타일 기타</option>
							</optgroup>
							<optgroup label="영어">
								<option value="201">토익</option>
								<option value="202">토플</option>
								<option value="203">탭스</option>
								<option value="204">토익/오픽</option>
								<option value="205">영어회화</option>
								<option value="206">작문/독해</option>
								<option value="207">미드정복</option>
								<option value="208">영어 기타</option>
							</optgroup>
							<optgroup label="외국어">
								<option value="301">중국어</option>
								<option value="302">일본어</option>
								<option value="303">독일어</option>
								<option value="305">태국어</option>
								<option value="306">스페인어</option>
								<option value="307">러시아어</option>
								<option value="308">프랑스어</option>
								<option value="310">외국어 기타</option>
							</optgroup>
							<optgroup label="컴퓨터">
								<option value="401">응용프로그래밍</option>
								<option value="402">웹프로그래밍</option>
								<option value="403">모바일프로그래밍</option>
								<option value="406">문서작성/편집</option>
							</optgroup>
							<optgroup label="디자인">
								<option value="501">웹/모바일디자인</option>
								<option value="502">일러스트/삽화</option>
								<option value="503">광고/영상</option>
							</optgroup>
							<optgroup label="국가고시/공무원">
								<option value="601">행정</option>
								<option value="602">교육</option>
								<option value="603">경찰</option>
							</optgroup>
							<optgroup label="취업">
								<option value="701">자소서/면접</option>
								<option value="702">인적성</option>
								<option value="705">기업공채</option>
							</optgroup>
							<optgroup label="음악/공연">
								<option value="805">밴드</option>
								<option value="806">국악</option>
							</optgroup>
							<optgroup label="스포츠">
								<option value="901">축구</option>
								<option value="902">야구</option>
								<option value="904">테니스</option>
							</optgroup>
						</select>
					</div>
				</div>
			</div>
			
			<div class="borad_list__wrap">
				<ul class="borad_list">
					<c:forEach var="board" items="${ boards }">
						<li class="list__item" data-purpose='${ board.purpose }' data-category='${board.smallCategoryName[0].smallCategoryName}' data-category2='${board.smallCategoryName[0].smallCategoryNo}'>
							<div class="board_item__wrap">
								<div class="item__pics">
									<img
										src="/studyCafe/resources/boardimage/${board.boardNo}.jpg"
										alt="" />
								</div>
								<ul class="list_info">
									<li class="list_detail__over"><strong>제목</strong> <c:choose>
											
											<c:when test="${ board.status eq '0' }">
												<span style='color: gray; font-size: 15px;' onclick="alert('모집 마감 되었습니다.');">
													<%-- <a href="#">${ board.title }&nbsp;(모집 마감)</a> --%> ${ board.title }&nbsp;(모집
													마감)
												</span>
											</c:when>
											
											<c:otherwise>
												<a style="font-size: 15px; color:blue ;font-style:inherit; text-decoration: underline;" href='detail.action?boardno=${ board.boardNo }'> ${ board.title }</a>
											
											</c:otherwise>
										</c:choose>

										<div class="list_detail__wrap">
											<div class="list_detail">
												<ul class="list_detail_info">
													<li><strong>목표</strong> <span>${ board.purpose }</span></li>
													<li><strong>기간</strong> <span>${ board.period }</span></li>
													<li><strong>횟수</strong> <span>${ board.frequency }</span></li>
													<li><strong>모집 마감일</strong> <span>${ board.closeDate }</span></li>
												</ul>
											</div>
											<span class="bg"></span>
										</div></li>
									<li><strong>작성자</strong> <span> 
									
									<c:forEach
												var="member" items="${ board.memberId }">				
											${ member.memberId }
									</c:forEach>
									
									</span></li>
									<li><strong>장소</strong> <span>${ board.place }</span></li>
									<li><strong>카테고리</strong> <span>
									
									<c:forEach
												var="categoryBoard" items="${board.smallCategoryName }">
											${ categoryBoard.smallCategoryName }
									</c:forEach>
									
									</span></li>
									<li class="buttons" style="text-align: center;margin-top:15px;">
										<c:choose>
											<c:when test="${ board.status eq '0' }">
												<button type="button" value="모집종료" class="btn btn-danger"
													onclick="alert('모집 마감 되었습니다.');">모집 마감</button>
											</c:when>
											<c:otherwise>
												<button type="button" value="상세보기" class="btn btn-info"
													onclick="location.href='detail.action?boardno=${ board.boardNo }';">상세보기
												</button>

											</c:otherwise>
										</c:choose>
									</li>
								</ul>
							</div>
						</li>

					</c:forEach>
				</ul>
			</div>

		</div>
	</div>
</body>
</html>

