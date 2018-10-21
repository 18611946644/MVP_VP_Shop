package com.bwie.week0205k.login.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.bwie.week0205k.bean.LoginResult;
import com.bwie.week0205k.home.view.HomeActivity;
import com.bwie.week0205k.login.model.LoginModel;
import com.bwie.week0205k.login.view.IView;
import com.bwie.week0205k.regist.view.RegistActivity;

/**
 * Created by eric on 2018/10/15.
 */

public class LoginPresenter {
    private IView iv;
    private LoginModel model;

    public void attach(IView iv) {
        this.iv = iv;
        model = new LoginModel(iv.getContext());
    }

    public void check() {
        String username = iv.getUsername();
        String password = iv.getPassword();

        if (TextUtils.isEmpty(username)) {
            iv.checkData(false, "用户名不能为空");
        } else if (TextUtils.isEmpty(password)) {
            iv.checkData(false, "密码不能为空");
        } else {
            iv.checkData(true, "");
        }
    }


    public void login() {
        LoginResult loginResult = model.login(iv.getUsername(), iv.getPassword());
        if (loginResult.isSuccess()) {
            // 如果是记住密码，保存SP
            if (iv.isRemember()) {
                model.saveUser(iv.getUsername(), iv.getPassword());
            } else {
                model.forget();
            }

            Intent intent = new Intent(iv.getContext(), HomeActivity.class);
            iv.getContext().startActivity(intent);
            iv.success(null);
        } else {
            iv.failed(new Exception(loginResult.getMsg()));
        }
    }

    public void gotoRegist() {
        Intent intent = new Intent(iv.getContext(), RegistActivity.class);
        iv.getContext().startActivity(intent);
    }

    public void isCheckBoxCheck() {
        // 如果从sp中读出是否记住密码
        boolean isChecked = model.isCheckboxChecked();
        // 如果是记住密码
        if (isChecked) {
            iv.setUsername(model.getUsername());
            iv.setPassword(model.getPassword());
        }
        // 复选框的状态
        iv.setCheckBox(isChecked);
    }


    public void detach() {
        if (iv != null) {
            iv = null;
        }
    }

}


