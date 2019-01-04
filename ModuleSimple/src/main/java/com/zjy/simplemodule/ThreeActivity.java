package com.zjy.simplemodule;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zjy.simplemodule.base.BaseViewModel;
import com.zjy.simplemodule.base.BindingActivity;
import com.zjy.simplemodule.databinding.ActivityThreeBinding;
import com.zjy.simplemodule.rxcallback.RxAvoid;
import com.zjy.simplemodule.rxcallback.RxCallback;

public class ThreeActivity extends BindingActivity<BaseViewModel, ActivityThreeBinding> {

    private boolean ok = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_three;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxAvoid.getInstance()
                        .with(getSelf())
                        .simple(!ok ? SecondActivity.class : MainActivity.class)
                        .callback(new RxCallback<Boolean>() {
                            @Override
                            public void onResult(Boolean aBoolean) {
                                Log.d(TAG, "onResult: " + aBoolean);
                            }
                        });
            }
        });
    }

    @Override
    protected void initData() {

    }
}
