package com.eshop.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.eshop.common.pojo.PictureResult;
import com.eshop.service.PictureService;

/**
 * ͼƬ�ϴ�controller
 * @author EEP
 *
 */
@Controller
public class PicController {
	@Autowired
	private PictureService picservice;
	@RequestMapping("pic/upload")
	@ResponseBody
	public PictureResult uploadPic(MultipartFile uploadFile,HttpServletRequest request) throws IOException{
		/**
		 * uploadFile��js���Ѿ������õ�ͼƬ��Ϣ
		 * ����service.upload�ϴ�ͼƬ��
		 */
		//System.out.println("request"+request);
		//System.out.println("uploadfile"+uploadFile);
		PictureResult result = picservice.uploadPic(uploadFile,request);
		return result;
	}
}
