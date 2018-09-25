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
 * ��Ʒ����Service,��Ӧ���ݱ�eshop_item_cat
 * @author EEP
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private EshopItemCatMapper itemcatmapper;
	
	
	@Override
	public List<EasyUITreeNode> getItemCatList(String parentId) {
	//����parentId��ѯ��Ʒ����
	EshopItemCatExample example = new EshopItemCatExample();
	//���ò�ѯ����
	Criteria criteria = example.createCriteria();
	criteria.andParentIdEqualTo(parentId);
	List<EshopItemCat> list = itemcatmapper.selectByExample(example);
	//ת����List<EasyUITreeNode>
	List<EasyUITreeNode> list_c = new ArrayList<>();
	for(EshopItemCat EshopItemCat : list){
		//�����ڵ����
		EasyUITreeNode node = new EasyUITreeNode();
		node.setId(EshopItemCat.getId());
		node.setText(EshopItemCat.getName());
		//�ж��Ƿ�Ϊ���ڵ�1���� 0����
		if(EshopItemCat.getIsParent()){
			node.setState("closed");
		}else{
			node.setState("open");
		}
		//��ӽڵ�
		list_c.add(node);
	}
	return list_c;
	}

}
