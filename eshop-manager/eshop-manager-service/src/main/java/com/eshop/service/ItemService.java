package com.eshop.service;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.pojo.EshopItem;

public interface ItemService {

	EshopItem getItemById(String itemId);
	EasyUIDataGridResult getItemList(int pages,int rows);
	EshopResult addItem(EshopItem item);
	EshopResult queryItem(String itemId);
	EshopResult instockItem(String itemId);
	EshopResult reshelfItem(String itemId);
	EshopResult deleteItem(String itemId);
}
