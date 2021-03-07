package com.iiisunny.wiki.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @description:
 * @author: iiisunny
 * @create: 2021-03-07 21:17
 **/
public class CommonUtil {
    public static String processErrorString(BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()+",");
        }
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }
}



