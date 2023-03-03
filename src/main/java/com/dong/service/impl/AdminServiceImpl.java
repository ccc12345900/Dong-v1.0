package com.dong.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.domain.Admin;
import com.dong.domain.Bid;
import com.dong.domain.Employee;
import com.dong.mapper.AdminMapper;
import com.dong.mapper.BidMapper;
import com.dong.service.AdminService;
import com.dong.service.BidService;
import com.dong.vo.BidVo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员的业务逻辑实现
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public String updatePass(String username, String password, String newPassword) {
        // 根据主键查询雇员ID信息
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username);
        Admin admin = adminMapper.selectOne(adminQueryWrapper);

        // 更新密码
        if (admin != null && admin.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            admin.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            adminMapper.updateById(admin);
            return "修改密码成功";

        }

        // 旧密码错误
        else {
            return "旧密码输入错误";
        }
    }

    @Override
    public Admin login(Admin admin) {
        // 查询出用户名的管理员
        // 使用 tk.mybatis 进行查询，不需要编写 xml 文件
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        // 构造查询条件, 根据用户名查询
        adminQueryWrapper.eq("username", admin.getUsername());
        // 使用 adminMapper 查询
        Admin currAdmin = adminMapper.selectOne(adminQueryWrapper);

        // 判断密码是否正确, 密码采用 MD5 加密，所以需要把页面传过来的密码加密后进行比较
        if (currAdmin != null && currAdmin.getPassword().equals(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()))) {
            // 密码相同登录成功
            return currAdmin;
        }

        // 密码不相同，登录失败,返回一个 null 值
        else {
            return null;
        }
    }

}
