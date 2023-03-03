package com.dong.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeBookmarked implements Serializable {
    /**
     * 雇员收藏任务ID
     */

    private Long id;

    /**
     * 雇员ID
     */

    private Long employeeId;

    /**
     * 任务ID
     */

    private Long taskId;

    private static final long serialVersionUID = 1L;
}
