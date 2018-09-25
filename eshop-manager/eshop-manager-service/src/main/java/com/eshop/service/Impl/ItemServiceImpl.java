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
	 * 1.ִ�в�ѯ
	 * 2.��ҳ
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//��ҳ
		PageHelper.startPage(page,rows);
		//ִ�в�ѯ
		EshopItemExample example = new EshopItemExample();
		List<EshopItem> list = itemMapper.selectByExample(example);
		//ȡ��ҳ��Ϣ
		PageInfo<EshopItem> pageinfo = new PageInfo<>(list);
		//���ش�����
		EasyUIDataGridResult res = new EasyUIDataGridResult();
		res.setTotal(pageinfo.getTotal());
		res.setRows(list);
		return res;
	}

	/**
	 * ������Ʒ�Լ���Ʒ����
	 * 
	 */
	@Override
	public EshopResult addItem(EshopItem item) {
		//�ж����������Ǹ���
		if(item.getId() == null){
		//���UUID��Ϊ��Ʒid��������ֵ
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		item.setId(id);
		item.setCreated(new Date());
		//��Ʒ״̬��1-������0-�¼�
		item.setStatus((byte)1);
		item.setUpdated(new Date());
		//����eshop_item��
		itemMapper.insert(item);
		}else{
		//��Ʒ״̬��1-������0-�¼�
		item.setStatus((byte)1);
		item.setUpdated(new Date());
		//����eshop_item��
		itemMapper.updateByPrimaryKeySelective(item);
		}
		return EshopResult.ok();
	}
	
	
	/**
	 * ����id������Ʒ��Ӧ����Ʒ��Ϣ
	 */
	@Override
	public EshopResult queryItem(String itemId) {
		EshopItem item = itemMapper.selectByPrimaryKey(itemId);
		return EshopResult.ok(item);
	}

	/**
	 * ��Ʒ�¼�
	 */
	@Override
	public EshopResult instockItem(String itemId) {
		
		return null;
	}
	
	/**
	 * ��Ʒ�ϼ�
	 */
	@Override
	public EshopResult reshelfItem(String itemId) {
		
		return null;
	}

	/**
	 * ��Ʒɾ��
	 */
	@Override
	public EshopResult deleteItem(String itemId) {
		itemMapper.deleteByPrimaryKey(itemId);
		return EshopResult.ok();
	}
	

}
