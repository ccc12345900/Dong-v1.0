package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.Bid;
import com.dong.vo.BidVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-03-03
 */
public interface BidService extends IService<Bid> {

    /**
     * 获取所有未中标的信息
     *
     * @param id 雇员 ID
     * @return
     */
    List<BidVo> getNoBitByEmployeeId(Long id);
    /**
     * 判断雇员是否已经投标过该任务
     *
     * @param taskId 任务 ID
     * @param id     雇员ID
     * @return
     */
    boolean getBidByTaskIdAndEmployeeId(Long taskId, Long id);

    /**
     * 雇员投标
     *
     * @param bid
     */
    void bid(Bid bid);

    /**
     * 接受投标
     *
     * @param taskId 任务 ID
     * @param employeeId 雇员 ID
     */
    void acceptBid(Long taskId, Long employeeId);
}
