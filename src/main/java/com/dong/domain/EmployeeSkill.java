package com.dong.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeSkill implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 雇员技能对应ID
     */
      private Long id;

    /**
     * 雇员ID
     */
    private Long employeeId;

    /**
     * 技能名称
     */
    private String skillName;


}
