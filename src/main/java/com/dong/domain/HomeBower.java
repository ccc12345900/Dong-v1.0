package com.dong.domain;

import lombok.Data;


import java.io.Serializable;
import java.util.Date;

@Data

public class HomeBower implements Serializable {
    /**
     * 主页浏览表
     */

    private Long id;

    /**
     * 雇员ID
     */

    private Long employeeId;

    /**
     * 雇主ID
     */

    private Long employerId;

    /**
     * 浏览时间
     */

    private Date createTime;

    private static final long serialVersionUID = 1L;
}
