package com.studycafe.view;

import com.studycafe.model.dto.UploadFile;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.util.Map;

public class DownloadView extends AbstractView {

	//creating response contents
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
										   HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. Controller에서 저장한 데이터 읽기
		UploadFile file = (UploadFile)model.get("uploadfile");
		
		//다운로드 처리
		// 2. 다운로드 처리할 때 필요한 정보 제공 (브라우저의 다운로드 창에 표시될 파일이름)
		String encodedFileName = new String(file.getUserFileName().getBytes("euc-kr"),
				"iso-8859-1");

		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"Attachment;Filename=\"" + encodedFileName + "\"");


		//3. 파일을 응답스트림에 기록
		// 3.a 세션에서 ServletContext의 path구하기
		// Httpsession으로 할 수 있지만, 파라미터 받는 값에 session없음 -> override된 거여서 parameter 추가 안됨!
		ServletContext application = request.getSession().getServletContext();
		String file2 = application.getRealPath("/WEB-INF/upload/" + file.getSavedFileName());
		// 3.b BufferedS 만들기: BinputS(FinputS(path)), BoutputS(resp.getOutputS())
		BufferedInputStream istream = new BufferedInputStream(new FileInputStream(file2));
		BufferedOutputStream ostream = new BufferedOutputStream(response.getOutputStream());

		while (true) {
			int data = istream.read();
			if (data != -1) {
				ostream.write(data);
			}else {
				break;
			}
		}
		istream.close();
		ostream.close();
	}
}










