package com.bwie.week0205k.home.model;

import com.bwie.week0205k.inter.ICallBack;
import com.bwie.week0205k.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by eric on 2018/10/16.
 */

public class HomeModel {

    public void getData(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().get(url, callBack, type);
    }

}
