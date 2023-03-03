package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.consts.TaskCategoryStatus;
import com.dong.consts.TaskStatus;
import com.dong.domain.Task;
import com.dong.domain.TaskCategory;
import com.dong.mapper.TaskCategoryMapper;
import com.dong.mapper.TaskMapper;
import com.dong.service.TaskCategoryService;
import com.dong.vo.TaskCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
@Service
public class TaskCategoryServiceImpl extends ServiceImpl<TaskCategoryMapper, TaskCategory> implements TaskCategoryService {

    @Autowired
    private TaskCategoryMapper taskCategoryMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskCategoryVo> getAll() {
        // 查询所有
        List<TaskCategory> taskCategories = taskCategoryMapper.selectList(null);

        // 将所有的 TaskCategory 转换为 TaskCategoryVo 页面展示对象
        List<TaskCategoryVo> taskCategoryVos = taskCategoriesToTaskCategoryVos(taskCategories);

        return taskCategoryVos;
    }

    @Override
    public List<TaskCategoryVo> getPopular() {
        // 构建查询条件
        QueryWrapper<TaskCategory> taskCategoryQueryWrapper = new QueryWrapper<>();

        // 分类状态为 1 为热门分类
        taskCategoryQueryWrapper.eq("is_popular", TaskCategoryStatus.POPULAR);

        // 执行查询
        List<TaskCategory> taskCategories = taskCategoryMapper.selectList(taskCategoryQueryWrapper);

        // 将查询到的任务分类信息转换为 Vo 对象，因为页面展示需要分类数量
        List<TaskCategoryVo> taskCategoryVos = taskCategoriesToTaskCategoryVos(taskCategories);

        return taskCategoryVos;
    }

    @Override
    public void onPopular(Long id) {
        // 根据 ID 查询出任务分类信息
        TaskCategory taskCategory = taskCategoryMapper.selectById(id);
        // 设置为热门
        taskCategory.setIsPopular(TaskCategoryStatus.POPULAR);
        // 更新任务分类信息
        taskCategoryMapper.updateById(taskCategory);
    }

    @Override
    public void closePopular(Long id) {
        // 根据 ID 查询出任务分类信息
        TaskCategory taskCategory = taskCategoryMapper.selectById(id);
        // 解除热门
        taskCategory.setIsPopular(TaskCategoryStatus.NO_POPULAR);
        // 更新任务分类信息
        taskCategoryMapper.updateById(taskCategory);
    }

    private List<TaskCategoryVo> taskCategoriesToTaskCategoryVos(List<TaskCategory> taskCategories) {
        // 将所有的 TaskCategory 转换为 TaskCategoryVo 页面展示对象
        List<TaskCategoryVo> taskCategoryVos = new ArrayList<>();
        for (TaskCategory taskCategory : taskCategories) {
            TaskCategoryVo taskCategoryVo = new TaskCategoryVo();
            BeanUtils.copyProperties(taskCategory, taskCategoryVo);

            // 查询分类总任务数量
            QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
            taskQueryWrapper.eq("category_id",taskCategoryVo.getId())
                    .and(wrapper->wrapper.ne("task_status", TaskStatus.CHECK_FAIL))
                    .and(wrapper->wrapper.ne("task_status", TaskStatus.UNCHECK))
                    .and(wrapper->wrapper.ne("task_status", TaskStatus.COMPLETE));
            List<Task> tasks = taskMapper.selectList(taskQueryWrapper);
            Integer taskCount = tasks != null ? tasks.size() : 0;
            taskCategoryVo.setTaskCount(taskCount);
            // 添加到集合
            taskCategoryVos.add(taskCategoryVo);
        }
        return taskCategoryVos;
    }
}
