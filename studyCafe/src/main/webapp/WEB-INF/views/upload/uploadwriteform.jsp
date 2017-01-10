<%@page import="com.studycafe.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<title>자료업로드</title>
<!-- <meta charset="utf-8" />
<title>자료업로드</title>
<link rel="Stylesheet" href="/studyCafe/resources/styles/default.css" />
<link rel="Stylesheet" href="/studyCafe/resources/styles/input2.css" /> -->

<!-- <link href="/studyCafe/resources/bootstrap/css/kfonts2.css" rel="stylesheet"> -->
<link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/studyCafe/resources/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(function() {
		var oEditors = [];

		// 추가 글꼴 목록
		//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "ir1",
			sSkinURI : "/studyCafe/resources/SmartEditor2Skin.html",
			htParams : {
				bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function() {
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function() {
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator : "createSEditor2"
		});
		function pasteHTML() {
			var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
			oEditors.getById["ir1"].exec("PASTE_HTML", [ sHTML ]);
		}

		function showHTML() {
			var sHTML = oEditors.getById["ir1"].getIR();
			alert(sHTML);
		}

		function submitContents(elClickedObj) {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

			// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
			try {
				elClickedObj.form.submit();
				/* var url = '/studyCafe/page/board/list.action?menuno=' + ${menu.menuNo} + '&memberpageno=' + ${memberpageno}; 
		    	$(location).attr('href', url); */
			} catch (e) {
			}
		}
		function setDefaultFont() {
			var sDefaultFont = '궁서';
			var nFontSize = 24;
			oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
		}

		$('#btnSubmit').on('click', function(event) {
			submitContents(this);
		})

	})
</script>
</head>

<body>

		<div>
			<c:import url="/WEB-INF/views/page/index.jsp" />
			<div class="col-xs-12 col-sm-9" style="text-align: center;">
				    <div class="panel panel-default">
						<div class="panel-heading"><h4><strong>자료 등록</strong></h4></div>
				        <!-- <div class="inputsubtitle">업로드 자료 정보</div> -->
					        <form action="write.action"
					        	method="post" enctype="multipart/form-data">
						        <table class="table table-bordered">
						        	<thead>
							            <tr>
							                <th style="text-align: center">제목</th>
							                <td>
							                    <input type="text" name="title" style="width:100%" />
							                    <input type="hidden" name="noticemenu" value="${noticemenu.menuNo}" />
												<input type="hidden" name="memberpageno" value="${memberpageno}" />
							                </td>
							            </tr>
							            <tr>
							                <th style="text-align: center">작성자</th>
							                <td style="text-align: left;">
							                	<input type="hidden" name="memberNo" value='${ sessionScope.loginuser.memberNo }' />
							                	${ sessionScope.loginuser.memberId }
							                </td>
							            </tr>
							            <tr>
							                <th style="text-align: center">첨부자료</th>
							                <td>
							                    <input type="file" name="attach" style="width:100%; height:25px" />
							                </td>
							            </tr>
							            <tr align="center" valign="middle">
							                <th style="text-align: center;" valign="top">자료설명</th>
							                <td>
							                	<textarea name="content" id="ir1" rows="10" cols="100"
													style="width: 100%; height: 300px; display: none;">
												</textarea>
							                </td>
							            </tr>
						            </thead>
						        </table>
						        <div class="buttons" style="text-align: center;">
									<button type="submit" value="자료등록" class="btn btn-primary btn-sm" id="btnSubmit"
										onclick="document.forms[0].submit();">자료등록</button>	
									<button type="button" value="취소" class="btn btn-primary btn-sm"
										onclick="location.href='list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}';">취소</button>		        
						        </div>
						        <br/>
					        </form>
					    </div>
					</div>   	
				</div>
			</div>
		</div>
	</div>
	<br/><br/><br/><br/>
</body>
</html>