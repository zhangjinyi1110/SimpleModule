package com.zjy.simplemodule.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.zjy.simplemodule.utils.GenericityUtils;

import io.reactivex.disposables.Disposable;

public class BaseViewModel<R extends BaseRepository> extends AndroidViewModel {

    protected R repository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        repository = getRepository();
    }

    @SuppressWarnings("unchecked")
    private R getRepository() {
        Class<R> rClass = GenericityUtils.getGenericity(getClass());
        try {
            if (rClass != null) {
                R r = rClass.newInstance();
                r.with(getApplication());
                return r;
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
//        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
//        try {
//            Class<R> rClass;
//            if (type != null) {
//                rClass = (Class<R>) type.getActualTypeArguments()[0];
//                R r = rClass.newInstance();
//                r.with(getApplication());
//                return r;
//            }
//        } catch (IllegalAccessException | InstantiationException | NullPointerException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    protected void addDisposable(Disposable disposable) {
        repository.addDisposable(disposable);
    }

    protected void clearDisposable() {
        repository.clearDisposable();
    }

    protected boolean isDisposed() {
        return repository.isDisposed();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (repository.isDisposed()) {
            repository.clearDisposable();
        }
    }
}
