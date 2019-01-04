package com.zjy.simplemodule;

import com.zjy.simplemodule.retrofit.HttpResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("wxarticle/chapters/json")
    Flowable<HttpResult<List<ChapterModel>>> get();

}
