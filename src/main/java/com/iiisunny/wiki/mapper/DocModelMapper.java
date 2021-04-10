package com.iiisunny.wiki.mapper;

import com.iiisunny.wiki.model.DocModel;
import com.iiisunny.wiki.model.DocModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocModelMapper {
    long countByExample(DocModelExample example);

    int deleteByExample(DocModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DocModel record);

    int insertSelective(DocModel record);

    List<DocModel> selectByExample(DocModelExample example);

    DocModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DocModel record, @Param("example") DocModelExample example);

    int updateByExample(@Param("record") DocModel record, @Param("example") DocModelExample example);

    int updateByPrimaryKeySelective(DocModel record);

    int updateByPrimaryKey(DocModel record);
}