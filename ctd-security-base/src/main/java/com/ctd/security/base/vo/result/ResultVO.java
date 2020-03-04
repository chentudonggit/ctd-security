package com.ctd.security.base.vo.result;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 返回
 *
 * @author chentudong
 * @date 2020/3/4 9:14 上午
 * @since 1.0
 */
public class ResultVO<T> implements Serializable
{
    /**
     * 响应业务状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应中的数据
     */
    private T data;

    public ResultVO()
    {
    }

    public ResultVO(T data)
    {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    public ResultVO(String message, T data)
    {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public ResultVO(Integer code, String message, T data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultVO ok()
    {
        return new ResultVO<>(null);
    }

    public static ResultVO ok(String message)
    {
        return new ResultVO<>(message, null);
    }

    public static ResultVO ok(Object data)
    {
        return new ResultVO<>(data);
    }

    public static <T> ResultVO ok(String message, Class<T> data)
    {
        return new ResultVO<>(message, data);
    }

    public static ResultVO build(Integer code, String message)
    {
        return new ResultVO<>(code, message, null);
    }

    public static <T> ResultVO build(Integer code, String message, T data)
    {
        return new ResultVO<>(code, message, data);
    }

    public String toJsonString()
    {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 ResultVO 对象
     *
     * @param json json
     * @return ResultVO
     */
    public static ResultVO format(String json)
    {
        try
        {
            return JSON.parseObject(json, ResultVO.class);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
