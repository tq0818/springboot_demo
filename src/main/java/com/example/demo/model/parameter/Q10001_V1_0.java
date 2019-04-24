package com.example.demo.model.parameter;

import com.example.demo.utils.DESUtil2;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Q10001_V1_0 {

    @NotNull(message = "帐号不能为空")
    @Length(max = 60, message = "帐号长度必须小于60")
    private String account;

    @NotNull(message = "密码不能为空")
    @Length(max = 100, message = "密码长度必须小于100")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        try {
            return DESUtil2.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
