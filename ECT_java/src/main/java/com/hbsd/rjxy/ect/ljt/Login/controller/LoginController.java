package com.hbsd.rjxy.ect.ljt.Login.controller;

import com.hbsd.rjxy.ect.entity.User;
import com.hbsd.rjxy.ect.ljt.Login.Constant;
import com.hbsd.rjxy.ect.ljt.Login.service.LoginService;
import com.hbsd.rjxy.ect.ljt.Login.util.DecodeUtil;
import com.hbsd.rjxy.ect.ljt.Login.util.RequestUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/qq")
    @ResponseBody
    public void userLoginByQQ(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        try {
            String jsonStr= RequestUtil.getJson(request);
            JSONObject object=new JSONObject(jsonStr);
            JSONObject res=new JSONObject();
            if(object.length()!=0){
                String qqOpenid=object.getString("qqOpenid");
                String userName=object.getString("userName");
                String headId=object.getString("userHeadPath");
                System.out.println(headId);
                String userSex=object.getString("gender");
                User user=loginService.findUserByQqOpenid(qqOpenid);
                if (user==null){
                    //user 为空，表示此qq用户首次登陆， 进行信息保存
                    System.out.println(userName+"QQ新用户登陆!");
                    Date date=new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String temp=sdf.format(date);
                    Date time=null;
                    try {
                        time = sdf.parse(temp);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    loginService.saveQQUser(qqOpenid,userName,userSex,headId,time);
                    User userSave=loginService.findUserByQqOpenid(qqOpenid);
                    res.put("result","true");
                    res.put("uid",userSave.getId());
                }else {
                    System.out.println(userName+"QQ用户登陆!");
                    res.put("result","true");
                    res.put("uid",user.getId());
                }
            }
            response.getWriter().append(res.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 密码登录
     * @param request
     * @param response
     */
    @RequestMapping("/password")
    @ResponseBody
    public void loginUser(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        try {
            String jsonStr= RequestUtil.getJson(request);
            JSONObject object=new JSONObject(jsonStr);
            JSONObject res=new JSONObject();
            if(object.length()!=0){
                String phone = DecodeUtil.decodeToString(object.getString("tel"));
                String password=DecodeUtil.decodeToString(object.getString("pwd"));
                System.out.println(phone+"====="+password);
                User user=loginService.findUserByTel(phone);
                if (user==null){
                    //user 为空，表示未注册， 提示用户进行验证码登录
                    res.put("result","false");
                }else {
                    if (user.getUserPwd()!=null){//用户已经设置密码
                        if (user.getUserPwd().equals(DigestUtils.md5Hex(DigestUtils.md5Hex(password)+ Constant.SALT))){//密码正确
                            res.put("result","true");
                            res.put("uid",user.getId());
                            res.put("username",user.getUserName());
                            res.put("userHeadPath",user.getHeadId()+"");
                        }else {
                            res.put("result","error");
                        }
                    }else{//用户未设置密码，提示用户手机验证码登录，可登录后设置密码
                        res.put("result","null");
                    }
                }
            }
            response.getWriter().append(res.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 手机验证码方式登录
     * @param request
     * @param response
     */
    @RequestMapping("/phone")
    @ResponseBody
    public void loginUserForPhone(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        try {
            String jsonStr= RequestUtil.getJson(request);
            JSONObject object=new JSONObject(jsonStr);
            JSONObject res=new JSONObject();
            if(object.length()!=0){
                String phone = DecodeUtil.decodeToString(object.getString("tel"));
                User user=loginService.findUserByTel(phone);
                if (user==null){
                    //user 为空，表示未注册， 进行注册
                    System.out.println(phone+"新用户注册!");
                    Date date=new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String temp=sdf.format(date);
                    Date time=null;
                    try {
                        time = sdf.parse(temp);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    loginService.saveUser(phone,time);
                    User userSave=loginService.findUserByTel(phone);
                    res.put("result","true");
                    res.put("uid",userSave.getId());
                    res.put("hasPassword","false");
                    res.put("username",userSave.getUserName());
                    res.put("userHeadPath",userSave.getHeadId()+"");
                }else {
                    System.out.println("用户验证码登录");
                    res.put("result","true");
                    res.put("uid",user.getId());
                    res.put("username",user.getUserName());
                    res.put("userHeadPath",user.getHeadId()+"");
                    if (user.getUserPwd()==null){
                        res.put("hasPassword","false");
                    }else {
                        res.put("hasPassword","true");
                    }
                }
            }
            response.getWriter().append(res.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("findOne")
    @ResponseBody
    public String findAll(){
        return loginService.findUserById(1).toString();
    }
}
