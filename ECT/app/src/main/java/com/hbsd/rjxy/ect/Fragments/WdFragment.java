package com.hbsd.rjxy.ect.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hbsd.rjxy.ect.R;
import com.hbsd.rjxy.ect.activity.EditPwdWithoutOldActivity;
import com.hbsd.rjxy.ect.activity.PhoneLoginActivity;
import com.hbsd.rjxy.ect.base.BaseFragment;
import com.hbsd.rjxy.ect.util.Constant;
//import com.hbsd.rjxy.ect.util.UploadUtils;
//import com.luck.picture.lib.PictureSelector;
//import com.luck.picture.lib.config.PictureConfig;
//import com.luck.picture.lib.config.PictureMimeType;
//import com.luck.picture.lib.entity.LocalMedia;
//
//import java.util.List;
//
//import pub.devrel.easypermissions.EasyPermissions;


import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link WdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WdFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public View view;

    public ImageView wg_imghead;
    public TextView wg_textname;
    public TextView wg_textReset;
    public TextView wg_textRelemail;

    private TextView wg_textFuxi;

    private String userName;

    public Gson gson;

    String uid;
    String userImg;
//    private String qiNiuImgPath;
//    private UploadUtils uploadUtils;
//
//    private boolean isEditedHead = false;
//    private boolean isPrepared;

    public WdFragment() {
        // Required empty public constructor
    }


    public static WdFragment newInstance(String param1, String param2) {
        WdFragment fragment = new WdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_wd,null);
        initView();
        setUser();

        initEvent();
        return view;

    }





    @Override
    public void initView() {
        wg_imghead =(ImageView)  view.findViewById(R.id.imghead);
        wg_textname =(TextView)  view.findViewById(R.id.textname);
        wg_textReset=(TextView) view.findViewById(R.id.text20);
        wg_textRelemail=view.findViewById(R.id.text16);



        gson=new Gson();

    }

    public void setUser(){
        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constant.LOGIN_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if(sharedPreferences.getString("uid",null)!=null){

            uid = sharedPreferences.getString("uid",null);
            userName=sharedPreferences.getString("username","狐狸不熬夜");
             userImg=sharedPreferences.getString("userHeadPath","null");
            Log.e("展示的headde url",userImg);
            if(userImg!=null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wg_textname.setText(userName );
                      // Glide.with(getActivity()).load(userImg).into(wg_imghead);
                       RequestOptions options = new RequestOptions().circleCrop();
                       Glide.with(getActivity()).load(userImg).apply(options).into(wg_imghead);


                    }
                });
            }
            else{
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wg_textname.setText(userName );
                        // Glide.with(getActivity()).load(userImg).into(wg_imghead);
                        RequestOptions options = new RequestOptions().circleCrop();
                        Glide.with(getActivity()).load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1404513126,3004416245&fm=26&gp=0.jpg").apply(options).into(wg_imghead);


                    }
                });


            }

        }
        else{
            Intent intent=new Intent();
            intent.setClass( this.getActivity(),PhoneLoginActivity.class);
            startActivity(intent);
        }
    }

    public void refresh() {
        setUser();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    public void initEvent(){
        ButtonClickListener buttonClickListener=new ButtonClickListener();
        wg_textRelemail.setOnClickListener(buttonClickListener);
        wg_textReset.setOnClickListener(buttonClickListener);
        wg_textname.setOnClickListener(buttonClickListener);
        wg_imghead.setOnClickListener(buttonClickListener);
    }
    public class ButtonClickListener implements View.OnClickListener{

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            final SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.LOGIN_SP_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.textname:{
                    if(wg_textname.getText().equals("请登录")){
                        /*如果用户处于非登录*/
                        Intent intent = new Intent(getActivity(), PhoneLoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"已登录",Toast.LENGTH_SHORT);


                            }
                        });

                    }
                    break;
                }
                /*修改密码*/
                case R.id.text16:{
                    if (sharedPreferences.getString("uid",null)==null){
                        /*如果用户处于非登录*/
                        Intent intent = new Intent(getActivity(), PhoneLoginActivity.class);
                        startActivity(intent);
                    }
                    else if(sharedPreferences.getString("hasPassword", "false").equals("false")){
                        editor.putString("hasPassword","true");
                        editor.commit();
                        Intent intent=new Intent(getActivity(), EditPwdWithoutOldActivity.class);
                        intent.putExtra("uid",sharedPreferences.getString("uid","null"));
                        startActivity(intent);
                    }
                    else{
                        editor.putString("hasPassword","true");
                        editor.commit();
                        Intent intent=new Intent(getActivity(), EditPwdWithoutOldActivity.class);
                        intent.putExtra("uid",sharedPreferences.getString("uid","null"));
                        startActivity(intent);
                    }
                    break;
                }
                /*清除缓存*/
                case R.id.text20:{

                    editor.clear();
                    editor.commit();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            wg_textname.setText("请登录" );
                            RequestOptions options = new RequestOptions().circleCrop();
                            Glide.with(getActivity()).load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1404513126,3004416245&fm=26&gp=0.jpg").apply(options).into(wg_imghead);

                        }
                    });
                    Log.e("清除结果","成功");
                    break;
                }
                case R.id.imghead:{
                    //更换头像
                    //
                  //  isEditedHead = true;
                    //askPermission();
                }
            }
        }
    }



}
