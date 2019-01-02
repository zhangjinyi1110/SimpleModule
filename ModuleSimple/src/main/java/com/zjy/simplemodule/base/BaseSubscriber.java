package com.zjy.simplemodule.base;

import android.content.Context;

import com.zjy.simplemodule.exception.HttpResultException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    private Context context;

    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
        if (context != null) {
            //show load
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if (context != null) {
            //dismiss load
        }
        onFailure(new HttpResultException(-1, "", t));
    }

    @Override
    public void onComplete() {
        if (context != null) {
            //dismiss load
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(HttpResultException exception);
}
