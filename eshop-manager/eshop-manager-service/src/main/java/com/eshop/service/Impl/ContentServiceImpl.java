package com.eshop.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.mapper.EshopContentMapper;
import com.eshop.pojo.EshopContent;
import com.eshop.pojo.EshopContentExample;
import com.eshop.pojo.EshopItem;
import com.eshop.pojo.EshopContentExample.Criteria;
import com.eshop.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private EshopContentMapper contentMapper;
	//����������Ŀid���ҹ��λ������Ϣ
	@Override
	public EasyUIDataGridResult getContentList(Long categoryId,int page, int rows) {
		EshopContentExample exam = new EshopContentExample();
		Criteria c = exam.createCriteria();
		c.andCategoryIdEqualTo(categoryId);
		List<EshopContent> list = contentMapper.selectByExample(exam);

		
		//��ҳ
		PageHelper.startPage(page,rows);
		//ȡ��ҳ��Ϣ
		PageInfo<EshopContent> pageinfo = new PageInfo<>(list);
		//���ش�����
		EasyUIDataGridResult res = new EasyUIDataGridResult();
		res.setTotal(pageinfo.getTotal());
		res.setRows(list);
		return res;
	}
	
	
	//������������Ϣ
	@Override
	public EshopResult saveContent(EshopContent content) {
        
		EshopContentExample exam = new EshopContentExample();
		Criteria c = exam.createCriteria();
		c.andCategoryIdEqualTo(content.getCategoryId());
		c.andSubTitleEqualTo(content.getSubTitle());
		c.andUrlEqualTo(content.getUrl());
		c.andPicEqualTo(content.getPic());
		List<EshopContent> list = contentMapper.selectByExample(exam);
		
		//�ж����������Ǹ���
		if(list.size() == 0){
			//���UUID��Ϊ��Ʒid��������ֵ
			content.setCreated(new Date());
			content.setUpdated(new Date());
			//����eshop_content��
			contentMapper.insert(content);
		}else{
			content.setUpdated(new Date());
			//����eshop_content��
			contentMapper.updateByPrimaryKeySelective(content);
			}

		return EshopResult.ok();
	}

}
