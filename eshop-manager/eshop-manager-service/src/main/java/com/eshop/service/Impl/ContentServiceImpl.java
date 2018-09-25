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
	//根据内容类目id查找广告位内容信息
	@Override
	public EasyUIDataGridResult getContentList(Long categoryId,int page, int rows) {
		EshopContentExample exam = new EshopContentExample();
		Criteria c = exam.createCriteria();
		c.andCategoryIdEqualTo(categoryId);
		List<EshopContent> list = contentMapper.selectByExample(exam);

		
		//分页
		PageHelper.startPage(page,rows);
		//取分页信息
		PageInfo<EshopContent> pageinfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult res = new EasyUIDataGridResult();
		res.setTotal(pageinfo.getTotal());
		res.setRows(list);
		return res;
	}
	
	
	//保存广告内容信息
	@Override
	public EshopResult saveContent(EshopContent content) {
        
		EshopContentExample exam = new EshopContentExample();
		Criteria c = exam.createCriteria();
		c.andCategoryIdEqualTo(content.getCategoryId());
		c.andSubTitleEqualTo(content.getSubTitle());
		c.andUrlEqualTo(content.getUrl());
		c.andPicEqualTo(content.getPic());
		List<EshopContent> list = contentMapper.selectByExample(exam);
		
		//判断是新增还是更新
		if(list.size() == 0){
			//随机UUID作为商品id，并插入值
			content.setCreated(new Date());
			content.setUpdated(new Date());
			//插入eshop_content表
			contentMapper.insert(content);
		}else{
			content.setUpdated(new Date());
			//更新eshop_content表
			contentMapper.updateByPrimaryKeySelective(content);
			}

		return EshopResult.ok();
	}

}
