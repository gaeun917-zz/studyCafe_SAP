<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" session="true" %>
<%@ page import="com.studycafe.model.dto.SmallCategory" %>
<%@ page import="com.studycafe.model.dto.BoardComment" %>
<%@ page import="com.studycafe.model.dto.Member" %>
<%@ page import="com.studycafe.model.dto.Board" %>
<%-- **jstl "core" tag library를 c접두사로 사용하는 설정 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- **functions : el에서 사용할 수 있는 함수를 포함하는 태그집합 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
    <title>글쓰기</title>
    <meta charset="utf-8"/>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="/studyCafe/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/studyCafe/resources/bootstrap/css/small-business.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="/studyCafe/resources/bootstrap/js/bootstrap-select.min.js"></script>
    <%--Jquery--%>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js" charset="utf-8"></script>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/list.css">

    <script type="text/javascript">
        function doDelete(boardNo) {
            yes = confirm(boardNo + '번 모집을 마감합니까?');
            if (yes) {
                location.href = 'delete.action?boardno=' + boardNo;
            }
        }
//      function register_Confirm() {
//         $("#register").click(function() {
//         alert("참가신청 되었습니다.!!");
//         });
//      }

        $('#register').on('click', function (event) {
            var membercount = ${membercount};
            var boardno = ${board.boardNo};
            $.ajax({
                    url: '/studyCafe/board/enjoy.action',
                    type: 'get',
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    data: {
                            count: membercount,
                            boardno: boardno
                    },
                    success: function (data, status, xhr) {
                             showResult(data);
                    }
            });
        });

        function showResult(data) {
            if (data.length == 0)
                return;
            $('#register').css('display', 'none'); //버튼 감추기
            alert("참가신청 되었습니다.!!");            //  alert
            $('#members').html(data + '/ ${board.memberCount} 명'); //모집인원 표시
        }
    </script>

</head>

<body>
<c:import url="/WEB-INF/views/include/header.jsp"/>

<div id="pageContainer">
    <div>
        <div class="container"> <!-- Page Content -->
            <div class="row"> <!-- Heading Row -->

                <div class="col-md-8"> <%--모임 이미지 --%>
                    <img class="img-responsive img-rounded" alt=""
                         src="/studyCafe/resources/boardimage/${board.boardNo}.jpg"/>
                </div>


                <div class="col-md-4">
                    <h1>${ board.title }</h1>
                    <h4>모임 정보</h4>
                    <ul>
                        <li><strong>Founder</strong>
                            <span style="margin-left: 130px">
                                <c:forEach var="member" items="${ board.memberId }">
                                     ${ member.memberId }
                                </c:forEach>
                            </span>
                        </li>
                        </br>
                        <li><strong>카테고리</strong>
                            <span style="margin-left: 110px">
                                <c:forEach var="categoryBoard" items="${ board.smallCategoryName }">
                                    ${categoryBoard.smallCategoryName}
                                </c:forEach>
                            </span>
                        </li>
                        </br>
                        <li><strong>목표</strong>
                            <span style="margin-left: 130px">
                                ${ board.purpose }
                            </span>
                        </li>
                        </br>
                        <li><strong>장소</strong>
                            <span style="margin-left: 140px">
                                ${ board.place }
                            </span>
                        </li>
                        </br>
                        <li><strong>기간</strong>
                            <span style="margin-left: 150px">
                                ${ board.period }
                            </span>
                        </li>
                        </br>
                        <li><strong>횟수</strong>
                            <span style="margin-left: 150px">
                                ${ board.frequency }
                            </span>
                        </li>
                        </br>
                        <li><strong>모집시작</strong>
                            <span style="margin-left: 80px">
                                ${ board.startDate }
                            </span>
                        </li>
                        </br>
                        <li><strong>모집마감</strong>
                            <span style="margin-left: 80px">
                                ${ board.closeDate }
                            </span>
                        </li>
                        </br>
                        <li><strong>모집 인원 수</strong>
                            <span id="members" style="margin-left: 100px">
                                ${membercount} / ${board.memberCount} 명
                            </span>
                        </li>
                        </br>
                    </ul>

                </div> <!-- /.col-md-4 -->
            </div><%-- Heading row--%>


            <div class="col-md-4" id="col-md-4-2">
                <h2>introduction</h2>
                <p>${board.content }</p>

                <div class="buttons">
                    <c:choose>
                                    <%-- 왜 sessionScope이지?--%>
                        <c:when test="${ sessionScope.loginuser.memberNo eq board.memberNo }">
                            <button type="button" value="삭제" class="btn btn-primary"
                                    onclick="location.href='javascript:doDelete(${ board.boardNo })'">모집 마감
                            </button>
                            <button type="button" value="취소" class="btn btn-primary"
                                    onclick="location.href='list.action';">목록보기
                            </button>
                        </c:when>

                        <c:when test="${not empty swich}">
                            <button type="button" value="취소" class="btn btn-primary"
                                    onclick="location.href='list.action';">목록보기
                            </button>
                        </c:when>

                        <c:otherwise>
                             <div class="col-md-5">
                                <div id="register" class="col-md-1">
                                    <button type="button" value="스터디참여" class="btn btn-primary">스터디참여
                                    </button>
                                </div>
                                <div class="col-md-3">
                                    <button type="button" value="취소" class="btn btn-primary"
                                            onclick="location.href='list.action';">목록보기
                                    </button>
                                </div>
                             </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


