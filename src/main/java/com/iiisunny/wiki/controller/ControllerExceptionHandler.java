package com.iiisunny.wiki.controller;

import com.iiisunny.wiki.common.CommonRes;
import com.iiisunny.wiki.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonRes validExceptionHandler(BindException e) {
        CommonRes CommonRes = new CommonRes();
        LOG.warn("参数校验失败：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        CommonRes.setSuccess(false);
        CommonRes.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return CommonRes;
    }

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonRes validExceptionHandler(BusinessException e) {
        CommonRes CommonRes = new CommonRes();
        LOG.warn("业务异常：{}", e.getCode().getDesc());
        CommonRes.setSuccess(false);
        CommonRes.setMessage(e.getCode().getDesc());
        return CommonRes;
    }

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonRes validExceptionHandler(Exception e) {
        CommonRes CommonRes = new CommonRes();
        LOG.error("系统异常：", e);
        CommonRes.setSuccess(false);
        CommonRes.setMessage("系统出现异常，请联系管理员");
        return CommonRes;
    }
}
