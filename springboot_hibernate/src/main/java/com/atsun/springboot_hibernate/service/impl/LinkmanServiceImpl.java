package com.atsun.springboot_hibernate.service.impl;

import com.atsun.springboot_hibernate.dao.LinkmanRepository;
import com.atsun.springboot_hibernate.entity.Linkman;
import com.atsun.springboot_hibernate.service.LinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkmanServiceImpl implements LinkmanService {
    @Autowired
    private LinkmanRepository linkmanRepository;


    @Override
    public Linkman select(Integer id) {
        return linkmanRepository.getById(id);
    }
}
