package com.common.utils;

import com.common.base.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 */
public class SessionUtil {

    /**
     * 获取登录用户的userId
     * @return
     */
    public static Integer getloginUserId()
    {
        Subject subject = SecurityUtils.getSubject();
        User sessionUser = (User)subject.getSession().getAttribute("userSession");
        return sessionUser.getID();
    }

    /**
     * 获取登录用户的userId
     * @return
     */
    public static String getloginUserAccountName()
    {
        Subject subject = SecurityUtils.getSubject();
        User sessionUser = (User)subject.getSession().getAttribute("userSession");
        return sessionUser.getUserName();
    }

    /**
     * 返回用户的IP地址
     * @param request
     * @return
     */
    public static String getUserIP() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession().getHost();
    }

    public static Object getAttr(Object key) {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession().getAttribute(key);
    }
}
