package com.example.demo.dao;

import com.example.demo.model.MUser;

public interface IUserDao {
    int countUserName(MUser mUser);

    boolean addUser(MUser mUser);

    MUser getUserByName(String userName);

    MUser getUserById(String id);

    boolean updateUser(MUser mUser);

    boolean deleteUser(String id);
}
