package com.example.demo.dao.impl;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.MUser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("userDaoImpl")
public class UserDaoImpl implements IUserDao {
    @Resource(name = "dbSqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int countUserName(MUser mUser) {
        return sqlSessionTemplate.selectOne("UserMapper.countUserName", mUser);
    }

    @Override
    public boolean addUser(MUser mUser) {
        int num = sqlSessionTemplate.insert("UserMapper.addUser", mUser);
        boolean result = false;
        if (num > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public MUser getUserByName(String userName) {
        MUser mUser = sqlSessionTemplate.selectOne("UserMapper.getUserByName", userName);
        return mUser;
    }

    @Override
    public MUser getUserById(String id) {
        MUser mUser = sqlSessionTemplate.selectOne("UserMapper.getUserById", id);
        return mUser;
    }

    @Override
    public boolean updateUser(MUser mUser) {
        int num = sqlSessionTemplate.update("UserMapper.updateUser", mUser);
        boolean result = false;
        if (num > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteUser(String id){
        int num = sqlSessionTemplate.update("UserMapper.deleteUser", id);
        boolean result = false;
        if (num > 0) {
            result = true;
        }
        return result;
    }
}
