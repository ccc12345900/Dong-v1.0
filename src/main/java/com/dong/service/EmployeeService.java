package com.dong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.Employee;
import com.dong.vo.EmployeeVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 根据 ID 获取雇员信息
     *
     * @param employeeId
     * @return
     */
    EmployeeVo getById(Long employeeId);

    Employee login(String username, String password);

    Integer getAllCount();

    Employee getByUsername(String username);

    /**
     * 雇员注册
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    void register(String username, String password);

    /**
     * 总浏览次数
     *
     * @return
     */
    Integer getBowerCount(Long employeeId);

    /**
     * 保存个人信息
     *
     * @param employee
     * @return
     */
    Employee saveEmployee(Employee employee);

    /**
     * 修改密码
     *
     * @param password
     * @param newPassword
     * @return
     */
    String updatePass(Long employeeId, String password, String newPassword);

    void bower(Long employeeId, Long id);

    /**
     * 添加技能
     *
     * @param id        雇员 ID
     * @param skillName 技能名称
     */
    void addSkill(Long id, String skillName);

    /**
     * 删除技能
     *
     * @param skillId
     */
    void deleteSkill(Long skillId);

    /**
     * 获取最近注册的 10 个会员
     *
     * @return
     */
    List<Employee> getRecently();
    /**
     * 查询所有雇员信息
     *
     * @return
     */
    List<Employee> getAll();

    /***
     * 添加用户评论
     * @param employee
     * @return
     */
    Employee saveEmployeeComment(Employee employee);
}
