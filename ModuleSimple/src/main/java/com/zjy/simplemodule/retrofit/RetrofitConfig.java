package com.zjy.simplemodule.retrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;

public class RetrofitConfig {

    static TimeUnit timeUnit = TimeUnit.SECONDS;
    static long writeTimeout = 50000;
    static long readTimeout = 50000;
    static long connectTimeout = 50000;
    static List<Interceptor> interceptors;
    static String baseUrl;
    static int successCode = 0;
    private static RetrofitConfig config;

    public static RetrofitConfig newInstance() {
        if (config == null) {
            synchronized (RetrofitConfig.class) {
                if (config == null) {
                    config = new RetrofitConfig();
                }
            }
        }
        return config;
    }

    private RetrofitConfig() {
        interceptors = new ArrayList<>();
    }

    public static RetrofitConfig setTimeUnit(TimeUnit timeUnit) {
        RetrofitConfig.timeUnit = timeUnit;
        return config;
    }

    public static RetrofitConfig setConnectTimeout(long connectTimeout) {
        RetrofitConfig.connectTimeout = connectTimeout;
        return config;
    }

    public static RetrofitConfig setReadTimeout(long readTimeout) {
        RetrofitConfig.readTimeout = readTimeout;
        return config;
    }

    public static RetrofitConfig setWriteTimeout(long writeTimeout) {
        RetrofitConfig.writeTimeout = writeTimeout;
        return config;
    }

    public static RetrofitConfig addInterceptors(List<Interceptor> interceptors) {
        RetrofitConfig.interceptors.addAll(interceptors);
        return config;
    }

    public static RetrofitConfig addInterceptor(Interceptor interceptor) {
        RetrofitConfig.interceptors.add(interceptor);
        return config;
    }

    public static RetrofitConfig setBaseUrl(String baseUrl) {
        RetrofitConfig.baseUrl = baseUrl;
        return config;
    }

    public static RetrofitConfig setSuccessCode(int successCode) {
        RetrofitConfig.successCode = successCode;
        return config;
    }
}
