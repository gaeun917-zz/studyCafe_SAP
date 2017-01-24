<%@page import="com.studycafe.model.dto.BoardComment" %>
<%@page import="com.studycafe.model.dto.Member" %>
<%@page import="com.studycafe.model.dto.PageBoard" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>글쓰기</title>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css"/>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/input2.css"/>
</head>


<body>
    <script type="text/javascript">
        // 삭제버튼
        $(document).ready(function () {
            $('#btn').on('click', function (event) {
                var title = ${board.PBoardNo};
                var yes = confirm( title + ' 번 글을 삭제할까요?'); //alert
                if (yes) {
                    var url = 'delete.action?boardno=' + title +
                        '&pageno=' + ${pageno} +'&menuno=' + ${menu.menuNo} +
                            "&memberpageno=" + ${memberpageno};
                    $(location).attr('href', url);
                }
                <%--location.href="writeform.action?menuno=" + --%>
                <%--${menu.menuNo}; --%>
            });
        });
    </script>

    <div class="page_board_detail">
        <div id="pageContainer">
            <c:import url="/WEB-INF/views/page/index.jsp"/>
            <div class="col-xs-12 col-sm-9" style="text-align: center">

                <%PageBoard board = (PageBoard) request.getAttribute("board");%>
                <%Member member = (Member) request.getAttribute("member");%>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><strong>${menu.name}</strong></h4>
                    </div>
                    <table class="table">
                        <tr>
                            <th>제목</th>
                            <td id="title">
                                <%=board.getTitle()%>
                            </td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td>
                                <%=member.getMemberId()%>
                            </td>
                        </tr>
                        <tr>
                            <th>작성일</th>
                            <td>
                                <%=board.getBoarddate()%>
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <%=board.getContent()%>
                            </td>
                        </tr>
                    </table>
                </div>
                <hr>


                <div class="buttons">
                    <c:choose>
                        <c:when test="${ sessionScope.loginuser.memberNo eq board.memberNo }">
                            <div class="row">
                                <div class="col-md-9"></div>
                                <div class="col-md-3">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <%--
                                            <button type="button" value="삭제" class="btn btn-primary"
                                                     onclick="location.href='javascript:doDelete(${baord.PBoardNo}, ${pageno})'"
                                             </button>
                                            script로 delete 처리해줌
                                        --%>
                                    <button id="btn" type="button" value="삭제" class="btn btn-primary">
                                        삭제
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" value="취소" class="btn btn-primary"
                                            onclick="location.href='list.action?' +
                                                    'menuno=${menu.menuNo}&memberpageno=${memberpageno}';">
                                        목록
                                    </button>
                                </div>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="row">
                                <div class="col-md-8"></div>
                                <div class="col-md-4">
                                    <button type="button" value="취소" class="btn btn-primary"
                                            onclick="location.href='list.action?' +
                                                    'menuno=${menu.menuNo}&memberpageno=${memberpageno}';">
                                        목록
                                    </button>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>  <br/> <br/>
            </div>
        </div>
    </div>  <br/><br/><br/><br/>
</body>
</html>