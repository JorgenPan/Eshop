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
 * 商品查询
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
	 * 根据id查找商品信息
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	private EshopItem getItemById(@PathVariable String itemId){
		EshopItem item = itemservice.getItemById(itemId);
		return item;
	}
	
	/**
	 * 获取商品信息，调用service的分页实现类
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result = itemservice.getItemList(page, rows);
		return result;
	}
	

	/**
	 * 导入excel格式商品数据
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
	 * 接受商品新增的内容
	 * 限定post请求
	 */
	@RequestMapping(value="/item/save", method = RequestMethod.POST)
	@ResponseBody
	public EshopResult addItem(EshopItem item){
		EshopResult result = itemservice.addItem(item);
		return result;
	}
	
	
	/**
	 * 查询商品id对应的商品描述
	 */
	@RequestMapping(value="/rest/item/param/item/query")
	@ResponseBody
	public EshopResult queryItem(String itemId){
		EshopResult result = itemservice.queryItem(itemId);
		return result;
	}
	
	
	/**
	 * 商品删除
	 */
	@RequestMapping(value="/rest/item/delete")
	@ResponseBody
	public EshopResult deleteItem(String itemId){
		System.out.println(itemId);
		EshopResult result = itemservice.deleteItem(itemId);
		return result;
	}
	
	/**
	 * 商品下架
	 */
	@RequestMapping(value="/rest/item/instock")
	@ResponseBody
	public EshopResult instockItem(String itemId){
		EshopResult result = itemservice.instockItem(itemId);
		return result;
	}
	
	/**
	 * 商品上架
	 */
	@RequestMapping(value="/rest/item/reshelf")
	@ResponseBody
	public EshopResult reshelfItem(String itemId){
		EshopResult result = itemservice.reshelfItem(itemId);
		return result;
	}
}
