<%@page import="com.studycafe.model.dto.Member" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <title>스터디 등록</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--Jquery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.12.4.js"/>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"/>
    <%--bootstrap--%>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"/>
    <%--css--%>
    <link rel="Stylesheet" href="/studyCafe/resources/styles/input2.css"/>
    <%--Editor--%>
    <script type="text/javascript" src="/studyCafe/resources/js/HuskyEZCreator.js" charset="utf-8"/>

    <script>
        $(function () {
            $("#datepicker1").datepicker({
                dateFormat: 'yy-mm-dd'
            });
        });
    </script>
</head>

<body>
<div id="pageContainer">
    <c:import url="/WEB-INF/views/include/header.jsp"/>

    <div style="padding-top: 30px;">
        <div id="inputcontent">
            <div class="inputsubtitle">스터디 등록</div>


            <form action="write.action" method="post" enctype="multipart/form-data">
                <input type="hidden" name="memberNo"
                       value='${ sessionScope.loginuser.memberNo }'/>
                <table class="table table-striped">
                    <tr>
                        <th style="height: 30px">등록인</th>
                        <td>
                            <input type="hidden" name="uploader"
                                   value='${ sessionScope.loginuser.memberId }'/>
                            ${ sessionScope.loginuser.memberId }
                        </td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="title" placeholder="스터디 룸의 제목을 입력하세요"
                                   style="width: 580px; height: 30px"/>
                        </td>
                    </tr>
                    <tr>
                        <th>카테고리</th>
                        <td>
                            <select name="smallCategoryNo" class="selectpicker" style="height: 30px">
                                <option disabled="disabled" selected="selected">카테고리를 선택하세요</option>
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
                        </td>
                    </tr>
                    <tr>
                        <th>목표</th>
                        <td style="height: 50px">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="자기개발" id="purpose" name="purpose"/>자기개발
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="자격증준비" id="purpose" name="purpose"/>자격증준비
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="취미생활" id="purpose" name="purpose"/>취미생활
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="업무시킬UP" id="purpose" name="purpose"/>업무시킬UP
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="건강/다이어트" id="purpose" name="purpose"/>건강/다이어트
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="기타" id="purpose" name="purpose"/>기타
                        </label></td>
                    </tr>

                    <tr>
                        <th>모집 마감일</th>
                        <td>
                            <input type="text" id="datepicker1" name="closeDate"
                                   placeholder="모집마감 날짜(yyyy-MM-dd)"/>
                        </td>
                    </tr>
                    <tr>
                        <th>기간</th>
                        <td>
                            <select class="btn btn-primary dropdown-toggle" name="period">
                            <option disabled="disabled" selected="selected">진행 기간</option>
                                <option value="1">1개월</option>
                                <option value="2">2개월</option>
                                <option value="3">3개월</option>
                                <option value="4">4개월</option>
                                <option value="5">5개월</option>
                                <option value="6">6개월</option>
                                <option value="7">7개월</option>
                                <option value="8">8개월</option>
                                <option value="9">9개월</option>
                                <option value="10">10개월</option>
                                <option value="11">11개월</option>
                                <option value="12">12개월</option>
                                <option value="12이상">1년 이상</option>
                             </select>
                        </td>
                    </tr>
                    <tr>
                        <th>횟수</th>
                        <td>
                            <input type="text" id="frequency" name="frequency" placeholder="ex: 3주, 3개월 "/>
                            <select>
                                <option disabled="disabled" selected="selected">주기</option>
                                    <option value="주">주</option>
                                    <option value="월">월</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>모집인원 수</th>
                        <td>
                            <input type="text" id="memberCount" name="memberCount"/>
                        </td>
                    </tr>
                    <tr>
                        <th>장소</th>
                        <td>
                            <input type="text" id="place" name="place"/>
                        </td>
                    </tr>
                    <tr>
                        <!-- uploading boardimage -->
                        <th>모임 이미지 업로드</th>
                        <td>
                            <input type="file" id="boardimage" name="boardimage"/>
                        </td>
                    </tr>

                    <tr>
                        <th><label for="ir1">About</label></th>
                        <td>
                            <textarea name="content" id="ir1" rows="10" cols="100"></textarea>
                        </td>
                    </tr>
                </table>

                <div class="buttons" style="text-align: center">
                    <input class="btn btn-default" type="submit" value="자료등록"
                           id="btnSubmit"/>
                    <input class="btn btn-default" type="button" value="취소"
                           onclick="location.href='/studyCafe/home.action';"/>
                </div>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    <%--naver editor api --%>
    $(function () {
        var oEditors = [];
        // 추가 글꼴 목록
        // var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"],
        //                          ["Comic Sans MS", "Comic Sans MS"],
        //                          ["TEST","TEST"]];

        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "ir1", // <textarea>에 적용
            sSkinURI: "/studyCafe/resources/SmartEditor2Skin.html", //skin.html path
            htParams: {
                bUseToolbar: true, // 툴바 사용 여부 (true/false)
                bUseVerticalResizer: true, // 입력창 상하 조절바 사용 여부 (true/false)
                bUseModeChanger: true, // 모드 탭(Editor|HTML|TEXT) 사용 여부 (true/false)
                //aAdditionalFontList : aAdditionalFontSet, // 추가 글꼴 목록
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
            var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
            oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
        }


        function showHTML() {
            var sHTML = oEditors.getById["ir1"].getIR();
            alert(sHTML);
        }

        function submitContents(elClickedObj) {
            oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

            // 에디터의 내용에 대한 값 검증:
            // document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
            try {
                elClickedObj.form.submit();
                /* var url = '/studyCafe/page/board/list.action?menuno=' +
                ${menu.menuNo} + '&memberpageno=' +
                ${memberpageno};
                 $(location).attr('href', url); */
            } catch (e) {
            }
        }

        function setDefaultFont() {
            var sDefaultFont = 'Helvetica';
            var nFontSize = 24;
            oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
        }

        $('#btnSubmit').on('click', function (event) {
            submitContents(this);
        })
    })
</script>
</body>
</html>