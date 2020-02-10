package com.youyue.framework.exception;

import com.youyue.framework.model.response.ResultCode;

/*
* 统一异常抛出类
* */
public class CustomException extends RuntimeException{
    //错误代码
    ResultCode resultCode;
    public CustomException(ResultCode resultCode){
        this.resultCode=resultCode;
    }
    public ResultCode getResultCode(){
        return resultCode;
    }
}
