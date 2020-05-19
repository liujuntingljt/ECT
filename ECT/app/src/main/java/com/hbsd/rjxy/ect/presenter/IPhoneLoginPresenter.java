package com.hbsd.rjxy.ect.presenter;

import org.json.JSONObject;

public interface IPhoneLoginPresenter {
    void doLogin(String tel);
    void doQQLogin(JSONObject jsonObject);
}
