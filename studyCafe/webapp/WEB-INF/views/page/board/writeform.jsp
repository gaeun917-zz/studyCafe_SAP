<%@page import="com.studycafe.model.dto.Member" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>글쓰기</title>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
    <%--<link rel="Stylesheet" href="/studyCafe/resources/styles/input2.css" />--%>
    <link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/studyCafe/resources/bootstrap/css/kfonts2.css" rel="stylesheet">
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.12.4.min.js" charset="utf-8"></script>
    <%--Calendar --%>
    <script type="text/javascript"
            src="/studyCafe/resources/js/HuskyEZCreator.js" charset="utf-8"></script>

    <script type="text/javascript">
        $(function () {
            var oEditors = [];
            // 추가 글꼴 목록
            //var aAdditionalFontSet =  [["MS UI Gothic", "MS UI Gothic"],
            //                          ["Comic Sans MS", "Comic Sans MS"],
            //                          ["TEST","TEST"]];

            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "ir1",
                sSkinURI: "/studyCafe/resources/SmartEditor2Skin.html",
                htParams: {
                    bUseToolbar: true,
                    bUseVerticalResizer: true, // 입력창 크기 조절바 사용 여부
                    bUseModeChanger: true, // 모드 탭(Editor | HTML | TEXT) 사용 여부
                    //aAdditionalFontList : aAdditionalFontSet,	// 추가 글꼴 목록
                    fOnBeforeUnload: function () {
                        //alert("완료!");
                    }
                }, //boolean
                fOnAppLoad: function () {
                    //oEditors.getById["ir1"].exec("PASTE_HTML",
                    // ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
                },
                fCreator: "createSEditor2"
            });

            function pasteHTML() {
                var sHTML = "<span style='color:#FF0000;'>" +
                                  "이미지도 같은 방식으로 삽입합니다.<\/span>";
                oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
            }

            function showHTML() {
                var sHTML = oEditors.getById["ir1"].getIR();
                alert(sHTML);
            }

            function submitContents(elClickedObj) {
                oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

                // 에디터의 내용에 대한 값 검증; document.getElementById("ir1").value
                try {
                    elClickedObj.form.submit();
                    <%--var url = '/studyCafe/page/board/list.action?menuno=' + --%>
                    <%--${menu.menuNo} + '&memberpageno=' + ${memberpageno}; --%>
                    <%--$(location).attr('href', url); --%>
                } catch (e) {
                }
            }

            function setDefaultFont() {
                var sDefaultFont = '궁서';
                var nFontSize = 24;
                oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
            }

            $('#btnSubmit').on('click', function (event) {
                submitContents(this);
            });
        });
    </script>
</head>


<body>
<div class="page_board_writeform">
    <c:import url="/WEB-INF/views/page/index.jsp"/>
    <div class="col-xs-12 col-sm-9" style="text-align: left;">


        <form action="writeform.action" method="post">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>
                        <strong>${menu.name}</strong>
                    </h4>
                </div>

                <table class="table table-bordered">
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="title" style="width: 100%;"/>
                            <input type="hidden" name="menuno" value="${menu.menuNo}"/>
                            <input type="hidden" name="memberpageno" value="${memberpageno}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>
                            <%=((Member) session.getAttribute("loginuser")).getName()%>
                        </td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td s>
                            <textarea name="content" id="ir1" rows="10" cols="100"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="buttons">
                <input type="button" id="btnSubmit" value="글쓰기"/> &nbsp;&nbsp;
                    <a href="/studyCafe/page/board/list.action?menuno=${menu.menuNo}&memberpageno=${memberpageno}">목록보기</a>
            </div>
        </form>
    </div>
</div>
<br/><br/><br/><br/>
</body>
</html>