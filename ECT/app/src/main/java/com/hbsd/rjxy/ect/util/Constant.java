package com.hbsd.rjxy.ect.util;

import android.Manifest;

/**
 *
 * 常量类
 * 要多写注释
 *
 */
public class Constant {


    public static String ip="192.168.0.105:8080";

    public static String[] TAB_STRING = {"首页","圈子","我的猫","直播","我的"}; //tabhost中的名词

    public static String LOGIN_URL="http://"+ip+"/login/";//登录的URL

    public static String UPLOAD_USERHEAD_TOKEN_URL="http://"+ip+"/Self/getToken";//上传用户头像获取token

    public static String GET_USER_URL="http://"+ip+"/Self/";//操作当前登录的用户信息
    //http://10.7.88.102:8080/Self/
    //http://47.94.171.160:8081/Self/


    public static final String QINIU_URL = "q20jftoug.bkt.clouddn.com";   //七牛服务器地址

    //调用图片选择器必须要的权限
    public static final String[] PERMISSION_NECESSARY = {Manifest.permission.INTERNET,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};

    public static final int PICTURESELECT_VIDEO = 121;
    public static final int PICTURESELECT_CAMERA = 122;

    public static final String PUBLISH_SP_NAME = "PUBLISH_PREFERENCE";  //草稿，以及其他和用户发布相关的sharepreference
    public static final String LOGIN_SP_NAME="loginInfo";//与登录有关的sp,存储用户id（uid），是否有密码（hasPassword）
                                                        //若为QQ授权登录，存储的是qqOpenid，用户名（username），头像地址（userHeadPath），性别（gender）
    public static final String HAS_PASSWORD="hasPassword";// 登录后用户是否有密码，true：有，false：无
    public static final String LOGIN_USERNAME="username";//登录后用户的用户名
    public static final String LOGIN_HEADPATH="userHeadPath";//登录后用户的头像地址无地址为"null"

    public static String KEYFROM = "dancizhushou00";//5425BFA77E53C82A6E0F30E888941BBE

    public static String KEY = "2102848981";

    public static String CIBAKEY = "5425BFA77E53C82A6E0F30E888941BBE";



}

