package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.TaskCategory;
import com.dong.vo.TaskCategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
public interface TaskCategoryService extends IService<TaskCategory> {
    List<TaskCategoryVo> getAll();

    List<TaskCategoryVo> getPopular();
    /**
     * 设为热门
     *
     * @param id
     */
    void onPopular(Long id);

    /**
     * 解除热门
     *
     * @param id
     */
    void closePopular(Long id);

}
