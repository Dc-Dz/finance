package com.qianxia.finance.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回类
 */
public class Result {

    // 状态码 100-成功  200-失败
    private Integer code;
    // 提示信息
    private String message;
    // 用户需要返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<>();

    /**
     * 处理成功
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 处理失败
     * @return
     */
    public static Result error(){
        Result result = new Result();
        result.setCode(500);
        result.setMessage("error");
        return result;
    }


    public Result add(String key, Object value){
        this.getExtend().put(key,value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
