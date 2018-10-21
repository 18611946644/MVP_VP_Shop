package com.bwie.week0205k.home.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.week0205k.R;
import com.bwie.week0205k.adapter.BannerAdapter;
import com.bwie.week0205k.adapter.CategotyAdapter;
import com.bwie.week0205k.adapter.ProductAdapter;
import com.bwie.week0205k.bean.Banner;
import com.bwie.week0205k.bean.Category;
import com.bwie.week0205k.bean.Product;
import com.bwie.week0205k.home.presenter.HomePresenter;
import com.bwie.week0205k.widget.MyGridView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements IView, View.OnClickListener {
    public static final int REUQEST_SCANNER = 123;

    private ImageView imgScanner;
    private ViewPager vpBanner;
    private MyGridView gvCategory;
    private MyGridView gvProduct;

    private BannerAdapter bannerAdapter;
    private CategotyAdapter categoryAdapter;
    private ProductAdapter productAdapter;

    private List<Banner.DataBean> bannerList;
    private List<Category.DataBean> categoryList;
    private List<Product.DataBean> productList;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initData();
        setListener();
    }

    private void setListener() {
        imgScanner.setOnClickListener(this);
    }

    private void initData() {
        bannerList = new ArrayList<>();
        categoryList = new ArrayList<>();
        productList = new ArrayList<>();

        bannerAdapter = new BannerAdapter(this, bannerList);
        categoryAdapter = new CategotyAdapter(this, categoryList);
        productAdapter = new ProductAdapter(this, productList);

        vpBanner.setAdapter(bannerAdapter);
        gvCategory.setAdapter(categoryAdapter);
        gvProduct.setAdapter(productAdapter);

        presenter = new HomePresenter();
        presenter.attach(this);
        presenter.getBanner();
        presenter.getCategory();
        presenter.getProduct();
    }

    private void initView() {
        imgScanner = findViewById(R.id.btn_scan_qr_code);
        vpBanner = findViewById(R.id.vp_banner);
        gvCategory = findViewById(R.id.gv_category);
        gvProduct = findViewById(R.id.gv_product);
    }

    @Override
    public void getBanner(List<Banner.DataBean> list) {
        if (list != null) {
            bannerList.clear();
            bannerList.addAll(list);
            bannerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getCategory(List<Category.DataBean> list) {
        if (list != null) {
            categoryList.clear();
            categoryList.addAll(list);
            categoryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getProduct(List<Product.DataBean> list) {
        if (list != null) {
            productList.clear();
            productList.addAll(list);
            productAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan_qr_code:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, REUQEST_SCANNER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
