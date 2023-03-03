package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.domain.EmployeeBookmarked;
import com.dong.mapper.EmployeeBookmarkedMapper;
import com.dong.service.EmployeeBookmarkedService;
import com.dong.service.TaskService;
import com.dong.utils.IDUtil;
import com.dong.vo.EmployeeBookmarkedVo;
import com.dong.vo.TaskVo;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 雇员收藏业务逻辑接口实现
 */
@Service
public class EmployeeBookmarkedServiceImpl implements EmployeeBookmarkedService {

    @Resource
    private EmployeeBookmarkedMapper employeeBookmarkedMapper;

    @Resource
    private TaskService taskService;

    @Override
    public EmployeeBookmarked bookmarked(Long id, Long taskId) {
        // 先根据雇员ID和任务ID获取收藏情况
        QueryWrapper<EmployeeBookmarked> employeeBookmarkedQueryWrapper = new QueryWrapper<>();
        employeeBookmarkedQueryWrapper.eq("employee_id", id).eq("task_id", taskId);
        EmployeeBookmarked employeeBookmarked = employeeBookmarkedMapper.selectOne(employeeBookmarkedQueryWrapper);

        // 如果查询到了说明雇员已经收藏该任务，取消收藏,删除该条记录
        if (employeeBookmarked != null) {
            employeeBookmarkedMapper.delete(employeeBookmarkedQueryWrapper);
        }

        // 如果没有查询到，说明雇员没有收藏该任务，添加一条收藏记录
        else {
            employeeBookmarked = new EmployeeBookmarked();
            employeeBookmarked.setId(IDUtil.getRandomId());
            employeeBookmarked.setEmployeeId(id);
            employeeBookmarked.setTaskId(taskId);
            employeeBookmarkedMapper.insert(employeeBookmarked);
        }

        return employeeBookmarked;
    }

    @Override
    public List<EmployeeBookmarkedVo> getByEmployeeId(Long employeeId) {
        QueryWrapper<EmployeeBookmarked> employeeBookmarkedQueryWrapper = new QueryWrapper<>();
        employeeBookmarkedQueryWrapper.eq("employee_id", employeeId);
        List<EmployeeBookmarked> employeeBookmarkeds = employeeBookmarkedMapper.selectList(employeeBookmarkedQueryWrapper);
        // 转换为视图展示对象
        List<EmployeeBookmarkedVo> employeeBookmarkedVos = new ArrayList<>();
        for (EmployeeBookmarked employeeBookmarked : employeeBookmarkeds) {
            // 创建一个 Vo 对象
            EmployeeBookmarkedVo employeeBookmarkedVo = new EmployeeBookmarkedVo();
            // 设置 ID
            employeeBookmarkedVo.setId(employeeId);
            // 查询任务信息
            TaskVo taskVo = taskService.getById(employeeBookmarked.getTaskId());
            // 设置任务信息
            employeeBookmarkedVo.setTaskVo(taskVo);
            employeeBookmarkedVos.add(employeeBookmarkedVo);
        }
        return employeeBookmarkedVos;
    }

    @Override
    public List<Long> getIdsByEmployeeId(Long employeeId) {
        QueryWrapper<EmployeeBookmarked> employeeBookmarkedQueryWrapper = new QueryWrapper<>();
        employeeBookmarkedQueryWrapper.eq("employee_id", employeeId);
        List<EmployeeBookmarked> employeeBookmarkeds = employeeBookmarkedMapper.selectList(employeeBookmarkedQueryWrapper);
        List<Long> ids = new ArrayList<>();
        for (EmployeeBookmarked employeeBookmarked : employeeBookmarkeds) {
            Long aLong = new Long(employeeBookmarked.getTaskId());
            ids.add(aLong);
        }
        return ids;
    }

    @Override
    public void remove(Long id, Long taskId) {
        // 构造查询条件
        QueryWrapper<EmployeeBookmarked> employeeBookmarkedQueryWrapper = new QueryWrapper<>();
        employeeBookmarkedQueryWrapper.eq("employee_id", id).eq("task_id", taskId);
        // 删除收藏
        employeeBookmarkedMapper.delete(employeeBookmarkedQueryWrapper);
    }
}
