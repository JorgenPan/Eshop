package com.eshop.service;

import java.util.List;
import com.eshop.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getItemCatList(String parentId);
}
