package com.dong.controller;


import com.dong.domain.Employee;
import com.dong.domain.JobWant;
import com.dong.mapper.JobWantMapper;
import com.dong.result.PageResult;
import com.dong.service.EmployeeService;
import com.dong.service.JobWantService;
import com.dong.vo.EmployeeVo;
import com.dong.vo.JobWantVo;
import com.dong.vo.TaskCategoryVo;
import com.dong.vo.TaskVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-03-03
 */
@Controller
@RequestMapping("/jobWant")
public class JobWantController {
    @Autowired
    private JobWantService jobWantService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       HttpSession session,
                       Model model) {

        // 调用 TaskService 进行分页查询，得到分页结果
        PageResult<JobWantVo> tasksPage = jobWantService.page(page);

        // 将查询结果放置到域对象中，供页面查询
        model.addAttribute("tasksPage", tasksPage);
        return "job_list";
    }

    @GetMapping("page")
    public String page(@RequestParam(required = true) Long taskId, Model model) {
        // 根据任务 ID 查询出任务详情
        JobWant byId = jobWantService.getById(taskId);
        JobWantVo jobWantVo = new JobWantVo();
        BeanUtils.copyProperties(byId, jobWantVo);
        // 放入域对象中，提供给页面查询
        model.addAttribute("task", jobWantVo);
        EmployeeVo byId1 = employeeService.getById(byId.getEmployeeId());
        model.addAttribute("employer",byId1);
        return "job_page";
    }

}

