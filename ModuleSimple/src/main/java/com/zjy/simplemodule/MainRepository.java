package com.zjy.simplemodule;

import com.zjy.simplemodule.base.BaseRepository;
import com.zjy.simplemodule.retrofit.BaseSubscriber;
import com.zjy.simplemodule.retrofit.HttpResultTransformer;
import com.zjy.simplemodule.retrofit.AutoDisposeUtils;
import com.zjy.simplemodule.retrofit.RetrofitUtils;

import java.util.List;

public class MainRepository extends BaseRepository {

    @Override
    protected void init() {

    }

    public void getMainList(BaseSubscriber<List<ChapterModel>> subscriber) {
        RetrofitUtils.getInstance()
                .createService(ApiService.class, "http://www.wanandroid.com/")
                .get()
                .compose(new HttpResultTransformer<List<ChapterModel>>())
                .as(AutoDisposeUtils.<List<ChapterModel>>bind(getCurrActivity()))
                .subscribe(subscriber);
    }

}
