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
public class TaskCategory implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 任务分类ID
     */
      private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类图片展示地址
     */
    private String categoryImg;

    /**
     * 是否热门 0 否 1 热门
     */
    private Integer isPopular;


}
