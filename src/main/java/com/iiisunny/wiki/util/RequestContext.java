package com.iiisunny.wiki.util;


import java.io.Serializable;

/**
 * 线程上下文的工具类
 */
public class RequestContext implements Serializable {

    private static ThreadLocal<String> remoteAddr = new ThreadLocal<>();

    public static String getRemoteAddr() {
        return remoteAddr.get();
    }

    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.remoteAddr.set(remoteAddr);
    }

}
