package com.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.common.pojo.EasyUITreeNode;
import com.eshop.common.pojo.EshopResult;
import com.eshop.pojo.EshopContentCategory;
import com.eshop.service.ContentCatgoryService;

@Controller
public class ContentCatgoryController {
	@Autowired
	public ContentCatgoryService contentcatservice;
	
	/**
	 * ��ȡ���ݷ����б�
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	//��ȡid��ֵ���Ҹ�ֵ��parentId
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		//System.out.println("parentId"+parentId);
		List<EasyUITreeNode> list = new ArrayList<>();
		list = contentcatservice.getContentCatList(parentId);
		return list;
	}
	
	
	/**
	 * �������ݷ���ڵ�
	 * 
	 * @returns
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	//��ȡ���ڵ��ֵ���½��ڵ�����
	public EshopResult createContentCatList(Long parentId,String name){
		EshopResult result = contentcatservice.insertCatgory(parentId,name);
		System.out.println(result.toString());
		return result;
	}
	
	/**
	 * �������ݷ���ڵ�
	 * 
	 * @return
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	//��ȡ���ڵ��ֵ���½��ڵ�����
	public EshopResult updateContentCatList(Long id,String name){
		EshopResult result = contentcatservice.updateCatgory(id,name);
		return result;
	}
	
	
	/**
	 * ɾ�����ݷ���ڵ�
	 * 
	 * @return
	 */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	//��ȡ���ڵ��ֵ���½��ڵ�����
	public EshopResult deleteContentCatList(Long id){
		EshopResult result = contentcatservice.deleteCatgory(id);
		return result;
	}
	
	
	
}
