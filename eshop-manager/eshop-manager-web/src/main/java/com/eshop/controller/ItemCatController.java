package com.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.common.pojo.EasyUITreeNode;
import com.eshop.service.ItemCatService;

/**
 * ��Ʒ��������controller
 * @author EEP
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemcatservice;
	
	@RequestMapping("/list")
	@ResponseBody
	//��ȡid��ֵ���Ҹ�ֵ��parentId
	public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") String parentId){
		List<EasyUITreeNode> list = new ArrayList<>();
		list = itemcatservice.getItemCatList(parentId);
		return list;
	}
}
