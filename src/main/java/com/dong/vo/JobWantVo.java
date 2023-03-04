package com.dong.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 橙宝cc
 * @date 2023/3/3 - 16:28
 */
@Data
public class JobWantVo {
    private Long id;

    private String jobTitle;

    private String jobDesc;

    private Double feesLow;

    private Double feesHigh;

    private Date createTime;

    private Integer jobStatus;

    private Long employeeId;

}
