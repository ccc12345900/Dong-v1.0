package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.Task;
import com.dong.result.PageResult;
import com.dong.vo.TaskVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
public interface TaskService extends IService<Task> {
    Integer getAllCount();
    List<TaskVo> getRecently();

    /**
     * 根据完成雇员 ID 获取中标总数
     *
     * @param id
     * @return
     */
    Integer getByBidEmployeeId(Long id);

    List<TaskVo> getByEmployeeId(Long employeeId);

    List<TaskVo> getCompletedByEmployeeId(Long id);

    TaskVo getById(Long taskId);
    /**
     * 获取雇员待完成的订单
     *
     * @param id 雇员 ID
     * @return
     */
    List<TaskVo> getUnCompletedByEmployeeId(Long id);


    /**
     * 雇员提交任务
     *
     * @param id     雇员 ID
     * @param taskId 任务 ID
     */
    void submitTask(Long id, Long taskId);
    /**
     * 分页查询任务列表
     *
     * @param categoryId 任务 ID
     * @param key        查询条件
     * @param page       第几页
     * @return
     */
    PageResult<TaskVo> page(Long categoryId, String key, int page);
    /**
     * 雇主获取任务总发布数量
     *
     * @param id
     * @return
     */
    List<TaskVo> getByEmployerId(Long id);
    /**
     * 获取所有任务总投标数
     *
     * @param id
     * @return
     */
    Integer getBidCount(Long id);

    /**
     * 获取任务提交请求
     *
     * @param id
     * @return
     */
    List<TaskVo> getRecentlySubmit(Long id);

    /**
     * 雇主发布任务
     *
     * @param task
     */
    void postTask(Task task, String skills, String upload);
    /**
     * 修改任务
     *
     * @param task
     */
    void updateTask(Task task);
    /**
     * 确认完成任务
     *
     * @param taskId 任务 ID
     */
    void successTask(Long taskId);
    /**
     * 获取所有任务完成数量
     *
     * @return
     */
    Integer getAllCompleteCount();
    /**
     * 获取所有任务完成价格总数
     *
     * @return
     */
    Double getAllCompletePrice();

    /**
     * 获取最近完成的十条任务
     *
     * @return
     */
    List<TaskVo> getRecentlyComplete();
    /**
     * 获取所有任务
     *
     * @return
     */
    List<TaskVo> getAll();

    /**
     * 查询所有待审核任务
     *
     * @return
     */
    List<TaskVo> getUnCheckAll();

    /**
     * 通过审核
     *
     * @param taskId 任务 ID
     */
    void checkSuccess(Long taskId);

    /**
     * 审核失败
     *
     * @param taskId 任务 ID
     */
    void unCheckSuccess(Long taskId);
}
