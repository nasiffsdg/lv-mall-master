package com.lv.mall.interceptor;

import com.lv.mall.constant.AuthServerConstant;
import com.lv.mall.constant.UserLevel;
import com.lv.mall.entity.UserInfo;
import com.lv.mall.to.UserInfoTo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 17324
 */

/**
 * 拦截器 就是验证登陆状态 并封装纯递给controller
 * @author 17324
 */
@Component
public class CartInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserInfoTo> userInfoToThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserInfoTo userInfoTo = new UserInfoTo();
        Object attribute = request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);

        if (attribute != null){
            UserInfo userInfo = (UserInfo) attribute;
            if (userInfo.getLevel() == 1){
                userInfoTo.setUserLevel(UserLevel.COMMON);
            }else {
                userInfoTo.setUserLevel(UserLevel.VIP);
            }
            userInfoTo.setUserInfo(userInfo);
        }else {
            userInfoTo.setUserLevel(UserLevel.NO_LOGIN);
            return false;
        }
        userInfoToThreadLocal.set(userInfoTo);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
