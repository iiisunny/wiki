package com.iiisunny.wiki.service.impl;

import com.iiisunny.wiki.mapper.EbookModelMapper;
import com.iiisunny.wiki.model.EbookModel;
import com.iiisunny.wiki.model.EbookModelExample;
import com.iiisunny.wiki.req.EbookModelReq;
import com.iiisunny.wiki.resp.EbookModelResp;
import com.iiisunny.wiki.service.EbookService;
import com.iiisunny.wiki.util.CopyUtil;
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

    @Autowired
    private EbookModelMapper ebookModelMapper;

    @Override
    public List<EbookModelResp> getList(EbookModelReq req) {
        EbookModelExample ebookModelExample = new EbookModelExample();
        EbookModelExample.Criteria criteria = ebookModelExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        List<EbookModel> ebookModelList = ebookModelMapper.selectByExample(ebookModelExample);

//        List<EbookModelResp> ebookModelRespList = new ArrayList<>();

//        // 使用Spring自带的BeanUtils.copyProperties实现属性的复制
//        for (EbookModel ebookModel : ebookModelList) {
////            EbookModelResp ebookModelResp = new EbookModelResp();
////            BeanUtils.copyProperties(ebookModel, ebookModelResp);
//            EbookModelResp ebookModelResp = CopyUtil.copy(ebookModel, EbookModelResp.class);
//            ebookModelRespList.add(ebookModelResp);
//        }

        List<EbookModelResp> list = CopyUtil.copyList(ebookModelList,EbookModelResp.class);
        return list;
    }

}
