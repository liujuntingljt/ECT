package com.hbsd.rjxy.ect.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbsd.rjxy.ect.R;
import com.hbsd.rjxy.ect.activity.MainActivity;
import com.hbsd.rjxy.ect.activity.PhoneLoginActivity;
import com.hbsd.rjxy.ect.base.BaseFragment;
import com.hbsd.rjxy.ect.util.Constant;

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


    private TextView wg_textFuxi;


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
        return view;

    }





    @Override
    public void initView() {
         wg_imghead =(ImageView)  view.findViewById(R.id.imghead);
         wg_textname =(TextView)  view.findViewById(R.id.textname);
         wg_textReset=(TextView) view.findViewById(R.id.text20);
         wg_textReset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });
    }

    public void setUser(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constant.LOGIN_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.getString("uid",null)!=null){

            String uid = sharedPreferences.getString("uid",null);
            String userName=sharedPreferences.getString("username","请登录");
            final String userImg=sharedPreferences.getString("userHeadPath","http://www.pc6.com/public/images/azlogo.png");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getActivity()).load(userImg).into(wg_imghead);
                }
            });
        }
        else{
            Intent intent=new Intent();
            intent.setClass( this.getActivity(),PhoneLoginActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
