package com.hbsd.rjxy.ect.presenter;

import android.util.Log;


import com.hbsd.rjxy.ect.util.Constant;
import com.hbsd.rjxy.ect.util.EncodeUtil;
import com.hbsd.rjxy.ect.util.OkHttpUtils;

import org.greenrobot.greendao.annotation.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EditPwdPresenterCompl {
    private EditPwdView editPwdView;


    public EditPwdPresenterCompl(EditPwdView editPwdView){
        this.editPwdView=editPwdView;
    }

    public void editPwdWithoutOld(String id,String newPwd) {
        /*TODO
            直接将id,新的密码发送到服务器
        */
        OkHttpUtils utils=new OkHttpUtils();
        String url= Constant.GET_USER_URL+"editPwd";
        JSONObject obj=new JSONObject();

        try {
            obj.put("uid",id);
            String edPwd= EncodeUtil.encodeToString(newPwd);
            obj.put("newPwd",edPwd);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str=obj.toString();

        utils.postJson(url, str, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                editPwdView.failFinish();
                Log.e("无旧密码修改密码","成功");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                editPwdView.okFinish();
                Log.e("无旧密码修改密码","成功");
            }
        });

    }

    public void editPwdWithOld(Integer id,String oldPwd, String newPwd) {
        /*TODO
            将id，旧密码，新密码发送到服务器
            先进行id查询，再进行旧密码匹配，最后修改为新密码
        */
            //Log.e("即将发生到客户端的是",id+oldPwd+newPwd);
            JSONObject obj=new JSONObject();
            try {
                obj.put("uid",id);
                obj.put("oldPwd", EncodeUtil.encodeToString(oldPwd));
                obj.put("newPwd",EncodeUtil.encodeToString(newPwd));
                String jsonStr=obj.toString();
                Log.e("jsonStr",jsonStr);
                OkHttpUtils utils=new OkHttpUtils();
                utils.postJson(Constant.GET_USER_URL + "editWithOldPwd", jsonStr, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                        editPwdView.failFinish();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.body().string().contains("false")){
                            editPwdView.failFinish();

                        }
                        else{
                            editPwdView.okFinish();
                            Log.e("有旧密码更换密码","成功");

                        }

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();


            }

    }
}
