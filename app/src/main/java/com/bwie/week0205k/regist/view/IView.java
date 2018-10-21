package com.bwie.week0205k.regist.view;

import android.app.Activity;
import android.content.Context;

/**
 * Created by eric on 2018/10/15.
 */

public interface IView {
    String getUsername();

    String getPassword();

    String getConfirmPassword();

    void checkData(boolean isCkecked, String msg);

    Context getContext();

    void exit();
}
