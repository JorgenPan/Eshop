package com.eshop.service.Impl;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.eshop.common.pojo.PictureResult;
import com.eshop.service.PictureService;


/**
 * 图片上传service
 */
@Service
public class PictureServiceImpl implements PictureService {


	@Override
	public PictureResult uploadPic(MultipartFile picFile,HttpServletRequest request) {
		PictureResult result = new PictureResult();
		//判断图片是否为空
		if(picFile.isEmpty()){
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		//上传图片到webapp/images
		try{
			//文件保存s路径
			String path = "E:\\eshop\\upload";
			String filename = picFile.getOriginalFilename();
			File dir = new File(path,filename);
			System.out.println("path:"+path);
			//MultipartFile自带的解析方法 
			picFile.transferTo(dir);
			result.setError(0);
			result.setMessage("上传成功");
			result.setUrl("http://localhost:9090//"+filename);
			return result;
		}catch(Exception e){
			result.setError(1);
			result.setMessage("上传失败");
			return result;
		}

	}

}
