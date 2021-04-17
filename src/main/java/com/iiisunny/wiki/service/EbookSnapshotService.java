package com.iiisunny.wiki.service;

import com.iiisunny.wiki.resp.StatisticResp;

import java.util.List;

public interface EbookSnapshotService {

    void genSnapshot();

    /**
     * 获取首页数值数据：总阅读数、总点赞数、今日阅读数、今日点赞数、今日预计阅读数、今日预计阅读增长
     */
    List<StatisticResp> getStatistic();

    /**
     * 30天数值统计
     */
    List<StatisticResp> get30Statistic();

}
