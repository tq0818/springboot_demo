package com.example.demo.service;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.MUser;
import com.example.demo.common.BgyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService extends BaseService {
    @Autowired
    private IUserDao iUserDao;


    /**
     * 添加用户
     *
     * @param mUserJson
     * @return
     */
    public String addUser(String mUserJson) {
        BgyResult br = new BgyResult();
        MUser mUser = json.parseObject(mUserJson, MUser.class);
        int count = iUserDao.countUserName(mUser);
        if (count > 0) {
            br.setCode("400");
            br.setMsg("用户名已存在");
            return json.toJSONString(br);
        }
        mUser.setId(get32UUID());
        boolean result = iUserDao.addUser(mUser);
        if (result) {
            br.setCode("200");
            br.setMsg("注册成功");
            br.setData(mUser);
        } else {
            br.setCode("400");
            br.setMsg("注册失败");
        }
        return json.toJSONString(br);
    }

    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    public String getUserByName(String userName) {
        BgyResult br = new BgyResult();
        MUser mUser = iUserDao.getUserByName(userName);
        br.setCode("200");
        br.setMsg("Ok");
        br.setData(mUser);
        return json.toJSONString(br);
    }

    /**
     * 编辑用户
     *
     * @param mUserJson
     * @return
     */
    public String updateUser(String id, String mUserJson) {
        BgyResult br = new BgyResult();
        MUser mUser = json.parseObject(mUserJson, MUser.class);
        MUser myMUser = iUserDao.getUserById(id);
        if (myMUser == null) {
            br.setCode("400");
            br.setMsg("用户不存在");
            return json.toJSONString(br);
        }
        boolean result = iUserDao.updateUser(mUser);
        if (result) {
            br.setCode("200");
            br.setMsg("修改成功");
        } else {
            br.setCode("400");
            br.setMsg("修改失败");
        }
        return json.toJSONString(br);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public String deleteUser(String id) {
        BgyResult br = new BgyResult();
        MUser myMUser = iUserDao.getUserById(id);
        if (myMUser == null) {
            br.setCode("400");
            br.setMsg("用户不存在");
            return json.toJSONString(br);
        }
        boolean result = iUserDao.deleteUser(id);
        if (result) {
            br.setCode("200");
            br.setMsg("删除成功");
        } else {
            br.setCode("400");
            br.setMsg("删除失败");
        }
        return json.toJSONString(br);
    }
}
