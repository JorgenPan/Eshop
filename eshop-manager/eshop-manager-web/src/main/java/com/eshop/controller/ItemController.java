package com.eshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.pojo.EshopItem;
import com.eshop.service.ItemService;
import com.eshop.service.LeadInExcelService;



/**
 * ��Ʒ��ѯ
 * @author EEP
 *
 */
@Controller
public class ItemController {
	
	
	@Autowired
	private ItemService itemservice;
	@Autowired
	private LeadInExcelService exlservice;
	
	/**
	 * ����id������Ʒ��Ϣ
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	private EshopItem getItemById(@PathVariable String itemId){
		EshopItem item = itemservice.getItemById(itemId);
		return item;
	}
	
	/**
	 * ��ȡ��Ʒ��Ϣ������service�ķ�ҳʵ����
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result = itemservice.getItemList(page, rows);
		return result;
	}
	

	/**
	 * ����excel��ʽ��Ʒ����
	 * @throws IOException 
	 */
	@RequestMapping(value="/LeadInEshopItem")
	@ResponseBody
	public EshopResult LeadInEshopItem(String excelPath) throws IOException{
		 System.out.println(excelPath);
		 EshopResult res = exlservice.LoadInItem(excelPath);
        return res;  
	}
	
	/**
	 * ������Ʒ����������
	 * �޶�post����
	 */
	@RequestMapping(value="/item/save", method = RequestMethod.POST)
	@ResponseBody
	public EshopResult addItem(EshopItem item){
		EshopResult result = itemservice.addItem(item);
		return result;
	}
	
	
	/**
	 * ��ѯ��Ʒid��Ӧ����Ʒ����
	 */
	@RequestMapping(value="/rest/item/param/item/query")
	@ResponseBody
	public EshopResult queryItem(String itemId){
		EshopResult result = itemservice.queryItem(itemId);
		return result;
	}
	
	
	/**
	 * ��Ʒɾ��
	 */
	@RequestMapping(value="/rest/item/delete")
	@ResponseBody
	public EshopResult deleteItem(String itemId){
		System.out.println(itemId);
		EshopResult result = itemservice.deleteItem(itemId);
		return result;
	}
	
	/**
	 * ��Ʒ�¼�
	 */
	@RequestMapping(value="/rest/item/instock")
	@ResponseBody
	public EshopResult instockItem(String itemId){
		EshopResult result = itemservice.instockItem(itemId);
		return result;
	}
	
	/**
	 * ��Ʒ�ϼ�
	 */
	@RequestMapping(value="/rest/item/reshelf")
	@ResponseBody
	public EshopResult reshelfItem(String itemId){
		EshopResult result = itemservice.reshelfItem(itemId);
		return result;
	}
}
