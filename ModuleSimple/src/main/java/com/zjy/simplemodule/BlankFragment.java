package com.zjy.simplemodule;

import android.os.Bundle;
import android.view.View;

import com.zjy.simplemodule.base.BaseFragment;
import com.zjy.simplemodule.base.BindingFragment;
import com.zjy.simplemodule.databinding.FragmentBlankBinding;

public class BlankFragment extends BindingFragment<MainViewModel, FragmentBlankBinding> {

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        binding.blankText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.show("BlankFragment");
            }
        });
    }

    @Override
    protected void initData() {

    }
}
