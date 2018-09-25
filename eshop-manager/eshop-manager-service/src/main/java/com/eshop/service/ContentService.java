package com.eshop.service;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.pojo.EshopContent;

public interface ContentService {

	//根据内容类目id查找广告位内容信息
	EasyUIDataGridResult getContentList(Long categoryId,int page, int rows);
	//保存广告内容信息
	EshopResult saveContent(EshopContent content);
}
