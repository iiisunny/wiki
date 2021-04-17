package com.iiisunny.wiki.mapper;

import com.iiisunny.wiki.model.ebookSnapshotModel;
import com.iiisunny.wiki.model.ebookSnapshotModelExample;
import java.util.List;

import com.iiisunny.wiki.resp.StatisticResp;
import org.apache.ibatis.annotations.Param;

public interface EbookSnapshotModelMapper {
    long countByExample(ebookSnapshotModelExample example);

    int deleteByExample(ebookSnapshotModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ebookSnapshotModel record);

    int insertSelective(ebookSnapshotModel record);

    List<ebookSnapshotModel> selectByExample(ebookSnapshotModelExample example);

    ebookSnapshotModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ebookSnapshotModel record, @Param("example") ebookSnapshotModelExample example);

    int updateByExample(@Param("record") ebookSnapshotModel record, @Param("example") ebookSnapshotModelExample example);

    int updateByPrimaryKeySelective(ebookSnapshotModel record);

    int updateByPrimaryKey(ebookSnapshotModel record);

    void genSnapshot();

    List<StatisticResp> getStatistic();

    List<StatisticResp> get30Statistic();
}