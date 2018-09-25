<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量打入商品信息</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</head>

<body>

<!-- 配置导入框 -->  
    <div id="importExcel" class="easyui-dialog" title="导入excel文件"  
        style="width: 400px; height: 300px;" data-options="modal:true">  
       <div class="easyui-panel" style="width:400px;padding:30px 70px 50px 70px">
		<div style="margin-bottom:20px">
			<div>所需要导入文件:</div>
			<input class="easyui-filebox" id="uploadExcel" name="file" data-options="prompt:'选择文件'" style="width:100%">
		</div>
		<div>
			<a href="#" class="easyui-linkbutton" style="width:100%" onclick="eshopItemLoad()">导入</a>
		</div>
	</div>  
    </div>
    
    <script type="text/JavaScript">
function eshopItemLoad(){     
    //得到上传文件的全路径  
    var fileName= $('#uploadExcel').filebox('getValue');  
          
            //进行基本校验  
            if(fileName==""){     
               $.messager.alert('提示','请选择上传文件！','info');   
            }else{  
                //对文件格式进行校验  
                var d1=/\.[^\.]+$/.exec(fileName);   
                if(d1==".xls" || d1==".xlsx"){
                	$.ajax({
                		url:"/LeadInEshopItem",
                		data:{"excelPath":"C:\\Users\\EEP\\Desktop\\1.xlsx"},
                		type:"POST",
                    	success:function(data){
                            if(data.status = 200){
                            	$.messager.alert("提示","导入成功");
                            }else{
                            	$.messager.alert("提示","导入失败");
                            }
                        }
                	});
               }else{  
                   $.messager.alert('提示','请选择xls格式文件！','info');   
                   $('#uploadExcel').filebox('setValue','');   
               }  
            }    
            }
  
</script> 
</body>
</html> 