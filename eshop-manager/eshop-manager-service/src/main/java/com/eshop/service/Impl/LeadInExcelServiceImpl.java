package com.eshop.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.common.pojo.EshopResult;
import com.eshop.mapper.EshopItemMapper;
import com.eshop.pojo.EshopItem;
import com.eshop.service.LeadInExcelService;


@Service
public class LeadInExcelServiceImpl implements LeadInExcelService {
	
	@Autowired
	private EshopItemMapper itemMapper;

	
	
	/**
	 * excel导入商品数据
	 */
	public EshopResult LoadInItem(String excelPath) throws IOException{
		List<EshopItem> list = new ArrayList<>();
		FileInputStream fileIn = new FileInputStream("C:\\Users\\EEP\\Desktop\\1.xlsx");
		// 创建一个Excel文件
		Workbook wb = new XSSFWorkbook(fileIn);
		//获取第一个表单
		Sheet sheet = wb.getSheetAt(0);
		
		
		/*int minRowIx = sheet.getFirstRowNum();  
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {  
            
             
            }*/
 

        for (Row row: sheet) {
                //获取单元格内容
            	//创建商品实体类
    			
    			EshopItem item=new EshopItem();
    			//取出当前行第1个单元格数据，并封装在item实体属性上
    			item.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    			item.setTitle(row.getCell(0).getStringCellValue());
    			item.setSellPoint(row.getCell(1).getStringCellValue());
    			item.setPrice(new Double(row.getCell(2).getNumericCellValue()).longValue());
    			Double d = row.getCell(3).getNumericCellValue();
    			int chang_d = d.intValue();
    			item.setNum(Integer.valueOf(chang_d));
    			item.setItemDesc(row.getCell(4).getStringCellValue());
    			item.setImage(row.getCell(5).getStringCellValue());
    			item.setCid(new Double(row.getCell(6).getNumericCellValue()).longValue());
    			//将integer类型转换成byte,1-正常，2=下架
    			int change_b = (int) row.getCell(7).getNumericCellValue();
    			item.setStatus((new Integer(change_b & 0xff).byteValue()));
    			//日期
    			item.setCreated(new Date());
    			item.setUpdated(new Date());
    			list.add(item);
    			System.out.println("执行了for循环");
    			 

        }
        
		
		int i = itemMapper.insertBatch(list);
		fileIn.close();
		if(i != 0){
			return EshopResult.ok();
		}
		return EshopResult.build(500, "导入失败");
	}
	
	
	
	
}
