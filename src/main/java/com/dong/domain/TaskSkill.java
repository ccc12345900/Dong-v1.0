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
public class TaskSkill implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 任务技能ID
     */
      private Long id;

    /**
     * 技能名称
     */
    private String skillName;

    /**
     * 任务ID
     */
    private Long taskId;


}
