package com.eshop.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.mapper.EshopItemMapper;
import com.eshop.pojo.EshopItem;
import com.eshop.pojo.EshopItemExample;
import com.eshop.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private EshopItemMapper itemMapper;


	@Override
	public EshopItem getItemById(String itemId) {
		EshopItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	/**
	 * 1.执行查询
	 * 2.分页
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//分页
		PageHelper.startPage(page,rows);
		//执行查询
		EshopItemExample example = new EshopItemExample();
		List<EshopItem> list = itemMapper.selectByExample(example);
		//取分页信息
		PageInfo<EshopItem> pageinfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult res = new EasyUIDataGridResult();
		res.setTotal(pageinfo.getTotal());
		res.setRows(list);
		return res;
	}

	/**
	 * 新增商品以及商品描述
	 * 
	 */
	@Override
	public EshopResult addItem(EshopItem item) {
		//判断是新增还是更新
		if(item.getId() == null){
		//随机UUID作为商品id，并插入值
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		item.setId(id);
		item.setCreated(new Date());
		//商品状态，1-正常，0-下架
		item.setStatus((byte)1);
		item.setUpdated(new Date());
		//插入eshop_item表
		itemMapper.insert(item);
		}else{
		//商品状态，1-正常，0-下架
		item.setStatus((byte)1);
		item.setUpdated(new Date());
		//更新eshop_item表
		itemMapper.updateByPrimaryKeySelective(item);
		}
		return EshopResult.ok();
	}
	
	
	/**
	 * 根据id查找商品对应的商品信息
	 */
	@Override
	public EshopResult queryItem(String itemId) {
		EshopItem item = itemMapper.selectByPrimaryKey(itemId);
		return EshopResult.ok(item);
	}

	/**
	 * 商品下架
	 */
	@Override
	public EshopResult instockItem(String itemId) {
		
		return null;
	}
	
	/**
	 * 商品上架
	 */
	@Override
	public EshopResult reshelfItem(String itemId) {
		
		return null;
	}

	/**
	 * 商品删除
	 */
	@Override
	public EshopResult deleteItem(String itemId) {
		itemMapper.deleteByPrimaryKey(itemId);
		return EshopResult.ok();
	}
	

}
