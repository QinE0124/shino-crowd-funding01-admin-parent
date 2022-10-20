package com.shino.crowd.service.api;

import com.shino.crowd.entity.Admin;

import java.util.List;

/**
 * @author QinE
 * @create 2022-10-18 11:46
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPwd);
}
