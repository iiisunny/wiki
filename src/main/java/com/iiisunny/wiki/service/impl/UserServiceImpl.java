package com.iiisunny.wiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iiisunny.wiki.exception.BusinessException;
import com.iiisunny.wiki.exception.BusinessExceptionCode;
import com.iiisunny.wiki.mapper.UserModelMapper;
import com.iiisunny.wiki.model.UserModel;
import com.iiisunny.wiki.model.UserModelExample;
import com.iiisunny.wiki.req.UserLoginReq;
import com.iiisunny.wiki.req.UserQueryReq;
import com.iiisunny.wiki.req.UserResetPasswordReq;
import com.iiisunny.wiki.req.UserSaveReq;
import com.iiisunny.wiki.resp.UserLoginResp;
import com.iiisunny.wiki.resp.UserQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.UserService;
import com.iiisunny.wiki.util.CopyUtil;
import com.iiisunny.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
            UserModel userDB = selectByLoginName(req.getLoginName());
            if (ObjectUtils.isEmpty(userDB)){
                // 新增
                userModel.setId(snowFlake.nextId());
                userModelMapper.insert(userModel);
            }else {
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        }else {
            // 更新
            userModel.setLoginName(null);
            userModel.setPassword(null);
            userModelMapper.updateByPrimaryKeySelective(userModel);
        }
    }

    @Override
    public void delete(Long id) {
        userModelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserModel selectByLoginName(String loginName) {
        UserModelExample userModelExample = new UserModelExample();
        UserModelExample.Criteria criteria = userModelExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<UserModel> userModelList = userModelMapper.selectByExample(userModelExample);
        if (CollectionUtils.isEmpty(userModelList)){
            return null;
        }else {
            return userModelList.get(0);
        }
    }

    @Override
    public void resetPassword(UserResetPasswordReq req) {
        // 将请求参数变为实体
        UserModel userModel = CopyUtil.copy(req, UserModel.class);
        userModelMapper.updateByPrimaryKeySelective(userModel);
    }

    @Override
    public UserLoginResp login(UserLoginReq req) {
        UserModel userDB = selectByLoginName(req.getLoginName());
        if (ObjectUtils.isEmpty(userDB)){
            // 用户名不存在
            LOG.info("用户名不存在，{}", req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }else {
            if (userDB.getPassword().equals(req.getPassword())){
                // 登录成功
                UserLoginResp userLoginResp = CopyUtil.copy(userDB, UserLoginResp.class);
                return userLoginResp;
            }else {
                // 密码不对
                LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", req.getPassword(), userDB.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }

    }
}
