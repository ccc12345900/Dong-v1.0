package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.JobWant;
import com.dong.result.PageResult;
import com.dong.vo.JobWantVo;
import com.dong.vo.TaskVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-03-03
 */
public interface JobWantService extends IService<JobWant> {

    List<JobWantVo> getRecently();

    /**
     * 分页查询任务列表
     *
     * @param categoryId 任务 ID
     * @param key        查询条件
     * @param page       第几页
     * @return
     */
    PageResult<JobWantVo> page(int page);

    void postJob(JobWant jobWant);

    List<JobWantVo> getUnCheckAll();

    void checkSuccess(Long taskId);

    void unCheckSuccess(Long taskId);
}
