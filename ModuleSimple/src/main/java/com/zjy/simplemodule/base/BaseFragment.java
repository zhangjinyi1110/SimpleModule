package com.zjy.simplemodule.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.simplemodule.utils.GenericityUtils;

public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    private FragmentActivity activity;
    protected VM viewModel;
    private boolean isFirst;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            activity = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirst = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (isBinding()) {
            view = bindView(inflater, container);
        } else {
            view = inflater.inflate(getLayoutId(), container, false);
        }
        viewModel = getViewModel();
        initView(savedInstanceState);
        initEvent();
        if (isFirst && isLazy()) {
            isFirst = false;
            initData();
        }
        return view;
    }

    private VM getViewModel() {
        try {
            return ViewModelProviders.of(this).get(GenericityUtils.<VM>getGenericity(getClass()));
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isFirst && isLazy()) {
            initData();
        }
    }

    protected View bindView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    protected boolean isBinding() {
        return false;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initEvent();

    protected abstract void initData();

    public boolean isLazy() {
        return true;
    }

    public FragmentActivity getSelfActivity() {
        return activity;
    }
}
