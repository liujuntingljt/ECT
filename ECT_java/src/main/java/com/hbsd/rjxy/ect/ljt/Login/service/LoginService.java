package com.hbsd.rjxy.ect.ljt.Login.service;

import com.hbsd.rjxy.ect.entity.User;
import com.hbsd.rjxy.ect.ljt.Login.Constant;
import com.hbsd.rjxy.ect.ljt.Login.dao.LoginDao;
import com.hbsd.rjxy.ect.ljt.Login.util.RandomUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;
    /**
     * 增加用户
     * @param tel
     */
    @Transactional
    public void saveUser(String tel, Date uregist){
        User user=new User();
        user.setUserPhone(DigestUtils.md5Hex(DigestUtils.md5Hex(tel)+ Constant.SALT));
        user.setCreateTime(uregist);
        user.setUserName(RandomUtil.getStringRandom());
        loginDao.save(user);
    }

    /**
     * 新增QQ登陆用户
     */
    @Transactional
    public void saveQQUser(String openid,String userName,String sex,String headId, Date uregist){
        User user=new User();
        user.setQqOpenid(openid);
        user.setUserName(userName);
        user.setUserSex(sex);
        user.setHeadId(headId);
        user.setCreateTime(uregist);
        loginDao.save(user);
    }

    /**
     * 根据手机号查询user
     * @param tel
     */
    @Transactional
    public User findUserByTel(String tel){
        User user=loginDao.findUserByUserPhone(DigestUtils.md5Hex(DigestUtils.md5Hex(tel)+ Constant.SALT));
        return user;
    }

    /**
     * 根据uid查询user
     * @param uid
     * @return user
     */
    @Transactional
    public User findUserById(Integer uid){
        User user=loginDao.findUserById(uid);
        return user;
    }

    @Transactional
    public User findUserByQqOpenid(String qqOpenid){
        return loginDao.findUserByQqOpenid(qqOpenid);
    }
}
