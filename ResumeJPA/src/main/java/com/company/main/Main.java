package com.company.main;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

public class Main {

    public static void main(String[] args) {

        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getById(1);
        System.out.println(u.getNationalityId().getName());
    }

}
