package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.consts.BidStatus;
import com.dong.consts.TaskStatus;
import com.dong.domain.Bid;
import com.dong.domain.Employee;
import com.dong.domain.Task;
import com.dong.mapper.BidMapper;
import com.dong.mapper.EmployeeMapper;
import com.dong.mapper.TaskMapper;
import com.dong.service.BidService;
import com.dong.vo.BidVo;
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
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements BidService {

    @Autowired
    private BidMapper bidMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<BidVo> getNoBitByEmployeeId(Long id) {
        QueryWrapper<Bid> bidQueryWrapper = new QueryWrapper<>();
        bidQueryWrapper.eq("employee_id", id).eq("bid_status", BidStatus.NO_BIT);

        List<Bid> bids = bidMapper.selectList(bidQueryWrapper);
        List<BidVo> bidVos = new ArrayList<>();
        for (Bid bid : bids) {
            BidVo bidVo = new BidVo();
            // 相同属性进行复制
            BeanUtils.copyProperties(bid, bidVo);
            // 根据投标雇员ID查询出雇员信息
            Employee currEmployee = employeeMapper.selectById(bid.getEmployeeId());
            bidVo.setEmployee(currEmployee);
            // 根据任务 ID 查询任务信息
            Task task = taskMapper.selectById(bid.getTaskId());
            bidVo.setTask(task);

            // 复制完值添加到集合中
            bidVos.add(bidVo);
        }
        return bidVos;
    }

    @Override
    public boolean getBidByTaskIdAndEmployeeId(Long taskId, Long id) {
        QueryWrapper<Bid> bidQueryWrapper = new QueryWrapper<>();
        bidQueryWrapper.eq("task_id", taskId).eq("employee_id", id);
        Bid bid = bidMapper.selectOne(bidQueryWrapper);

        // 如果 bid 不为 null 代表已经投递过该任务
        if (bid != null) {
            return true;
        }

        return false;
    }

    @Override
    public void bid(Bid bid) {
        bidMapper.insert(bid);
    }

    @Override
    public void acceptBid(Long taskId, Long employeeId) {
// 先查询任务信息
        Task task = taskMapper.selectById(taskId);
        // 设置中标雇员信息
        task.setEmployeeId(employeeId);
        // 设置任务状态
        task.setTaskStatus(TaskStatus.BIT);
        // 设置中标时间
        task.setBidTime(new Date());
        // 查询投标信息，获取投标价格
        QueryWrapper<Bid> bidQueryWrapper = new QueryWrapper<>();
        bidQueryWrapper.eq("task_id", taskId).eq("employee_id",employeeId);
        Bid bid = bidMapper.selectOne(bidQueryWrapper);
        // 设置中标价格
        task.setTaskPrice(bid.getBidPrice());
        // 更新到数据库
        taskMapper.updateById(task);

        // 修改竞标状态
        QueryWrapper<Bid> bidQueryWrapper1 = new QueryWrapper<>();
        bidQueryWrapper1.eq("task_id", taskId).eq("employee_id", employeeId);
        Bid bid1 = bidMapper.selectOne(bidQueryWrapper1);
        bid1.setBidStatus(BidStatus.BIT);
        bidMapper.updateById(bid1);
    }
}
