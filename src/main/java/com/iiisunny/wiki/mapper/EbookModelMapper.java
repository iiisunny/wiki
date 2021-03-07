package com.iiisunny.wiki.mapper;

import com.iiisunny.wiki.model.EbookModel;
import com.iiisunny.wiki.model.EbookModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EbookModelMapper {
    long countByExample(EbookModelExample example);

    int deleteByExample(EbookModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EbookModel record);

    int insertSelective(EbookModel record);

    List<EbookModel> selectByExample(EbookModelExample example);

    EbookModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EbookModel record, @Param("example") EbookModelExample example);

    int updateByExample(@Param("record") EbookModel record, @Param("example") EbookModelExample example);

    int updateByPrimaryKeySelective(EbookModel record);

    int updateByPrimaryKey(EbookModel record);
}