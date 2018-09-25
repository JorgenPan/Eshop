package com.eshop.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.mapper.EshopUserMapper;
import com.eshop.pojo.EshopUserExample.Criteria;
import com.eshop.pojo.EshopUser;
import com.eshop.pojo.EshopUserExample;
import com.eshop.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private EshopUserMapper userMapper;
	
	
	/**
	 * �����û��б�
	 */
	@Override
	public EasyUIDataGridResult getUserList(int page, int rows) {
		
		//��ҳ
		PageHelper.startPage(page,rows);
		//ִ�в�ѯ
		EshopUserExample example = new EshopUserExample();
		List<EshopUser> list = userMapper.selectByExample(example);
	    //ȡ��ҳ��Ϣ
		PageInfo<EshopUser> pageinfo = new PageInfo<>(list);
		//���ش�����
		EasyUIDataGridResult res = new EasyUIDataGridResult();
		res.setTotal(pageinfo.getTotal());
		res.setRows(list);
		return res;
	}
	
	/**
	 * �����û�����
	 */
	@Override
	public EshopResult queryUser(Long id) {
		//����id�һ��û�
		EshopUserExample example = new EshopUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		EshopUser user = userMapper.selectByExample(example).get(0);
		return EshopResult.ok(user);
	}
	
	
	
	/**
	 * �޸��û�����
	 */
	@Override
	public EshopResult editUser(Long id,String username, String password, String phone, String email) {
		//����id�һ��û�
		EshopUserExample example = new EshopUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		EshopUser user = userMapper.selectByExample(example).get(0);
		//�޸��û���ֵ
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		user.setUpdated(new Date());
		//��������
		userMapper.updateByPrimaryKeySelective(user);
		return EshopResult.ok();
	}

	

}
