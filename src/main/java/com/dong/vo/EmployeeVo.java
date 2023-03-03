package com.dong.vo;


import com.dong.domain.EmployeeSkill;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 雇员视图展示对象
 *
 * @author by yuu
 * @Classname EmployeeVo
 * @Date 2019/10/16 1:03
 */
@Data
public class EmployeeVo implements Serializable {
    /**
     * 雇员ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像地址
     */
    private String headImg;

    /**
     * 标语
     */
    private String tagline;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 主页被浏览次数
     */
    private Integer browseCount;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 雇员技能集合
     */
    private List<EmployeeSkill> skills;

    /**
     * 创建时间
     */
    private Date createTime;
}
