package com.eshop.service;

import java.io.IOException;

import com.eshop.common.pojo.EshopResult;

public interface LeadInExcelService {
	public EshopResult LoadInItem(String excelPath)throws IOException;
}
