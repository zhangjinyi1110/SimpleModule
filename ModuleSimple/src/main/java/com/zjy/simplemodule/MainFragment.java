package com.zjy.simplemodule;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zjy.simplemodule.adapter.BindingAdapter;
import com.zjy.simplemodule.base.BindingFragment;
import com.zjy.simplemodule.databinding.FragmentMainBinding;
import com.zjy.simplemodule.databinding.ItemFMainBinding;
import com.zjy.simplemodule.rxcallback.Avoid;
import com.zjy.simplemodule.rxcallback.RxAvoid;
import com.zjy.simplemodule.rxcallback.RxCallback;
import com.zjy.simplemodule.rxcallback.RxPermission;
import com.zjy.simplemodule.utils.SizeUtils;
import com.zjy.simplemodule.widget.PageLayout;

import java.util.List;

public class MainFragment extends BindingFragment<MainViewModel, FragmentMainBinding> {

    private BindingAdapter<ChapterModel, ItemFMainBinding> adapter;
    private PageLayout pageLayout;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new BindingAdapter<ChapterModel, ItemFMainBinding>(getContext()) {
            @Override
            protected void convert(ItemFMainBinding binding, ChapterModel model, int position) {
                binding.itemMainText.setText(model.getName());
            }

            @Override
            protected int getLayoutId(int type) {
                return R.layout.item_f_main;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pageLayout = new PageLayout.Builder(getSelfActivity())
                .setDefault()
                .create(this);
    }

    @Override
    protected void initEvent() {
        adapter.setItemClickListener(new BindingAdapter.OnItemClickListener<ChapterModel, ItemFMainBinding>() {
            @Override
            public void onItemClick(ItemFMainBinding binding, ChapterModel chapterModel, int position) {
//                ToastUtils.showToastShort(getContext(), chapterModel.getName());
                if (position % 2 == 0) {
                    RxAvoid.getInstance()
//                            .with(getSelfActivity())
                            .result(new Intent(getSelfActivity(), SecondActivity.class))
                            .callback(new RxCallback<Avoid>() {
                                @Override
                                public void onResult(Avoid avoid) {
                                    Log.d(TAG, "onResult: " + avoid.isOk() + "/" + avoid.getResultCode());
                                    if (avoid.getIntent() != null) {
                                        Log.d(TAG, "onResult: " + avoid.getIntent().getStringExtra("key"));
                                    }
                                }
                            });
                } else {
                    RxAvoid.getInstance()
//                            .with(getSelfActivity())
                            .result(ThreeActivity.class)
                            .callback(new RxCallback<Avoid>() {
                                @Override
                                public void onResult(Avoid avoid) {
                                    Log.d(TAG, "onResult: " + avoid.isOk() + "/" + avoid.getResultCode());
                                    if (avoid.getIntent() != null) {
                                        Log.d(TAG, "onResult: " + avoid.getIntent().getStringExtra("key"));
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void initData() {
        viewModel.getChapterList().observe(this, new Observer<List<ChapterModel>>() {
            @Override
            public void onChanged(@Nullable List<ChapterModel> chapterModels) {
                if (chapterModels != null) {
                    adapter.clear();
                    adapter.addList(chapterModels);
                    adapter.setLoadEnable(false);
                    pageLayout.showContent();
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            viewModel.getMainList();
        }
        super.onHiddenChanged(hidden);
    }
}
