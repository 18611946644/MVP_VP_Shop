package com.bwie.week0205k.login.view;

import android.content.Context;

/**
 * Created by eric on 2018/10/15.
 */

public interface IView<T> {
    void success(T t);

    void failed(Exception e);

    void setUsername(String username);

    void setPassword(String password);

    String getUsername();

    String getPassword();

    void checkData(boolean isChecked, String msg);

    Context getContext();

    void setCheckBox(boolean isChecked);

    boolean isRemember();
}
