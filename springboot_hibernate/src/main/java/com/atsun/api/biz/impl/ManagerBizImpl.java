package com.atsun.api.biz.impl;

import com.atsun.api.biz.ManagerBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ManagerBizImpl implements ManagerBiz {
}