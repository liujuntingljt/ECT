package com.hbsd.rjxy.ect.zsh.self.service;

import com.hbsd.rjxy.ect.ljt.Login.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    /**
     *
     * 获取token
     * @return
     */
    public String getToken(){
        return new QiniuUtils().getUpToken();
    }


}
