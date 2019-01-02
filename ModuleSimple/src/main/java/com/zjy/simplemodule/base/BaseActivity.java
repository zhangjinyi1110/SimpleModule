package com.zjy.simplemodule.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.zjy.simplemodule.utils.ActivityManager;
import com.zjy.simplemodule.utils.GenericityUtils;

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

    protected VM viewModel;
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isBinding()) {
            setContentView(getLayoutId());
        } else {
            bindingView();
        }
        ActivityManager.getInstance().addActivity(this);
        viewModel = getViewModel();
        if (getToolBar() != null) {
            setSupportActionBar(getToolBar());
            initToolbar(getSupportActionBar());
        }
        initView(savedInstanceState);
        initEvent();
        initData();
    }

    private VM getViewModel() {
        try {
            return ViewModelProviders.of(this).get(GenericityUtils.<VM>getGenericity(this.getClass()));
        } catch (ClassCastException e) {
            e.printStackTrace();
            Log.e(TAG, "getViewModel: " + e.toString());
            return null;
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initEvent();

    protected abstract void initData();

    protected void bindingView() {
    }

    protected Toolbar getToolBar() {
        return null;
    }

    protected void initToolbar(ActionBar actionBar) {
    }

    protected boolean isBinding() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return onHomeClick();
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean onHomeClick() {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onCleared();
    }
}
