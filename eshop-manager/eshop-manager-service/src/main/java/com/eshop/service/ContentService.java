package com.eshop.service;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.pojo.EshopContent;

public interface ContentService {

	//����������Ŀid���ҹ��λ������Ϣ
	EasyUIDataGridResult getContentList(Long categoryId,int page, int rows);
	//������������Ϣ
	EshopResult saveContent(EshopContent content);
}
