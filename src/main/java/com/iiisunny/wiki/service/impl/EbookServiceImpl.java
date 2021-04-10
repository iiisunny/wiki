package com.iiisunny.wiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iiisunny.wiki.mapper.EbookModelMapper;
import com.iiisunny.wiki.model.EbookModel;
import com.iiisunny.wiki.model.EbookModelExample;
import com.iiisunny.wiki.req.EbookQuerylReq;
import com.iiisunny.wiki.req.EbookSaveReq;
import com.iiisunny.wiki.resp.EbookQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.EbookService;
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
public class EbookServiceImpl implements EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookServiceImpl.class);

    @Autowired
    private EbookModelMapper ebookModelMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public PageResp<EbookQueryResp> getList(EbookQuerylReq req) {

        EbookModelExample ebookModelExample = new EbookModelExample();
        EbookModelExample.Criteria criteria = ebookModelExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }

        if (!ObjectUtils.isEmpty(req.getCategoryId2())){
            criteria.andCategory2IdEqualTo(req.getCategoryId2());
        }
        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());
        List<EbookModel> ebookModelList = ebookModelMapper.selectByExample(ebookModelExample);

        PageInfo<EbookModel> pageInfo = new PageInfo<>(ebookModelList);
        LOG.info("总页数: {}", pageInfo.getTotal());

        List<EbookQueryResp> list = CopyUtil.copyList(ebookModelList, EbookQueryResp.class);

        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    @Override
    public void save(EbookSaveReq req) {

        // 将请求参数变为实体
        EbookModel ebookModel = CopyUtil.copy(req, EbookModel.class);
        if (ObjectUtils.isEmpty(req.getId())){
            // 新增
            ebookModel.setId(snowFlake.nextId());
            ebookModelMapper.insert(ebookModel);
        }else {
            // 更新
            ebookModelMapper.updateByPrimaryKey(ebookModel);
        }
    }

    @Override
    public void delete(Long id) {
        ebookModelMapper.deleteByPrimaryKey(id);
    }
}
