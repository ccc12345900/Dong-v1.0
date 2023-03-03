package com.dong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.Admin;


/**
 * 管理员的业务逻辑接口
 */
public interface AdminService extends IService<Admin> {

     String updatePass(String username, String password, String newPassword);

    /**
     * 管理员登录
     *
     * @param admin 管理员
     * @return
     */
    Admin login(Admin admin);
}
