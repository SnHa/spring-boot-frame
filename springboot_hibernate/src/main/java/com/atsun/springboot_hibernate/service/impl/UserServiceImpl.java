package com.atsun.springboot_hibernate.service.impl;

import com.atsun.springboot_hibernate.dao.UserRepository;
import com.atsun.springboot_hibernate.entity.User;
import com.atsun.springboot_hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * @author SH
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public User select(Integer id) {
        User user = userRepository.getById(id);
        return user;
    }
}
