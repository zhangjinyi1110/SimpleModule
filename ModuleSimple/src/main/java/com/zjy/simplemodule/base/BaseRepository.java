package com.zjy.simplemodule.base;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseRepository {

    private Context context;
    private CompositeDisposable disposables;

    public void with(Context context) {
        this.context = context;
        disposables = new CompositeDisposable();
        init();
    }

    protected abstract void init();

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    protected void clearDisposable() {
        disposables.clear();
    }

    protected boolean isDisposed() {
        return disposables.isDisposed();
    }

    public Context getContext() {
        return context;
    }

}
