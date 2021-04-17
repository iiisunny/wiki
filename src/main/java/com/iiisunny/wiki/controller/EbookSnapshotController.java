package com.iiisunny.wiki.controller;

import com.iiisunny.wiki.common.CommonRes;
import com.iiisunny.wiki.resp.StatisticResp;
import com.iiisunny.wiki.service.EbookSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

    @Autowired
    private EbookSnapshotService ebookSnapshotService;

    @RequestMapping(value = "/get-statistic", method = RequestMethod.GET)
    public CommonRes getStatistic() {
        List<StatisticResp> statisticResp = ebookSnapshotService.getStatistic();
        CommonRes<List<StatisticResp>> commonResp = new CommonRes<>();
        commonResp.setContent(statisticResp);
        return commonResp;
    }

    @RequestMapping(value = "/get-30-statistic", method = RequestMethod.GET)
    public CommonRes get30Statistic() {
        List<StatisticResp> statisticResp = ebookSnapshotService.get30Statistic();
        CommonRes<List<StatisticResp>> commonResp = new CommonRes<>();
        commonResp.setContent(statisticResp);
        return commonResp;
    }

}
