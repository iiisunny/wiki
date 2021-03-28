package com.iiisunny.wiki.resp;

import java.util.List;

public class PageResp<T> {

    // 总数据
    private long total;

    // 列表
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}