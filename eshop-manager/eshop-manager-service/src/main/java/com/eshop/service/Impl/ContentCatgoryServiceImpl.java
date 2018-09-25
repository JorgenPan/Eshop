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
	 * 查询内容分类列表
	 */
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		//根据parentId查询子节点列表
		EshopContentCategoryExample example = new EshopContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<EshopContentCategory> list = contentcatmapper.selectByExample(example);
		//将结果转换成EasyUITreeNode格式
		List<EasyUITreeNode> rList = new ArrayList<>();
		for(EshopContentCategory a : list){
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(a.getId());
			//System.out.println("a.getId()"+a.getId());
			node.setText(a.getName());
			//System.out.println("a.getName()"+a.getName());
			//判断是否为父节点1：是 0：否
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
	 * 新增内容分类节点
	 */
	@Override
	public EshopResult insertCatgory(Long parentId, String name) {
		EshopContentCategory eshopcontentcat = new EshopContentCategory();
		eshopcontentcat.setName(name);
		eshopcontentcat.setParentId(parentId);
		//1(正常),2(删除)
		eshopcontentcat.setStatus(1);
		eshopcontentcat.setIsParent(false);
		eshopcontentcat.setSortOrder(1);
		//时间
		Date date = new Date();
		eshopcontentcat.setCreated(date);
		eshopcontentcat.setUpdated(date);
		//插入数据
		contentcatmapper.insert(eshopcontentcat);
		//查询父节点
		EshopContentCategory parent = contentcatmapper.selectByPrimaryKey(parentId);
		//判断父节点本身是否为父节点，不是则更新
		if(!parent.getIsParent()){
			parent.setIsParent(true);
			contentcatmapper.updateByPrimaryKey(parent);
		}
		
		//返回主键信息
		Long id = eshopcontentcat.getId();
		return EshopResult.ok(id);
	}
	
	/**
	 * 更改内容分类节点
	 */
	@Override
	public EshopResult updateCatgory(Long id,String name) {
		EshopContentCategory eshopcontentcat = new EshopContentCategory();
		eshopcontentcat.setId(id);
		eshopcontentcat.setName(name);
		//时间
		eshopcontentcat.setUpdated(new Date());		
		contentcatmapper.updateByPrimaryKeySelective(eshopcontentcat);
		return EshopResult.ok(id);
	}
	
	
	/**
	 * 删除内容分类节点
	 */
	@Override
	public EshopResult deleteCatgory(Long id) {
		EshopContentCategory eshopcontentcat = new EshopContentCategory();
		eshopcontentcat.setId(id);
		contentcatmapper.deleteByPrimaryKey(id);
		return EshopResult.ok(id);
	}

}
