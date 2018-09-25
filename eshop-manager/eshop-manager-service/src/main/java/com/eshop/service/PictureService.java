package com.eshop.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import com.eshop.common.pojo.PictureResult;

public interface PictureService {

	PictureResult uploadPic(MultipartFile picFile,HttpServletRequest request);
}
