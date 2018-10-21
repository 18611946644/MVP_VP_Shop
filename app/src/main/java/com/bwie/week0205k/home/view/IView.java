package com.bwie.week0205k.home.view;

import android.content.Context;

import com.bwie.week0205k.bean.Banner;
import com.bwie.week0205k.bean.Category;
import com.bwie.week0205k.bean.Product;

import java.util.List;

/**
 * Created by eric on 2018/10/15.
 */

public interface IView {
    void getBanner(List<Banner.DataBean> list);

    void getCategory(List<Category.DataBean> list);

    void getProduct(List<Product.DataBean> list);

    Context getContext();

    void failed(Exception e);
}
