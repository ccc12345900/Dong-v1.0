package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.consts.JobStatus;
import com.dong.domain.Complaints;
import com.dong.domain.JobWant;
import com.dong.mapper.ComplaintsMapper;
import com.dong.service.ComplaintsService;
import com.dong.utils.IDUtil;
import com.dong.vo.ComplaintsVo;
import com.dong.vo.JobWantVo;
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
public class ComplaintsServiceImpl extends ServiceImpl<ComplaintsMapper, Complaints> implements ComplaintsService {

    @Autowired
    private ComplaintsMapper complaintsMapper;

    @Override
    public void postcomplaints(Complaints complaints) {
        //设置ID
        complaints.setId(IDUtil.getRandomId());
        // 设置创建时间
        complaints.setCreateTime(new Date());
        // 插入到数据库
        complaintsMapper.insert(complaints);
    }

    @Override
    public List<ComplaintsVo> getUnCheckAll() {
        QueryWrapper<Complaints> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.orderByDesc("create_time");
        List<Complaints> complaints = complaintsMapper.selectList(taskQueryWrapper);
        List<ComplaintsVo> taskVos = complaintsTocomplaintsVo(complaints);
        return taskVos;
    }

    private List<ComplaintsVo> complaintsTocomplaintsVo(List<Complaints> complaints) {
        ArrayList<ComplaintsVo> complaintsVos = new ArrayList<>();
        for(Complaints com : complaints)
        {
            ComplaintsVo complaintsVo = new ComplaintsVo();
            BeanUtils.copyProperties(com, complaintsVo);
            complaintsVos.add(complaintsVo);
        }
        return complaintsVos;
    }

}
