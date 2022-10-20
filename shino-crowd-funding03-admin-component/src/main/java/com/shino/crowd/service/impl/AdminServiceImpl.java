package com.shino.crowd.service.impl;

import com.shino.crowd.constant.CrowdConstant;
import com.shino.crowd.entity.Admin;
import com.shino.crowd.entity.AdminExample;
import com.shino.crowd.exception.LoginFailedException;
import com.shino.crowd.mapper.AdminMapper;
import com.shino.crowd.service.api.AdminService;
import com.shino.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author QinE
 * @create 2022-10-18 11:48
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPwd) {

        // 1.根据登陆账号查询Admin对象
        // 1).创建AdminExample对象
        AdminExample adminExample = new AdminExample();

        // 2).创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();

        // 3).在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        // 4).调用AdminMapper的方法执行查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.判断Admin对象是否为null
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if (admins.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = admins.get(0);

        // 3.如果Admin对象为null，则抛出异常
        if (admin == null) {
            throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.如果Admin对象不为null，则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密
        String userPswdForm = CrowdUtil.md5(userPswdDB);

        // 6.对密码进行比较
        if (!Objects.equals(userPswdDB, userPswdForm)) {
            // 7.如果比较结果不一致，则抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        //如果一致，则返回Admin对象
        return admin;
    }
}
