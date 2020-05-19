package com.hbsd.rjxy.ect.view;

import org.json.JSONObject;

public interface IPhoneLoginView {
    public void onLoginResult(String result, JSONObject jsonObject);
    public void onQQLoginResult(String result, JSONObject jsonObject);
}
