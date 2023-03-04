package com.dong.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.domain.Employee;
import com.dong.domain.EmployeeSkill;
import com.dong.domain.HomeBower;
import com.dong.mapper.EmployeeMapper;
import com.dong.mapper.EmployeeSkillMapper;
import com.dong.mapper.HomeBowerMapper;
import com.dong.service.EmployeeService;
import com.dong.utils.IDUtil;
import com.dong.vo.EmployeeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeSkillMapper employeeSkillMapper;

    @Autowired
    private HomeBowerMapper homeBowerMapper;

    @Override
    public EmployeeVo getById(Long employeeId) {
        // 查询雇员信息
        Employee employee = employeeMapper.selectById(employeeId);

        // 查询雇员技能信息
        QueryWrapper<EmployeeSkill> employeeSkillQueryWrapper = new QueryWrapper<>();
        employeeSkillQueryWrapper.eq("employee_id", employee.getId());

        // 转换为视图展示对象
        EmployeeVo employeeVo = new EmployeeVo();
        BeanUtils.copyProperties(employee, employeeVo);

        // 查询出雇员所有技能
        List<EmployeeSkill> employeeSkills = employeeSkillMapper.selectList(employeeSkillQueryWrapper);
        employeeVo.setSkills(employeeSkills);
        return employeeVo;
    }

    @Override
    public Employee login(String username, String password) {
        //填写查询条件
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("username",username);
        // 根据用户名获取用户信息
        Employee employee = employeeMapper.selectOne(employeeQueryWrapper);


        // 验证密码是否正确,密码一致登录成功
        if (employee != null && employee.getPassword().equals(password)) {
            System.out.println("登录成功");
            return employee;
        }

        // 密码不一致，返回 null
        return null;
    }

    @Override
    public Integer getAllCount() {
        List<Employee> employees = employeeMapper.selectList(null);
        return employees != null ? employees.size() : 0;
    }

    @Override
    public Employee getByUsername(String username) {
        // 构造查询条件
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        // 按用户名查询
        employeeQueryWrapper.eq("username", username);
        Employee employee = employeeMapper.selectOne(employeeQueryWrapper);

        return employee;
    }

    @Override
    public void register(String username, String password) {
        // 新建一个雇员实体类
        Employee employee = new Employee();
        // 设置 ID
        employee.setId(IDUtil.getRandomId());
        // 设置用户名
        employee.setUsername(username);
        // 设置密码
        employee.setPassword(password);
        // 设置创建时间
        employee.setCreateTime(new Date());
        // 设置主页点击次数
        employee.setBrowseCount(0);
        // 设置默认头像
        employee.setHeadImg("http://recruit1.oss-cn-shenzhen.aliyuncs.com/10f65b3a-e73d-4d8b-b95b-3841534ea0dc.png");

        // 插入到数据库
        employeeMapper.insert(employee);
    }

    @Override
    public Integer getBowerCount(Long employeeId) {
        // 根据雇员主键ID查询出雇员信息
        Employee employee = employeeMapper.selectById(employeeId);
        // 获取雇员主页浏览数量
        Integer bowerCount = employee.getBrowseCount();
        return bowerCount;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        // 更新
        employeeMapper.updateById(employee);

        // 重新查询雇员信息
        Employee currEmployee = employeeMapper.selectById(employee.getId());
        return currEmployee;
    }

    @Override
    public String updatePass(Long employeeId, String password, String newPassword) {
        // 根据主键查询雇员ID信息
        Employee employee = employeeMapper.selectById(employeeId);

        // 更新密码
        if (employee != null && employee.getPassword().equals(password)) {
            employee.setPassword(newPassword);
            employeeMapper.updateById(employee);
            return "修改密码成功";

        }

        // 旧密码错误
        else {
            return "旧密码输入错误";
        }
    }

    @Override
    public void bower(Long employeeId, Long employerId) {
// 增加一条主页浏览记录
        HomeBower homeBower = new HomeBower();
        homeBower.setId(IDUtil.getRandomId());
        homeBower.setEmployeeId(employeeId);
        homeBower.setEmployerId(employerId);
        homeBower.setCreateTime(new Date());
        homeBowerMapper.insert(homeBower);

        // 雇员主页访问次数 + 1
        Employee employee = employeeMapper.selectById(employeeId);
        Integer bowerCount = employee.getBrowseCount() + 1;
        employee.setBrowseCount(bowerCount);
        employeeMapper.updateById(employee);
    }

    @Override
    public void addSkill(Long id, String skillName) {
    // 创建一个技能实体类，添加到数据库
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setId(IDUtil.getRandomId());
        employeeSkill.setSkillName(skillName);
        employeeSkill.setEmployeeId(id);
        employeeSkillMapper.insert(employeeSkill);
    }

    @Override
    public void deleteSkill(Long skillId) {
        employeeSkillMapper.deleteById(skillId);
    }

    @Override
    public List<Employee> getRecently() {
        // 使用 PageHelper 分页查询
        PageHelper.startPage(0, 10);

        // 设置分页查询条件
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        // 按照创建时间倒叙查询
        employeeQueryWrapper.orderByDesc("create_time");
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employeeMapper.selectList(employeeQueryWrapper));

        // 获取查询结果
        List<Employee> list = employeePageInfo.getList();

        return list;
    }

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectList(null);
    }

    @Override
    public Employee saveEmployeeComment(Employee employee) {
        Employee employee1 = employeeMapper.selectById(employee.getId());
        employee1.setProfile(employee1.getProfile()+"\r\n"+"商户评价："+employee.getProfile());
        System.out.println(employee1.getProfile());
        // 更新
        employeeMapper.updateById(employee1);

        // 重新查询雇员信息
        Employee currEmployee = employeeMapper.selectById(employee.getId());
        return currEmployee;
    }


}
