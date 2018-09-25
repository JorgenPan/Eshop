package com.eshop.service.Impl;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.eshop.common.pojo.PictureResult;
import com.eshop.service.PictureService;


/**
 * ͼƬ�ϴ�service
 */
@Service
public class PictureServiceImpl implements PictureService {


	@Override
	public PictureResult uploadPic(MultipartFile picFile,HttpServletRequest request) {
		PictureResult result = new PictureResult();
		//�ж�ͼƬ�Ƿ�Ϊ��
		if(picFile.isEmpty()){
			result.setError(1);
			result.setMessage("ͼƬΪ��");
			return result;
		}
		//�ϴ�ͼƬ��webapp/images
		try{
			//�ļ�����s·��
			String path = "E:\\eshop\\upload";
			String filename = picFile.getOriginalFilename();
			File dir = new File(path,filename);
			System.out.println("path:"+path);
			//MultipartFile�Դ��Ľ������� 
			picFile.transferTo(dir);
			result.setError(0);
			result.setMessage("�ϴ��ɹ�");
			result.setUrl("http://localhost:9090//"+filename);
			return result;
		}catch(Exception e){
			result.setError(1);
			result.setMessage("�ϴ�ʧ��");
			return result;
		}

	}

}
