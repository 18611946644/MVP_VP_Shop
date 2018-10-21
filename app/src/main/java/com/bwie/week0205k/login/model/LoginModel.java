package com.bwie.week0205k.login.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.bwie.week0205k.bean.LoginResult;

/**
 * Created by eric on 2018/10/15.
 */

public class LoginModel {
    private Context context;
    private SharedPreferences registSp;
    private SharedPreferences loginSp;


    public LoginModel(Context context) {
        this.context = context.getApplicationContext();
        registSp = this.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        loginSp = this.context.getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    public LoginResult login(String username, String password) {
        // SharedPreference中保存的用户名和密码
        String dbUserName = registSp.getString("username", "");
        String dbPassword = registSp.getString("password", "");

        if (!username.equals(dbUserName)) {
            return new LoginResult(false, "没有此用户");
        } else if (!password.equals(dbPassword)) {
            return new LoginResult(false, "密码错误");
        } else {
            return new LoginResult(true, "");
        }
    }

    public boolean isCheckboxChecked() {
        boolean checked = loginSp.getBoolean("checked", false);
        return checked;
    }

    public String getUsername() {
        return loginSp.getString("username", "");
    }

    public String getPassword() {
        return loginSp.getString("password", "");
    }

    // 记住密码的时候保存
    public void saveUser(String username, String password) {
        loginSp.edit().putString("username", username)
                .putString("password", password)
                .putBoolean("checked", true)
                .commit();
    }

    // 没有记住密码的时候清除保存
    public void forget() {
        loginSp.edit().clear().commit();
    }
}
