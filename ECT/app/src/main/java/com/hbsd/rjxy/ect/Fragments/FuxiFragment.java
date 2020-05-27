package com.hbsd.rjxy.ect.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbsd.rjxy.ect.R;
import com.hbsd.rjxy.ect.activity.DictionaryActivity;
import com.hbsd.rjxy.ect.activity.PlanActivity;
import com.hbsd.rjxy.ect.activity.WordBookActivity;
import com.hbsd.rjxy.ect.base.BaseFragment;

/**

 */
public class FuxiFragment extends BaseFragment {


    private View v;
    private TextView frag_my_danciben, frag_my_plan, frag_my_dict;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fuxi, null);
        initView();
        return v;
    }


    @Override
    public void initView() {
        frag_my_danciben = findTextViewbyId(v, R.id.frag_my_danciben);
        frag_my_plan = findTextViewbyId(v, R.id.frag_my_plan);
        frag_my_dict = findTextViewbyId(v, R.id.frag_my_dict);
        frag_my_danciben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WordBookActivity.class));
            }
        });
        frag_my_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PlanActivity.class));
            }
        });
        frag_my_dict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DictionaryActivity.class));
            }
        });
    }
}
