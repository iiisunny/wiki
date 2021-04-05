package com.iiisunny.wiki.mapper;

import com.iiisunny.wiki.model.CategoryModel;
import com.iiisunny.wiki.model.CategoryModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryModelMapper {
    long countByExample(CategoryModelExample example);

    int deleteByExample(CategoryModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CategoryModel record);

    int insertSelective(CategoryModel record);

    List<CategoryModel> selectByExample(CategoryModelExample example);

    CategoryModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CategoryModel record, @Param("example") CategoryModelExample example);

    int updateByExample(@Param("record") CategoryModel record, @Param("example") CategoryModelExample example);

    int updateByPrimaryKeySelective(CategoryModel record);

    int updateByPrimaryKey(CategoryModel record);
}