package com.example.controller;

import com.example.base.BaseOpenApiResult;
import com.example.base.PageInfo;
import com.example.domain.User;
import com.example.services.UserService;
import com.example.util.APIResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@CrossOrigin
@RequestMapping (value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping (value = "/add", produces = {"application/json;charset=UTF-8"}, method =
            RequestMethod.POST)
    public BaseOpenApiResult addUser(User user) {

        userService.addUser(user);
        return APIResponseUtil.makeOKRsp();
    }

    @ResponseBody
    @RequestMapping (value = "/findUser/{id}", method = RequestMethod.GET)
    public BaseOpenApiResult findOneUser(@PathVariable ("id") Long id) {

        return APIResponseUtil.makeOKRsp(userService.findUserById(id));
    }

    @ResponseBody
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public long modifyUser(User user) {

        return userService.updateUser(user);
    }

    @RequestMapping (value = "/user/{id}", method = RequestMethod.DELETE)
    public void modifyUser(@PathVariable ("id") Long id) {

        userService.deleteUser(id);
    }

    @ResponseBody
    @RequestMapping (value = "/all",
                     produces = {"application/json;charset=UTF-8"},
                     method = RequestMethod.POST)
    @CrossOrigin
    public BaseOpenApiResult findAllUser(PageInfo pageInfo) {

        return APIResponseUtil.makeOKRsp(userService.findAllUser(pageInfo.getPageNum(),
                                                                 pageInfo.getPageSize()));
    }
}

