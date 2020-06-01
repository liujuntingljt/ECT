package com.hbsd.rjxy.ect.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hbsd.rjxy.ect.R;
import com.hbsd.rjxy.ect.presenter.EditPwdPresenterCompl;
import com.hbsd.rjxy.ect.presenter.EditPwdView;

public class EditPwdWithoutOldActivity extends AppCompatActivity implements EditPwdView {

    private TextView tx_pwd;
    private  TextView tx_confirm;
    private Button btn_commit;
    private ImageView iv_login_show_pwd;
    private ImageView iv_login_show_confirm_pwd;
    private Intent intent;
    private  String uid;
    private EditPwdPresenterCompl editPwdPresenterCompl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pwd_without_old);
        /*接收我的界面传来的数据*/
        intent=getIntent();
        uid=intent.getStringExtra("uid");
        Gson gson=new Gson();
       // user=gson.fromJson(str, User.class);

        tx_pwd=findViewById(R.id.self_new_pwd);
        tx_confirm=findViewById(R.id.self_confirm_pwd);
        btn_commit=findViewById(R.id.self_btn_pwd_commit);
        iv_login_show_pwd=findViewById(R.id.iv_login_show_pwd);
        iv_login_show_confirm_pwd=findViewById(R.id.iv_login_show_confirm_pwd);
        /*TODO
         * 设置全局的监听器
         * */
        MyAllListener myAllListener=new MyAllListener();


        btn_commit.setOnClickListener(myAllListener);
        iv_login_show_confirm_pwd.setOnClickListener(myAllListener);
        iv_login_show_pwd.setOnClickListener(myAllListener);
    }

    @Override
    public void okFinish() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(EditPwdWithoutOldActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

            }
        });

        finish();
    }

    @Override
    public void failFinish() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(EditPwdWithoutOldActivity.this, "网络问题，修改失败", Toast.LENGTH_SHORT).show();

            }
        });

        finish();
    }

    private class  MyAllListener implements View.OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {


            switch (v.getId()){
                case R.id.iv_login_show_confirm_pwd: {
                    if(tx_confirm.getInputType()==128){
                        tx_confirm.setInputType(129);
                        iv_login_show_confirm_pwd.setImageResource(R.drawable.eye_close);
                    }
                    else if(tx_confirm.getInputType()==129){
                        tx_confirm.setInputType(128);
                        iv_login_show_confirm_pwd.setImageResource(R.drawable.eye_open);
                    }
                    break;

                }
                case R.id.iv_login_show_pwd:{
                    if(tx_pwd.getInputType()==128){
                        tx_pwd.setInputType(129);
                        iv_login_show_pwd.setImageResource(R.drawable.eye_close);
                    }
                    else if(tx_pwd.getInputType()==129){
                        tx_pwd.setInputType(128);
                        iv_login_show_pwd.setImageResource(R.drawable.eye_open);
                    }
                    break;
                }
                case R.id.self_btn_pwd_commit:{
                    editPwdPresenterCompl=new EditPwdPresenterCompl(EditPwdWithoutOldActivity.this);
                    String newPwd=tx_pwd.getText().toString();
                    String confirm=tx_confirm.getText().toString();
                    if(newPwd.equals(confirm)){
                        //Integer id=user.getUserId();
                        editPwdPresenterCompl.editPwdWithoutOld(uid,newPwd);
                        finish();

                    }
                    else{
                        Toast.makeText(EditPwdWithoutOldActivity.this,"前后密码不一致",Toast.LENGTH_SHORT);
                    }
                    break;

                }
            }

        }
    }
}
