<%@ page import="java.util.ArrayList" %>
<%@ page import="com.studycafe.model.dto.Member" %>
<%@ page import="com.studycafe.model.dto.PageBoard" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>게시물 목록</title>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css"/>

</head>
<body>
<script type="text/javascript">
    $(function () {
        //검색
        $('#btn1').on('click', function (event) {
            var search = $("input[id=search]").val(); //?
            if (search == '') {
                alert('검색어를 입력하세요.');
            } else {
                var url = 'search.action?search=' + search +
                          '&menuno=' + ${menu.menuNo} +'&pageno=1' +
                          "&memberpageno=" + ${memberpageno};
                $(location).attr('href', url);
            }
        });
        //글쓰기
        $('#btn2').on('click', function (event) {
            var url = "writeform.action?" +
                "menuno=" + ${menu.menuNo} + "&memberpageno=" + ${memberpageno};
            $(location).attr('href', url);
        });
    });
</script>

    <div id="pageContainer">
        <c:import url="/WEB-INF/views/page/index.jsp"/>
        <div class="col-xs-12 col-sm-9" style="text-align: center">
            <div class="page_board_list">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><strong>${menu.name}</strong></h4>
                    </div>
                    <table class="page_board_list_table">
                        <thead>
                            <tr>
                                <th>번 호</th>
                                <th>제 목</th>
                                <th>글쓴이</th>
                                <th>작성일</th>
                            </tr>
                        </thead>


                        <% List<PageBoard> pageBoards = (List<PageBoard>) request.getAttribute("boards"); %>
                        <% ArrayList<Member> members = (ArrayList<Member>) request.getAttribute("members"); %>



                        <% for (int i = 0; i < pageBoards.size(); i++) { %>
                        <tr>
                            <td><%= pageBoards.get(i).getPBoardNo() %></td>
                            <td>
                                <a href='detail.action?boardno=<%= pageBoards.get(i).getPBoardNo() %>
                                                       &pageno=<%= request.getAttribute("pageno") %>
                                                       &menuno=${menu.menuNo}
                                                       &memberpageno=${memberpageno}'>
                                    <%= pageBoards.get(i).getTitle() %>
                                </a>
                            </td>
                            <%-- <td><%= members.get(i).getMemberId() %></td> --%>
                            <td>
                                <%= members.get(i).getMemberId() %>
                            </td>
                            <td>
                                <%= pageBoards.get(i).getBoarddate() %>
                            </td>
                        </tr>
                        <% } %>



                    </table>
                    <hr>
                    <div class="row">
                        <div class="col-md-4">
                            <input id="search" type="text" />
                            <input id="btn1" type="button" class="btn btn-primary btn-sm">검색</input>
                        </div>
                        <div class="col-md-2">
                            <input id="btn2" type="button" class="btn btn-primary btn-sm">글쓰기</input>
                        </div>

                        <div>
                            <%-- 페이지 번호 출력 --%>
                            <%if (request.getAttribute("pager") != null) { %>
                            <ul class='pager'>
                                <%= request.getAttribute("pager").toString() %>
                            </ul>
                            <% } %>
                        <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<br/><br/><br/><br/>
</body>
</html>