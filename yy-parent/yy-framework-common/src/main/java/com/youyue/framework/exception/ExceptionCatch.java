package com.youyue.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.youyue.framework.model.response.CommonCode;
import com.youyue.framework.model.response.ResponseResult;
import com.youyue.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/*
* 统一异常铺获类
* */
@ControllerAdvice  //控制器增强
public class ExceptionCatch {
    private static final Logger LOGGER= LoggerFactory.getLogger(ExceptionCast.class);
    //定义map 配置异常类型对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //定义map的bulid对象，去构建ImmutableMap
    protected  static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder=ImmutableMap.builder();
    //获取CustomException此类型的异常(运行期)
    @ExceptionHandler(CustomException.class)
    @ResponseBody //需要将错误信息转换为json
    public ResponseResult customException(CustomException customException){
        //记录日志
        LOGGER.error("catch exception:{}",customException.getMessage());
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }
    //捕获编译器异常
    @ExceptionHandler(Exception.class)
    @ResponseBody //需要将错误信息转换为json
    public ResponseResult exception(Exception exception){
        //记录日志
        LOGGER.error("catch exception:{}",exception.getMessage());
        if(EXCEPTIONS==null){
            EXCEPTIONS=builder.build();//EXCEPTIONS构建成功
        }
        ResultCode resultCode=EXCEPTIONS.get(exception.getClass());
        if(resultCode!=null){
            return new ResponseResult(resultCode);
        }else {
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }

    }

    static {
        //定义异常类型对应的错误代码
        //在这里加入一些基础的异常类型判断
        builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
    }
}
