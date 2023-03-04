package com.dong.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
public class ComplaintsVo implements Serializable {


      private Long id;

    private Long complainant;

    private String content;

    private Date createTime;
}
