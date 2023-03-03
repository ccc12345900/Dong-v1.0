package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.Employer;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
public interface EmployerService extends IService<Employer> {

    /**
     * 保存个人基本信息
     *
     * @param employer
     * @return
     */
    Employer saveEmployer(Employer employer);

    Employer login(String username, String password);

    Integer getAllCount();

    /**
     * 根据用户名获取雇主信息
     *
     * @param username 用户名
     * @return
     */
    Employer getByUsername(String username);

    /**
     * 雇主注册
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);
    /**
     * 添加任务技能
     *
     * @param taskId    技能 ID
     * @param skillName 技能名称
     */
    void addSkill(Long taskId, String skillName);

    /**
     * 修改密码
     *
     * @param id 雇主 ID
     * @param password 密码
     * @param newPassword 新密码
     * @return
     */
    String updatePass(Long id, String password, String newPassword);
    /**
     * 删除任务技能
     *
     * @param skillId
     */
    void deleteSkill(Long skillId);

    /**
     * 获取所有雇主
     *
     * @return
     */
    List<Employer> getAll();
}
