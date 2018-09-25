package com.eshop.common.pojo;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * �Զ�����Ӧ���
 *
 */
public class EshopResult {
	   // ����jackson����
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // ��Ӧҵ��״̬
    private Integer status;

    // ��Ӧ��Ϣ
    private String msg;

    // ��Ӧ�е�����
    private Object data;

    public static EshopResult build(Integer status, String msg, Object data) {
        return new EshopResult(status, msg, data);
    }

    public static EshopResult ok(Object data) {
        return new EshopResult(data);
    }

    public static EshopResult ok() {
        return new EshopResult(null);
    }

    public EshopResult() {

    }

    public static EshopResult build(Integer status, String msg) {
        return new EshopResult(status, msg, null);
    }

    public EshopResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public EshopResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * ��json�����ת��ΪEshopResult����
     * jsonNode �ǽ������νṹ��  �����ڽ���DOM 
     *  {"age" : 29, 
  	 *   "messages" : [ "msg 1", "msg 2", "msg 3" ], 
  	 *	 "name" : "mkyong" 
  	 *	 }
     * @param jsonData json����
     * @param clazz EshopResult�е�object����
     * @return
     */
    public static EshopResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, EshopResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * û��object�����ת��
     * 
     * @param json
     * @return
     */
    public static EshopResult format(String json) {
        try {
            return MAPPER.readValue(json, EshopResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object�Ǽ���ת��
     * 
     * @param jsonData json����
     * @param clazz �����е�����
     * @return
     */
    public static EshopResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
	
	
}
