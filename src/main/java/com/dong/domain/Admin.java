package com.dong.domain;

import lombok.Data;

import java.io.Serializable;

@Data

public class Admin implements Serializable {
    /**
     * 管理员ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private static final long serialVersionUID = 1L;
}
