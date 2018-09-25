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
	 * 删除内容分类节点
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	//获取类目节点id,分页格式page和rows
	public EasyUIDataGridResult queryContentList(Long categoryId,Integer page, Integer rows){
		EasyUIDataGridResult result = contentservice.getContentList(categoryId,page,rows);
		return result;
	}
	
	
	
	
	/**
	 * 保存广告内容
	 * @return
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public EshopResult saveContent(EshopContent content){
		EshopResult result = contentservice.saveContent(content);
		return result;
	}
}
