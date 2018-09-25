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
	 * 获取内容分类列表
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	//获取id的值并且赋值给parentId
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		//System.out.println("parentId"+parentId);
		List<EasyUITreeNode> list = new ArrayList<>();
		list = contentcatservice.getContentCatList(parentId);
		return list;
	}
	
	
	/**
	 * 新增内容分类节点
	 * 
	 * @returns
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	//获取父节点的值和新建节点名字
	public EshopResult createContentCatList(Long parentId,String name){
		EshopResult result = contentcatservice.insertCatgory(parentId,name);
		System.out.println(result.toString());
		return result;
	}
	
	/**
	 * 更新内容分类节点
	 * 
	 * @return
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	//获取父节点的值和新建节点名字
	public EshopResult updateContentCatList(Long id,String name){
		EshopResult result = contentcatservice.updateCatgory(id,name);
		return result;
	}
	
	
	/**
	 * 删除内容分类节点
	 * 
	 * @return
	 */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	//获取父节点的值和新建节点名字
	public EshopResult deleteContentCatList(Long id){
		EshopResult result = contentcatservice.deleteCatgory(id);
		return result;
	}
	
	
	
}
