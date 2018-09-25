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
	 * excel������Ʒ����
	 */
	public EshopResult LoadInItem(String excelPath) throws IOException{
		List<EshopItem> list = new ArrayList<>();
		FileInputStream fileIn = new FileInputStream("C:\\Users\\EEP\\Desktop\\1.xlsx");
		// ����һ��Excel�ļ�
		Workbook wb = new XSSFWorkbook(fileIn);
		//��ȡ��һ����
		Sheet sheet = wb.getSheetAt(0);
		
		
		/*int minRowIx = sheet.getFirstRowNum();  
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {  
            
             
            }*/
 

        for (Row row: sheet) {
                //��ȡ��Ԫ������
            	//������Ʒʵ����
    			
    			EshopItem item=new EshopItem();
    			//ȡ����ǰ�е�1����Ԫ�����ݣ�����װ��itemʵ��������
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
    			//��integer����ת����byte,1-������2=�¼�
    			int change_b = (int) row.getCell(7).getNumericCellValue();
    			item.setStatus((new Integer(change_b & 0xff).byteValue()));
    			//����
    			item.setCreated(new Date());
    			item.setUpdated(new Date());
    			list.add(item);
    			System.out.println("ִ����forѭ��");
    			 

        }
        
		
		int i = itemMapper.insertBatch(list);
		fileIn.close();
		if(i != 0){
			return EshopResult.ok();
		}
		return EshopResult.build(500, "����ʧ��");
	}
	
	
	
	
}
