package com.dong.domain;

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
@EqualsAndHashCode(callSuper = false)
public class Complaints implements Serializable {

    private static final long serialVersionUID=1L;

      private Long id;

    private Long complainant;

    private String content;

    private Date createTime;
}
