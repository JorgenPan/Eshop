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
	 * 返回用户列表
	 */
	@Override
	public EasyUIDataGridResult getUserList(int page, int rows) {
		
		//分页
		PageHelper.startPage(page,rows);
		//执行查询
		EshopUserExample example = new EshopUserExample();
		List<EshopUser> list = userMapper.selectByExample(example);
	    //取分页信息
		PageInfo<EshopUser> pageinfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult res = new EasyUIDataGridResult();
		res.setTotal(pageinfo.getTotal());
		res.setRows(list);
		return res;
	}
	
	/**
	 * 查找用户数据
	 */
	@Override
	public EshopResult queryUser(Long id) {
		//根据id找回用户
		EshopUserExample example = new EshopUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		EshopUser user = userMapper.selectByExample(example).get(0);
		return EshopResult.ok(user);
	}
	
	
	
	/**
	 * 修改用户数据
	 */
	@Override
	public EshopResult editUser(Long id,String username, String password, String phone, String email) {
		//根据id找回用户
		EshopUserExample example = new EshopUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		EshopUser user = userMapper.selectByExample(example).get(0);
		//修改用户的值
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		user.setUpdated(new Date());
		//更新数据
		userMapper.updateByPrimaryKeySelective(user);
		return EshopResult.ok();
	}

	

}
