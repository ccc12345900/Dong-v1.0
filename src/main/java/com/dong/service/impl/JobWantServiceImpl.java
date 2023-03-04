package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.consts.JobStatus;
import com.dong.consts.TaskStatus;
import com.dong.domain.*;
import com.dong.mapper.JobWantMapper;
import com.dong.result.PageResult;
import com.dong.service.JobWantService;
import com.dong.utils.IDUtil;
import com.dong.vo.BidVo;
import com.dong.vo.JobWantVo;
import com.dong.vo.TaskVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-03-03
 */
@Service
public class JobWantServiceImpl extends ServiceImpl<JobWantMapper, JobWant> implements JobWantService {

    @Autowired
    private JobWantMapper jobWantMapper;

    @Override
    public List<JobWantVo> getRecently() {
        // 设置分页查询条件，第一个条件是第几页，因为只查 5 条，所以是 0 ，代表第 0 页，5 是要查询的条数
        PageHelper.startPage(1, 5);

        // 构建查询条件,查询任务状态为 0 的任务，0 代表还未中标的任务
        QueryWrapper<JobWant> jobWantQueryWrapper = new QueryWrapper<>();
        jobWantQueryWrapper.or().eq("job_status", JobStatus.CHECK);
        // 根据创建时间，倒叙查询
        jobWantQueryWrapper.orderByDesc("create_time");
        // 分页查询，查询出前 5 条
        PageInfo<JobWant> taskPageInfo = new PageInfo<>(jobWantMapper.selectList(jobWantQueryWrapper));

        // 获取查询结果
        List<JobWant> jobWants = taskPageInfo.getList();

        // Task 转换为 TaskVo 视图展示类
        List<JobWantVo> jobVos = jobWantsTojobVOs(jobWants);
        return jobVos;
    }

    @Override
    public PageResult<JobWantVo> page(int page) {
        // 使用 PageHelper 进行分页,两个参数，第一个 page 代表查询第几页，pageSize，代表每页查询的条数，默认 10 条
        int pageSize = 10;
        PageHelper.startPage(page, pageSize);

        //查询条件
        QueryWrapper<JobWant> jobWantQueryWrapper = new QueryWrapper<>();
        jobWantQueryWrapper.eq("job_status",JobStatus.CHECK);
        // 分页查询
        PageInfo<JobWant> taskPageInfo = new PageInfo<>(jobWantMapper.selectList(jobWantQueryWrapper));

        // 获取查询结果
        List<JobWant> tasks = taskPageInfo.getList();

        // Task 转换为 TaskVo 视图展示类
        List<JobWantVo> taskVos = jobWantsTojobVOs(tasks);

        // 创建一个分页对象
        PageResult<JobWantVo> pageResult = new PageResult<>();
        // 第几页
        pageResult.setPage(page);
        // 分页数据
        pageResult.setList(taskVos);
        // 数据总条数
        Long total = taskPageInfo.getTotal();
        pageResult.setTotal(total.intValue());
        // 总页数
        int pageTotal = (total.intValue() + pageSize - 1) / pageSize;
        pageResult.setPageTotal(pageTotal);

        return pageResult;
    }

    @Override
    public void postJob(JobWant jobWant) {
        //设置ID
        jobWant.setId(IDUtil.getRandomId());
        // 设置创建时间
        jobWant.setCreateTime(new Date());
        // 设置任务状态
        jobWant.setJobStatus(JobStatus.UNCHECK);
        // 插入到数据库
        jobWantMapper.insert(jobWant);
    }

    @Override
    public List<JobWantVo> getUnCheckAll() {
        QueryWrapper<JobWant> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("job_status", JobStatus.UNCHECK);
        List<JobWant> jobs = jobWantMapper.selectList(taskQueryWrapper);
        List<JobWantVo> taskVos = jobWantsTojobVOs(jobs);
        return taskVos;
    }

    @Override
    public void checkSuccess(Long taskId) {
        JobWant jobWant = jobWantMapper.selectById(taskId);
        jobWant.setJobStatus(JobStatus.CHECK);
        jobWantMapper.updateById(jobWant);
    }

    @Override
    public void unCheckSuccess(Long taskId) {
        JobWant jobWant = jobWantMapper.selectById(taskId);
        jobWant.setJobStatus(JobStatus.CHECK_FAIL);
        jobWantMapper.updateById(jobWant);
    }

    private List<JobWantVo> jobWantsTojobVOs(List<JobWant> jobWants) {
        List<JobWantVo> jobVos = new ArrayList<>();
        for(JobWant jobWant : jobWants)
        {
            JobWantVo jobWantVo = new JobWantVo();
            BeanUtils.copyProperties(jobWant, jobWantVo);
            jobVos.add(jobWantVo);
        }
        return jobVos;
    }

}
