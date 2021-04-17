package com.iiisunny.wiki.service.impl;

import com.iiisunny.wiki.mapper.EbookSnapshotModelMapper;
import com.iiisunny.wiki.resp.StatisticResp;
import com.iiisunny.wiki.service.EbookSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookSnapshotServiceImpl implements EbookSnapshotService {

    @Autowired
    private EbookSnapshotModelMapper ebookSnapshotModelMapper;

    @Override
    public void genSnapshot() {
        ebookSnapshotModelMapper.genSnapshot();
    }

    @Override
    public List<StatisticResp> getStatistic() {
        return ebookSnapshotModelMapper.getStatistic();
    }

    @Override
    public List<StatisticResp> get30Statistic() {
        return ebookSnapshotModelMapper.get30Statistic();
    }
}
