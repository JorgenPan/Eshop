package com.eshop.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eshop.common.pojo.EasyUITreeNode;
import com.eshop.common.pojo.EshopResult;
import com.eshop.mapper.EshopContentCategoryMapper;
import com.eshop.pojo.EshopContentCategory;
import com.eshop.pojo.EshopContentCategoryExample;
import com.eshop.pojo.EshopContentCategoryExample.Criteria;

@Service
public class ContentCatgoryServiceImpl implements com.eshop.service.ContentCatgoryService {

	@Autowired
	private EshopContentCategoryMapper contentcatmapper;
	
	
	/**
	 * ��ѯ���ݷ����б�
	 */
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		//����parentId��ѯ�ӽڵ��б�
		EshopContentCategoryExample example = new EshopContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<EshopContentCategory> list = contentcatmapper.selectByExample(example);
		//�����ת����EasyUITreeNode��ʽ
		List<EasyUITreeNode> rList = new ArrayList<>();
		for(EshopContentCategory a : list){
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(a.getId());
			//System.out.println("a.getId()"+a.getId());
			node.setText(a.getName());
			//System.out.println("a.getName()"+a.getName());
			//�ж��Ƿ�Ϊ���ڵ�1���� 0����
			if(a.getIsParent()){
				node.setState("closed");
			}else{
				node.setState("open");
			}
			rList.add(node);
		}
		return rList;
	}
	
	
	/**
	 * �������ݷ���ڵ�
	 */
	@Override
	public EshopResult insertCatgory(Long parentId, String name) {
		EshopContentCategory eshopcontentcat = new EshopContentCategory();
		eshopcontentcat.setName(name);
		eshopcontentcat.setParentId(parentId);
		//1(����),2(ɾ��)
		eshopcontentcat.setStatus(1);
		eshopcontentcat.setIsParent(false);
		eshopcontentcat.setSortOrder(1);
		//ʱ��
		Date date = new Date();
		eshopcontentcat.setCreated(date);
		eshopcontentcat.setUpdated(date);
		//��������
		contentcatmapper.insert(eshopcontentcat);
		//��ѯ���ڵ�
		EshopContentCategory parent = contentcatmapper.selectByPrimaryKey(parentId);
		//�жϸ��ڵ㱾���Ƿ�Ϊ���ڵ㣬���������
		if(!parent.getIsParent()){
			parent.setIsParent(true);
			contentcatmapper.updateByPrimaryKey(parent);
		}
		
		//����������Ϣ
		Long id = eshopcontentcat.getId();
		return EshopResult.ok(id);
	}
	
	/**
	 * �������ݷ���ڵ�
	 */
	@Override
	public EshopResult updateCatgory(Long id,String name) {
		EshopContentCategory eshopcontentcat = new EshopContentCategory();
		eshopcontentcat.setId(id);
		eshopcontentcat.setName(name);
		//ʱ��
		eshopcontentcat.setUpdated(new Date());		
		contentcatmapper.updateByPrimaryKeySelective(eshopcontentcat);
		return EshopResult.ok(id);
	}
	
	
	/**
	 * ɾ�����ݷ���ڵ�
	 */
	@Override
	public EshopResult deleteCatgory(Long id) {
		EshopContentCategory eshopcontentcat = new EshopContentCategory();
		eshopcontentcat.setId(id);
		contentcatmapper.deleteByPrimaryKey(id);
		return EshopResult.ok(id);
	}

}
