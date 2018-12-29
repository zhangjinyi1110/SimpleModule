package com.zjy.simplemodule.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

public abstract class SimpleBindingActivity<VM extends BaseViewModel, B extends ViewDataBinding> extends SimpleActivity<VM> {

    protected B binding;

    @Override
    protected boolean isBinding() {
        return true;
    }

    @Override
    protected void bindingView() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }
}
