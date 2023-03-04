package com.dong.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2023-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class JobWant implements Serializable {

    private static final long serialVersionUID=1L;

      private Long id;

    private String jobTitle;

    private String jobDesc;

    private Double feesLow;

    private Double feesHigh;

    private Date createTime;

    private Integer jobStatus;

    private Long employeeId;


}
