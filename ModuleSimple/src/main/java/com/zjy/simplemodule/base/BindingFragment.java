package com.zjy.simplemodule.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BindingFragment<VM extends BaseViewModel, B extends ViewDataBinding> extends BaseFragment<VM> {

    protected B binding;

    @Override
    protected boolean isBinding() {
        return true;
    }

    @Override
    protected View bindView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

}
