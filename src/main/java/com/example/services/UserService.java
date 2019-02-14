package com.example.services;


import com.example.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    User findUserById(Long id);

    Long updateUser(User user);

    Long deleteUser(Long id);
}
