package com.xibu.tickets.dto;

import java.util.Objects;

/**
 * @author 外哥
 * @Description:
 * @email : liwai2012220663@163.com
 * @date 2021/1/31 10:45
 */
public class ResultObject {

    private Integer code ;
    private String msg ;
    private Object data ;

    public ResultObject(int code, String msg) {
        this.code = code ;
        this.msg = msg ;
    }

    public ResultObject(int code, String msg , Object data) {
        this.code = code ;
        this.msg = msg ;
        this.data = data ;
    }

    public ResultObject(int code, Object data) {
        this.code = code ;
        this.data = data ;
    }

    public ResultObject(Object obj) {
        this.data = obj ;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultObject that = (ResultObject) o;
        return code.equals(that.code) &&
                msg.equals(that.msg) &&
                data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
}
