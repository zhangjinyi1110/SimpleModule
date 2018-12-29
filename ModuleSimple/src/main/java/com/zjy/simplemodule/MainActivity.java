package com.zjy.simplemodule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zjy.simplemodule.base.SimpleActivity;
import com.zjy.simplemodule.base.SimpleBindingActivity;
import com.zjy.simplemodule.databinding.ActivityMainBinding;

public class MainActivity extends SimpleBindingActivity<MainViewModel, ActivityMainBinding> {

    private BlankFragment blankFragment;
    private MainFragment mainFragment;

    @Override
    protected int getContentId() {
        return R.id.main_text;
    }

    @Override
    protected Fragment getFragment() {
        if (blankFragment == null)
            blankFragment = BlankFragment.newInstance();
        return blankFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mainFragment = MainFragment.newInstance();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        binding.main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(blankFragment);
            }
        });
        binding.main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(mainFragment);
            }
        });
    }
}
