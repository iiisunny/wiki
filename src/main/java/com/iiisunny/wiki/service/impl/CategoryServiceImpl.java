package com.iiisunny.wiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iiisunny.wiki.mapper.CategoryModelMapper;
import com.iiisunny.wiki.model.CategoryModel;
import com.iiisunny.wiki.model.CategoryModelExample;
import com.iiisunny.wiki.req.CategoryQuerylReq;
import com.iiisunny.wiki.req.CategorySaveReq;
import com.iiisunny.wiki.resp.CategoryQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.CategoryService;
import com.iiisunny.wiki.util.CopyUtil;
import com.iiisunny.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @description: service接口的实现类
 * @author: iiisunny
 * @create: 2021-03-07 21:58
 **/

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public PageResp<CategoryQueryResp> getList(CategoryQuerylReq req) {

        CategoryModelExample CategoryModelExample = new CategoryModelExample();
        CategoryModelExample.Criteria criteria = CategoryModelExample.createCriteria();

        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());
        List<CategoryModel> categoryModelList = categoryModelMapper.selectByExample(CategoryModelExample);

        PageInfo<CategoryModel> pageInfo = new PageInfo<>(categoryModelList);
        LOG.info("总页数: {}", pageInfo.getTotal());

        List<CategoryQueryResp> list = CopyUtil.copyList(categoryModelList, CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    @Override
    public void save(CategorySaveReq req) {

        // 将请求参数变为实体
        CategoryModel categoryModel = CopyUtil.copy(req, CategoryModel.class);
        if (ObjectUtils.isEmpty(req.getId())){
            // 新增
            categoryModel.setId(snowFlake.nextId());
            categoryModelMapper.insert(categoryModel);
        }else {
            // 更新
            categoryModelMapper.updateByPrimaryKey(categoryModel);
        }
    }

    @Override
    public void delete(Long id) {
        categoryModelMapper.deleteByPrimaryKey(id);
    }
}
