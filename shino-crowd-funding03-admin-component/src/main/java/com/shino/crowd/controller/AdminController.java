package com.shino.crowd.controller;

import com.shino.crowd.constant.CrowdConstant;
import com.shino.crowd.entity.Admin;
import com.shino.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author QinE
 * @create 2022-10-19 16:06
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPwd") String userPwd,
            HttpSession session
    ) {
       // 调用Service方法执行登陆检查
        // 如果这个方法能返回admin对象，说明登陆成功，如果账号、密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPwd);

        // 将登陆成功返回的admin对象存入Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        return "redirect:/admin/to/main/page";
    }

    @RequestMapping("/admin/do/logout")
    public String doLogout(HttpSession session) {

        //强制Session失效
        session.invalidate();

        return "redirect:/admin/to/login/page";
    }
}
