package com.zjy.simplemodule;

import android.app.Application;
import android.support.annotation.NonNull;

import com.zjy.simplemodule.base.BaseViewModel;
import com.zjy.simplemodule.utils.ToastUtils;

public class MainViewModel extends BaseViewModel<MainRepository> {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void show() {
        ToastUtils.showToastShort(getApplication(), "message");
    }

    public void show(String message) {
        ToastUtils.showToastShort(getApplication(), message);
    }

}
