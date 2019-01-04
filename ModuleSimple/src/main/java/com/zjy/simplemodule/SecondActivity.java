package com.zjy.simplemodule;

import android.content.Intent;
import android.os.Bundle;

import com.zjy.simplemodule.base.BaseViewModel;
import com.zjy.simplemodule.base.BindingActivity;
import com.zjy.simplemodule.databinding.ActivitySecondBinding;

public class SecondActivity extends BindingActivity<BaseViewModel, ActivitySecondBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setResult(RESULT_OK, new Intent().putExtra("key", "value"));
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

}
