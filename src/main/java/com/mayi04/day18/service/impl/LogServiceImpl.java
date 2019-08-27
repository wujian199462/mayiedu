package com.mayi04.day18.service.impl;

import com.mayi04.day18.dao.LogDao;
import com.mayi04.day18.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    //如果当前存在事务就讲当前事务挂起
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addLog() {
        logDao.add("addLog"+System.currentTimeMillis());
    }
}
