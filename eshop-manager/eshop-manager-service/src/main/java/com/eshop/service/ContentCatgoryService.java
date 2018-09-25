package com.eshop.service;

import java.util.List;

import com.eshop.common.pojo.EasyUITreeNode;
import com.eshop.common.pojo.EshopResult;

public interface ContentCatgoryService {

	List<EasyUITreeNode> getContentCatList(Long parentId);
	EshopResult insertCatgory(Long parentId,String name);
	EshopResult updateCatgory(Long id,String name);
	EshopResult deleteCatgory(Long id);
}
