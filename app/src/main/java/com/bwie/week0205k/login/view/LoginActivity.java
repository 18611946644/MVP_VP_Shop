package com.bwie.week0205k.login.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.week0205k.R;
import com.bwie.week0205k.login.presenter.LoginPresenter;
import com.bwie.week0205k.login.view.IView;

public class LoginActivity extends AppCompatActivity implements IView, View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemember;
    private Button btnLogin;
    private Button btnRegist;

    private LoginPresenter presenter;

    private boolean isRemember = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        setListener();
    }

    private void initData() {
        presenter = new LoginPresenter();
        presenter.attach(this);
        // 是否记住了密码
        presenter.isCheckBoxCheck();
    }

    private void setListener() {
        btnLogin.setOnClickListener(this);
        btnRegist.setOnClickListener(this);
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "onCheckedChanged: " + isChecked);
                isRemember = isChecked;
            }
        });
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegist = findViewById(R.id.btn_regist);
    }

    @Override
    public void success(Object o) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUsername(String username) {
        etUsername.setText(username);
    }

    @Override
    public void setPassword(String password) {
        etPassword.setText(password);
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void checkData(boolean isChecked, String msg) {
        // 没有通过检验就弹出提示
        if (!isChecked) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            presenter.login();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setCheckBox(boolean isChecked) {
        cbRemember.setChecked(isChecked);
        isRemember = isChecked;
    }

    @Override
    public boolean isRemember() {
        return isRemember;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                presenter.check();
                break;
            case R.id.btn_regist:
                presenter.gotoRegist();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
