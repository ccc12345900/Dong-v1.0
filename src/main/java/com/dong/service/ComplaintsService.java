package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.domain.Complaints;
import com.dong.vo.ComplaintsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-03-03
 */
public interface ComplaintsService extends IService<Complaints> {

    void postcomplaints(Complaints complaints);

    List<ComplaintsVo> getUnCheckAll();
}
