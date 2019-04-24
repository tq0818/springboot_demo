package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

public class BaseService {

    private static final long serialVersionUID = 6357869213649815390L;

    /**
     * 得到32位的uuid
     *
     * @return
     */
    public String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * @param fastJson
     */
    protected JSONObject json = new JSONObject();
    /**
     * fastjson JSONArray
     */
    protected JSONArray jsonArray = new JSONArray();
    /**
     * fastjson用法
     * 对象转json字符串 String json = json.toJSONString(对象);
     * 字符串转json对象 json =json.parseObject(jsonStr);
     * 字符串转java对象 Object object = JSON.parseObject(jsonStr, Object.class);
     * 字符串转list  List<Object> list = JSON.parseArray(jsonStr, Object.class);
     */
}
