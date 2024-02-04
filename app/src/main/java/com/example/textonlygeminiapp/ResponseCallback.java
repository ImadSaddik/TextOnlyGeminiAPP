package com.example.textonlygeminiapp;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable throwable);
}
