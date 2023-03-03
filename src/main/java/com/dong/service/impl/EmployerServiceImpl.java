package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.domain.Employer;
import com.dong.domain.TaskSkill;
import com.dong.mapper.EmployerMapper;
import com.dong.mapper.TaskSkillMapper;
import com.dong.service.EmployerService;
import com.dong.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
@Service
public class EmployerServiceImpl extends ServiceImpl<EmployerMapper, Employer> implements EmployerService {

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private TaskSkillMapper taskSkillMapper;

    @Override
    public Employer saveEmployer(Employer employer) {
        // 更新
        employerMapper.updateById(employer);

        // 重新查询雇主信息
        Employer currEmployer = employerMapper.selectById(employer.getId());
        return currEmployer;
    }

    @Override
    public Employer login(String username, String password) {
        //填写查询条件
        QueryWrapper<Employer> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("username",username);
        // 根据用户名获取用户信息
        Employer employer = employerMapper.selectOne(employeeQueryWrapper);

        // 验证密码是否正确,密码一致登录成功
        if (employer != null && employer.getPassword().equals(password)) {
            System.out.println("雇主登录成功");
            return employer;
        }

        // 密码不一致，返回 null
        return null;
    }

    @Override
    public Integer getAllCount() {
        // 调用 employerMapper 查询所有雇主数据
        List<Employer> employers = employerMapper.selectList(null);
        // 三元表达式返回总数，如果 employers 不为空返回集合的数据，为空返回 0
        return employers != null ? employers.size() : 0;
    }

    @Override
    public Employer getByUsername(String username) {
        // 构造查询条件
        QueryWrapper<Employer> employerQueryWrapper = new QueryWrapper<>();
        // 根据用户名查询
        QueryWrapper<Employer> username1 = employerQueryWrapper.eq("username", username);
        // 执行查询
        Employer employer = employerMapper.selectOne(username1);
        return employer;
    }

    @Override
    public void register(String username, String password) {
        Employer employer = new Employer();
        // 设置 ID,使用 IDUtil 获取随机ID
        employer.setId(IDUtil.getRandomId());
        // 设置用户名
        employer.setUsername(username);
        // 设置密码
        employer.setPassword(password);
        // 设置创建时间
        employer.setCreateTime(new Date());
        // 设置默认头像
        employer.setHeadImg("http://recruit1.oss-cn-shenzhen.aliyuncs.com/10f65b3a-e73d-4d8b-b95b-3841534ea0dc.png");
        // 插入到数据库
        employerMapper.insert(employer);
    }

    @Override
    public void addSkill(Long taskId, String skillName) {
        TaskSkill taskSkill = new TaskSkill();
        taskSkill.setId(IDUtil.getRandomId());
        taskSkill.setTaskId(taskId);
        taskSkill.setSkillName(skillName);
        taskSkillMapper.insert(taskSkill);
    }

    @Override
    public String updatePass(Long employerId, String password, String newPassword) {
        // 根据主键查询雇员ID信息
        Employer employer = employerMapper.selectById(employerId);

        // 更新密码
        if (employer != null && employer.getPassword().equals(password)) {
            employer.setPassword(newPassword);
            employerMapper.updateById(employer);
            return "修改密码成功";

        }

        // 旧密码错误
        else {
            return "旧密码输入错误";
        }
    }

    @Override
    public void deleteSkill(Long skillId) {
        taskSkillMapper.deleteById(skillId);
    }

    @Override
    public List<Employer> getAll() {
        return employerMapper.selectList(null);
    }
}
