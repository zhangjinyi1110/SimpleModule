package com.zjy.simplemodule.base.fragment;

import android.arch.lifecycle.ViewModelProviders;

import com.zjy.simplemodule.base.BaseViewModel;
import com.zjy.simplemodule.utils.GenericityUtils;

public abstract class AbsFragment<VM extends BaseViewModel> extends BaseFragment {

    protected VM viewModel;

    @Override
    protected boolean isViewModel() {
        return true;
    }

    @Override
    protected void initViewModel() {
        viewModel = getViewModel();
    }

    private VM getViewModel() {
        try {
            return ViewModelProviders.of(this).get(GenericityUtils.<VM>getGenericity(getClass()));
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}