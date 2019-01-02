package com.zjy.simplemodule;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.zjy.simplemodule.base.BaseSubscriber;
import com.zjy.simplemodule.base.BaseViewModel;
import com.zjy.simplemodule.exception.HttpResultException;
import com.zjy.simplemodule.utils.ToastUtils;

import java.util.List;

public class MainViewModel extends BaseViewModel<MainRepository> {

    private MutableLiveData<List<ChapterModel>> mainList;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void show(String message) {
        ToastUtils.showToastShort(getApplication(), message);
    }

    public MutableLiveData<List<ChapterModel>> getChapterList() {
        if (mainList == null) {
            mainList = new MutableLiveData<>();
        }
        getMainList();
        return mainList;
    }

    public void getMainList() {
        repository.getMainList(new BaseSubscriber<List<ChapterModel>>() {
            @Override
            public void onSuccess(List<ChapterModel> chapterModels) {
                mainList.setValue(chapterModels);
            }

            @Override
            public void onFailure(HttpResultException exception) {
                ToastUtils.showToastShort(repository.getContext(), "发生错误");
            }
        });
    }

}
