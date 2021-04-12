package com.iiisunny.wiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iiisunny.wiki.mapper.UserModelMapper;
import com.iiisunny.wiki.model.UserModel;
import com.iiisunny.wiki.model.UserModelExample;
import com.iiisunny.wiki.req.UserQueryReq;
import com.iiisunny.wiki.req.UserSaveReq;
import com.iiisunny.wiki.resp.UserQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.UserService;
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
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserModelMapper userModelMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public PageResp<UserQueryResp> getList(UserQueryReq req) {

        UserModelExample userModelExample = new UserModelExample();
        UserModelExample.Criteria criteria = userModelExample.createCriteria();

        if (!ObjectUtils.isEmpty(req.getLoginName())){
            criteria.andLoginNameEqualTo(req.getLoginName());
        }
        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());
        List<UserModel> userModelList = userModelMapper.selectByExample(userModelExample);

        PageInfo<UserModel> pageInfo = new PageInfo<>(userModelList);
        LOG.info("总页数: {}", pageInfo.getTotal());

        List<UserQueryResp> list = CopyUtil.copyList(userModelList, UserQueryResp.class);

        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    @Override
    public void save(UserSaveReq req) {

        // 将请求参数变为实体
        UserModel userModel = CopyUtil.copy(req, UserModel.class);
        if (ObjectUtils.isEmpty(req.getId())){
            // 新增
            userModel.setId(snowFlake.nextId());
            userModelMapper.insert(userModel);
        }else {
            // 更新
            userModelMapper.updateByPrimaryKey(userModel);
        }
    }

    @Override
    public void delete(Long id) {
        userModelMapper.deleteByPrimaryKey(id);
    }
}
