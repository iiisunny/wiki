package com.iiisunny.wiki.mapper;

import com.iiisunny.wiki.model.ContentModel;
import com.iiisunny.wiki.model.ContentModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContentModelMapper {
    long countByExample(ContentModelExample example);

    int deleteByExample(ContentModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ContentModel record);

    int insertSelective(ContentModel record);

    List<ContentModel> selectByExampleWithBLOBs(ContentModelExample example);

    List<ContentModel> selectByExample(ContentModelExample example);

    ContentModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ContentModel record, @Param("example") ContentModelExample example);

    int updateByExampleWithBLOBs(@Param("record") ContentModel record, @Param("example") ContentModelExample example);

    int updateByExample(@Param("record") ContentModel record, @Param("example") ContentModelExample example);

    int updateByPrimaryKeySelective(ContentModel record);

    int updateByPrimaryKeyWithBLOBs(ContentModel record);
}