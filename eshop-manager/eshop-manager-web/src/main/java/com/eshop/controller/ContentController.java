package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.pojo.EshopContent;
import com.eshop.service.ContentService;


@Controller
public class ContentController {

	@Autowired
	private ContentService contentservice;
	
	
	
	/**
	 * ɾ�����ݷ���ڵ�
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	//��ȡ��Ŀ�ڵ�id,��ҳ��ʽpage��rows
	public EasyUIDataGridResult queryContentList(Long categoryId,Integer page, Integer rows){
		EasyUIDataGridResult result = contentservice.getContentList(categoryId,page,rows);
		return result;
	}
	
	
	
	
	/**
	 * ����������
	 * @return
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public EshopResult saveContent(EshopContent content){
		EshopResult result = contentservice.saveContent(content);
		return result;
	}
}
