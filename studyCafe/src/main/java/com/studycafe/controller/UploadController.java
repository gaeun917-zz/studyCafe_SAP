package com.studycafe.controller;

import com.studycafe.common.Util;
import com.studycafe.model.dto.*;
import com.studycafe.model.service.PageService;
import com.studycafe.model.service.UploadService;
import com.studycafe.view.DownloadView;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/upload/")
public class UploadController implements ApplicationContextAware, BeanNameAware {

	private ApplicationContext context;
	private String beanName;
	@Override
	public void setBeanName(String arg0) {
		this.beanName = arg0;		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;
	}
	
	@Autowired
	@Qualifier("uploadService")
	private UploadService uploadService;
	
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;
	
	@Autowired
	@Qualifier("txManager")
	PlatformTransactionManager txManager;
	
	@Autowired
	@Qualifier("txTemplate")
	TransactionTemplate txTemplate;
	
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session, int memberpageno) {

		Member member = (Member) session.getAttribute("loginuser");
		
		List<Upload> uploads = uploadService.getAllUploads(memberpageno);
		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		ModelAndView mav = new ModelAndView("upload/uploadlist");
		mav.addObject("uploads", uploads);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("pages", pages);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		return mav;
	}
	
	@RequestMapping(value = "write.action", method = RequestMethod.GET)
	public ModelAndView uploadForm(HttpSession session, int memberpageno) {
		
		Member member = (Member) session.getAttribute("loginuser");
		
		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		ModelAndView mav = new ModelAndView("upload/uploadwriteform");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("pages", pages);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		return mav;
	}
	
	@RequestMapping(value = "write.action", method = RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest req, HttpSession session,
							   Upload upload, int memberpageno) {
		
		//processUploadTx(req, upload);
		//processUploadTx2(req, upload);
		//processUploadTx3(req, upload);
		
		Member member = (Member) session.getAttribute("loginuser");
		
		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		upload.setPageNo(memberpageno);
		processUploadTx3(req, upload);

		ModelAndView mav = new ModelAndView("redirect:/upload/list.action");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("pages", pages);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		return mav;
	}
	
	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("uploadno")int uploadNo,
							 HttpSession session, int memberpageno) {

		Upload upload = uploadService.searchUploadByUploadNo(uploadNo);
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		ModelAndView mav = new ModelAndView("upload/uploadview");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("upload", upload);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);

		return mav;
	}
	
	@RequestMapping(value = "download.action", method = RequestMethod.GET)
	public ModelAndView download(@RequestParam("uploadfileno") int uploadFileNo) {

		UploadFile uploadFile =
				uploadService.searchUploadFileByUploadFileNo(uploadFileNo);

		ModelAndView mav = new ModelAndView();
		mav.setView(new DownloadView()); // jsp viewName 아닌데도 가능한가요?
		mav.addObject("uploadfile", uploadFile);

		return mav;
	}
	
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String delete(@RequestParam("uploadno") int uploadNo, int memberpageno) {

		uploadService.deleteUpload(uploadNo);

		//3. 목록으로 이동
		return "redirect:/upload/list.action?memberpageno=" + memberpageno;
	}
	
	public void processUploadTx3(MultipartHttpServletRequest req, Upload upload) {		
				
		try {
			ArrayList<UploadFile> files = new ArrayList<>();

			MultipartFile file = req.getFile("attach");
			if (file != null && file.getSize() > 0) {
				String fileName = file.getOriginalFilename();

				if (fileName.contains("\\")) {
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

				} // else가 없고, if statement끝난거임
				String path = req.getRealPath("/WEB-INF/upload");//실제 파일을 저장할 경로
				String uniqueFileName = Util.getUniqueFileName(path, fileName);

				file.transferTo(new File(path, uniqueFileName));

				UploadFile uploadFile = new UploadFile();
						   uploadFile.setSavedFileName(uniqueFileName);
						   uploadFile.setUserFileName(fileName);
				files.add(uploadFile);
				}

			//Upload insert하고 uploadNo return 받음
			int newUploadNo = uploadService.registerUpload(upload);

			for (UploadFile uf : files) {
					uf.setUploadNo(newUploadNo);
					uploadService.registerUploadFile(uf);//UploadFile insert
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//직접 구현한 advice를 사용한 경우 사용
			//throw new RuntimeException(ex);			
		}
	}
}
