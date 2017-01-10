package com.studycafe.model.service;

import java.util.List;

import com.studycafe.model.dto.Upload;
import com.studycafe.model.dto.UploadFile;

public interface UploadService {

	int registerUpload(Upload upload);
	void registerUploadFile(UploadFile uploadFile);
	
	List<Upload> getAllUploads(int memberpageno);
	List<UploadFile> getAllUploadFiles(int uploadNo);
	
	Upload searchUploadByUploadNo(int uploadNo);
	UploadFile searchUploadFileByUploadFileNo(int uploadFileNo);
	
	int deleteUpload(int uploadNo);
	
}
