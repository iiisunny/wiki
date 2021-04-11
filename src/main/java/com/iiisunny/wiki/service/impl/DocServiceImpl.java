package com.iiisunny.wiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iiisunny.wiki.mapper.DocModelMapper;
import com.iiisunny.wiki.model.DocModel;
import com.iiisunny.wiki.model.DocModelExample;
import com.iiisunny.wiki.req.DocQuerylReq;
import com.iiisunny.wiki.req.DocSaveReq;
import com.iiisunny.wiki.resp.DocQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.DocService;
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
public class DocServiceImpl implements DocService {

    private static final Logger LOG = LoggerFactory.getLogger(DocServiceImpl.class);

    @Autowired
    private DocModelMapper docModelMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public PageResp<DocQueryResp> list(DocQuerylReq req) {

        DocModelExample docModelExample = new DocModelExample();
        docModelExample.setOrderByClause("sort asc");
        DocModelExample.Criteria criteria = docModelExample.createCriteria();

        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());
        List<DocModel> docModelList = docModelMapper.selectByExample(docModelExample);

        PageInfo<DocModel> pageInfo = new PageInfo<>(docModelList);
        LOG.info("总页数: {}", pageInfo.getTotal());

        List<DocQueryResp> list = CopyUtil.copyList(docModelList, DocQueryResp.class);

        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    @Override
    public List<DocQueryResp> all() {

        DocModelExample docModelExample = new DocModelExample();
        docModelExample.setOrderByClause("sort asc");
        List<DocModel> docModelList = docModelMapper.selectByExample(docModelExample);
        List<DocQueryResp> list = CopyUtil.copyList(docModelList, DocQueryResp.class);

        return list;
    }

    @Override
    public void save(DocSaveReq req) {

        // 将请求参数变为实体
        DocModel docModel = CopyUtil.copy(req, DocModel.class);
        if (ObjectUtils.isEmpty(req.getId())){
            // 新增
            docModel.setId(snowFlake.nextId());
            docModelMapper.insert(docModel);
        }else {
            // 更新
            docModelMapper.updateByPrimaryKey(docModel);
        }
    }

    @Override
    public void delete(Long id) {
        docModelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void delete(List<String> ids) {
        DocModelExample docModelExample = new DocModelExample();
        DocModelExample.Criteria criteria = docModelExample.createCriteria();
        criteria.andIdIn(ids);
        docModelMapper.deleteByExample(docModelExample);
    }
}
