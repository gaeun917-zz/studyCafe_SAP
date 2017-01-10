package com.studycafe.model.mapper;

import java.util.List;

import com.studycafe.model.dto.Upload;
import com.studycafe.model.dto.UploadFile;

public interface UploadMapper {

	void insertUpload(Upload upload);
	void insertUploadFile(UploadFile uploadFile);
	
	List<Upload> selectUploadList(int memberpageno);
	List<UploadFile> selectUploadFilesByUploadNo(int uploadNo);
	
	Upload selectUploadByUploadNo(int uploadNo);
	UploadFile selectUploadFileByUploadFileNo(int uploadFileNo);
	
	void increaseUploadReadCount(int uploadNo);
	void increaseUploadFileDownloadCount(int uploadFileNo);
	
	Upload selectUploadByUploadNo2(int uploadNo);
	Upload selectUploadByUploadNo3(int uploadNo);
	
	void deleteBoard(int uploadNo);
	
	
}
