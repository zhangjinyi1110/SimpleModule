package com.zjy.simplemodule;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zjy.simplemodule.adapter.BaseAdapter;
import com.zjy.simplemodule.adapter.viewholder.SimpleViewHolder;
import com.zjy.simplemodule.base.BindingFragment;
import com.zjy.simplemodule.databinding.FragmentMainBinding;
import com.zjy.simplemodule.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BindingFragment<MainViewModel, FragmentMainBinding> {

    private BaseAdapter<String> adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new BaseAdapter<String>(getContext()) {
            @Override
            protected int getLayoutId(int type) {
                return R.layout.item_f_main;
            }

            @Override
            protected void convert(SimpleViewHolder holder, String s, int position) {
                ((TextView) holder.findViewById(R.id.item_main_text)).setText(s);
            }

            @Override
            protected SimpleViewHolder getViewHolder(View view) {
                return new SimpleViewHolder(view);
            }
        };
        binding.mainTv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = SizeUtils.dp2px(getSelfActivity(), 1);
            }
        });
        binding.mainTv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.mainTv.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("message" + i);
        }
        adapter.addList(list);
    }
}
