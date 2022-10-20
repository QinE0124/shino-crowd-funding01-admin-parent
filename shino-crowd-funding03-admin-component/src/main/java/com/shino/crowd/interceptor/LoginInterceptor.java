package com.shino.crowd.interceptor;

import com.shino.crowd.constant.CrowdConstant;
import com.shino.crowd.entity.Admin;
import com.shino.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author QinE
 * @create 2022-10-19 17:47
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.通过request对象获取session对象
        HttpSession session = request.getSession();

        // 2.尝试从session域中获取Admin对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);

        // 3.判断admin对象是否为空
        if (admin == null) {

            // 4.抛出异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }

        // 5.如果Admin对象不为null，则返回true放行
        return true;
    }
}
