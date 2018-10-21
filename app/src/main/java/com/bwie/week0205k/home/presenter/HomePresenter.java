package com.bwie.week0205k.home.presenter;

import com.bwie.week0205k.bean.Banner;
import com.bwie.week0205k.bean.Category;
import com.bwie.week0205k.bean.Product;
import com.bwie.week0205k.home.model.HomeModel;
import com.bwie.week0205k.home.view.IView;
import com.bwie.week0205k.inter.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by eric on 2018/10/15.
 */

public class HomePresenter {
    private IView iv;
    private HomeModel model;

    public void attach(IView iv) {
        this.iv = iv;
        model = new HomeModel();
    }

    public void getBanner() {
        Type type = new TypeToken<Banner>() {
        }.getType();

        model.getData("https://www.zhaoapi.cn/ad/getAd", new ICallBack() {
            @Override
            public void success(Object obj) {
                Banner banner = (Banner) obj;
                if (banner != null) {
                    iv.getBanner(banner.getData());
                }
            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        }, type);

    }

    public void getCategory() {

        Type type = new TypeToken<Category>() {
        }.getType();

        model.getData("https://www.zhaoapi.cn/product/getCatagory", new ICallBack() {
            @Override
            public void success(Object obj) {
                Category category = (Category) obj;
                if (category != null) {
                    iv.getCategory(category.getData());
                }
            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        }, type);

    }

    public void getProduct() {

        Type type = new TypeToken<Product>() {
        }.getType();

        model.getData("https://www.zhaoapi.cn/product/getCarts?uid=71", new ICallBack() {
            @Override
            public void success(Object obj) {
                Product product = (Product) obj;
                if (product != null) {
                    iv.getProduct(product.getData());
                }
            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        }, type);

    }

    public void detach() {
        if (iv != null) {
            iv = null;
        }
    }

}
