package com.hbsd.rjxy.ect.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbsd.rjxy.ect.R;
import com.hbsd.rjxy.ect.base.BaseFragment;
import com.hbsd.rjxy.ect.entity.EveryDayWords;
import com.hbsd.rjxy.ect.util.HttpMethods;
import com.hbsd.rjxy.ect.util.HttpUtil;

import java.io.IOException;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 *每日一句
 */
public class MiaojuFragment extends BaseFragment {

    private View v;
    private ImageView img, voice;
    private TextView content1, content2;
    private MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_miaoju, container, false);
        initView();
        getData();
        return v;
    }

    private void getData() {
        if(!HttpUtil.isNetworkAvailable(getActivity())){
            showToast("当前网络不可用");
            voice.setVisibility(View.GONE);
            return;
        }

        HttpMethods.getInstance().getEveryDayWords().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<EveryDayWords>() {
            @Override
            public void call(final EveryDayWords everyDayWords) {
                content1.setText(everyDayWords.getContent());
                content2.setText(everyDayWords.getNote());
                Glide.with(getActivity()).load(everyDayWords.getPicture2()).into(img);
                voice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mp.reset();
                        try {
                            mp.setDataSource(everyDayWords.getTts());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mp.prepareAsync();
                    }
                });
            }
        });
    }

    @Override
    public void initView() {
        img = findImageViewbyId(v, R.id.frag_tui_img);
        voice = findImageViewbyId(v, R.id.frag_tui_voice);
        content1 = findTextViewbyId(v, R.id.frag_tui_content1);
        content2 = findTextViewbyId(v, R.id.frag_tui_content2);
        mp = new MediaPlayer();

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()) {
            mp.stop();
        }
        mp.release();
    }

}
