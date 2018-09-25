package com.eshop.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eshop.common.pojo.EasyUITreeNode;
import com.eshop.mapper.EshopItemCatMapper;
import com.eshop.pojo.EshopItemCatExample.Criteria;
import com.eshop.pojo.EshopItemCat;
import com.eshop.pojo.EshopItemCatExample;
import com.eshop.service.ItemCatService;


/**
 * 商品分类Service,对应数据表eshop_item_cat
 * @author EEP
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private EshopItemCatMapper itemcatmapper;
	
	
	@Override
	public List<EasyUITreeNode> getItemCatList(String parentId) {
	//根据parentId查询商品分类
	EshopItemCatExample example = new EshopItemCatExample();
	//设置查询条件
	Criteria criteria = example.createCriteria();
	criteria.andParentIdEqualTo(parentId);
	List<EshopItemCat> list = itemcatmapper.selectByExample(example);
	//转换成List<EasyUITreeNode>
	List<EasyUITreeNode> list_c = new ArrayList<>();
	for(EshopItemCat EshopItemCat : list){
		//创建节点对象
		EasyUITreeNode node = new EasyUITreeNode();
		node.setId(EshopItemCat.getId());
		node.setText(EshopItemCat.getName());
		//判断是否为父节点1：是 0：否
		if(EshopItemCat.getIsParent()){
			node.setState("closed");
		}else{
			node.setState("open");
		}
		//添加节点
		list_c.add(node);
	}
	return list_c;
	}

}
