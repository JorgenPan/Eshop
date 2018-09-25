package com.eshop.service;

import com.eshop.common.pojo.EasyUIDataGridResult;
import com.eshop.common.pojo.EshopResult;

public interface UserService {
	
	EasyUIDataGridResult getUserList(int page,int rows);
	EshopResult queryUser(Long id);
	EshopResult editUser(Long id,String username, String password, String phone, String email);
}
