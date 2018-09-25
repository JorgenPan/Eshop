package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;
import com.eshop.service.UserService;

@Controller
public class userController {

	@Autowired
	private UserService userservice;
	
	
	/**
	 * ��ȡ�û��б�
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/user/list")
	@ResponseBody
	public EasyUIDataGridResult getUserList(Integer page,Integer rows){
		EasyUIDataGridResult result = userservice.getUserList(page, rows);
		return result;
	}
	
	
	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	@RequestMapping("/rest/user/param/user/query")
	@ResponseBody
	public EshopResult queryUser(Long id){
		EshopResult res = userservice.queryUser(id);
		return res;
	}
	
	/**
	 * �޸��û���Ϣ
	 * @return
	 */
	@RequestMapping("/rest/user/update")
	@ResponseBody
	public EshopResult editUser(Long id,String username,String password,String phone,String email){
		EshopResult res = userservice.editUser(id,username,password,phone,email);
		return res;
	}
	
	
	
	
}	
