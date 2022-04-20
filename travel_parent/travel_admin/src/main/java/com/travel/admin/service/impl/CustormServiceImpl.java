package com.travel.admin.service.impl;


import com.travel.admin.mapper.CustormMapper;
import com.travel.admin.service.ICustormService;
import com.travel.common.domain.Custorm;
import com.travel.common.query.CustormQuery;
import com.travel.common.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @description: CustormServiceImpl
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CustormServiceImpl implements ICustormService {

    @Autowired
    private CustormMapper custormMapper;

    @Override
    public void regCustorm(Custorm custorm) {
        custorm.setCreatetime(LocalDate.now());
        custorm.setTotalpoints(0L);
        custorm.setHeadImg("tx0.jpg");
        custormMapper.regCustorm(custorm);
    }

    @Override
    public Custorm loginCustorm(Custorm custorm) {
        return custormMapper.loginCustorm(custorm);
    }

    @Override
    public Custorm editSaveUserInfo(Custorm custorm) {
        custormMapper.editSaveUserInfo(custorm);
        return custormMapper.queryCustormById(custorm.getId());
    }

    @Override
    public Custorm updatePwd(Custorm custorm) {
        custormMapper.updatePwd(custorm);
        return custormMapper.queryCustormById(custorm.getId());
    }

    @Override
    public Custorm updateHeadImg(Custorm custorm) {
        custormMapper.updateHeadImg(custorm);
        return custormMapper.queryCustormById(custorm.getId());
    }

    @Override
    public PageList getCustormList(CustormQuery custormQuery) {
        //查询客户的总数
        Long total = custormMapper.queryTotal(custormQuery);
        //查询客户的数据
        List custormList = custormMapper.queryData(custormQuery);
        PageList pageList = new PageList(custormQuery.getCurrentPage(), total, custormList);
        return pageList;
    }
}
