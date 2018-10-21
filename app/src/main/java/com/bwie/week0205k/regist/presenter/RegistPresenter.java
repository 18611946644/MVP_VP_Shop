package com.bwie.week0205k.regist.presenter;

import android.text.TextUtils;

import com.bwie.week0205k.regist.model.RegistModel;
import com.bwie.week0205k.regist.view.IView;

/**
 * Created by eric on 2018/10/15.
 */

public class RegistPresenter {
    private IView iv;
    private RegistModel model;

    public void attach(IView iv) {
        this.iv = iv;
        model = new RegistModel(iv.getContext());
    }

    public void check() {
        String username = iv.getUsername();
        String password = iv.getPassword();
        String confirmPassword = iv.getConfirmPassword();

        if (TextUtils.isEmpty(username)) {
            iv.checkData(false, "用户名不能为空");
        } else if (TextUtils.isEmpty(password)) {
            iv.checkData(false, "密码不能为空");
        } else if (TextUtils.isEmpty(confirmPassword)) {
            iv.checkData(false, "请再次输入密码");
        } else if (!password.equals(confirmPassword)) {
            iv.checkData(false, "两次输入的密码不一致");
        } else {
            iv.checkData(true, "");
        }

    }

    public void regist() {
        String username = iv.getUsername();
        String password = iv.getPassword();

        // 注册成功，也就是把数据保存到SP中
        if (model.regist(username, password)) {
            // 退出当前页面
            iv.exit();
        }

    }


    public void detach() {
        if (iv != null) {
            iv = null;
        }
    }
}
