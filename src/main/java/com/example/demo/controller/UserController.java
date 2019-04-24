package com.example.demo.controller;

import com.example.demo.controller.base.BaseController;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param mUserJson
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(@RequestBody String mUserJson) throws Exception {
        String resultInfo = "";
        try {
            resultInfo = userService.addUser(mUserJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

    /**
     * 通过用户名称获取用户
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public String getUserByName(@PathVariable String userName) throws Exception {
        String resultInfo = "";
        try {
            resultInfo = userService.getUserByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

    /**
     * 修改用户
     *
     * @param mUserJson
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("id") String id, @RequestBody String mUserJson) throws Exception {
        String resultInfo = "";
        try {
            resultInfo = userService.updateUser(id, mUserJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") String id) throws Exception {
        String resultInfo = "";
        try {
            resultInfo = userService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }
}
