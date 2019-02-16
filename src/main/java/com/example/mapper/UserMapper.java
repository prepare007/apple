package com.example.mapper;


import com.example.domain.User;

import java.util.List;

public interface UserMapper {


    int insertSelective(User record);


    List<User> selectAllUser();

    User findUserById(Long id);

    Long updateByPrimaryKeySelective(User user);

    Long deleteUser(Long id);
}