package com.studycafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.studycafe.model.dao.UploadDao;
import com.studycafe.model.dto.Upload;
import com.studycafe.model.dto.UploadFile;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {

	@Autowired
	@Qualifier("oracleUploadDao")
	private UploadDao uploadDao;

	@Override
	public int registerUpload(Upload upload) {
		return uploadDao.insertUpload(upload);
	}

	@Override
	public void registerUploadFile(UploadFile uploadFile) {
		uploadDao.insertUploadFile(uploadFile);
	}

	@Override
	public List<Upload> getAllUploads(int memberpageno) {
		return uploadDao.getUploadList(memberpageno);
	}

	@Override
	public List<UploadFile> getAllUploadFiles(int uploadNo) {
		return uploadDao.getUploadFilesByUploadNo(uploadNo);
	}

	@Override
	public Upload searchUploadByUploadNo(int uploadNo) {
		return uploadDao.getUploadByUploadNo(uploadNo);
	}

	@Override
	public UploadFile searchUploadFileByUploadFileNo(int uploadFileNo) {
		return uploadDao.getUploadFileByUploadFileNo(uploadFileNo);
	}

	@Override
	public int deleteUpload(int uploadNo) {
		return uploadDao.deleteUpload(uploadNo);
	}
	
}
