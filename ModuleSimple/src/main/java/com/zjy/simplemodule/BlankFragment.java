package com.zjy.simplemodule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zjy.simplemodule.adapter.BaseAdapter;
import com.zjy.simplemodule.adapter.BindingAdapter;
import com.zjy.simplemodule.base.BindingFragment;
import com.zjy.simplemodule.databinding.FragmentBlankBinding;
import com.zjy.simplemodule.databinding.ItemFBlankBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class BlankFragment extends BindingFragment<MainViewModel, FragmentBlankBinding> {

    private BindingAdapter<String, ItemFBlankBinding> adapter;

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new BindingAdapter<String, ItemFBlankBinding>(getSelfActivity()) {
            @Override
            protected void convert(ItemFBlankBinding binding, String s, int position) {
                binding.itemBlankText.setText(s);
            }

            @Override
            protected int getLayoutId(int type) {
                return R.layout.item_f_blank;
            }
        };
        adapter.setHeaderEnable(true);
//        adapter.setLoadEnable(false);
        adapter.setFooterFullSpan(false);
        binding.blankRecycler.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        binding.blankRecycler.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        adapter.setLoadMoreListener(new BaseAdapter.OnLoadMoreListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onLoadMore() {
                Flowable.timer(3, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                initData();
                                if (adapter.getList().size() > 150) {
                                    adapter.setLoadEnable(false);
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("message" + (System.currentTimeMillis() % (1000 * (i * i + 1))));
        }
        adapter.addList(list);
    }
}
