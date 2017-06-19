package com.csms.test;

import com.csms.dao.UserDao;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class UserDaoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println(userDao.updateAdminPwd("admin","admin"));

    }
}
