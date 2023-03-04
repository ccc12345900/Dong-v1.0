package com.dong.controller;


import com.dong.domain.Complaints;
import com.dong.domain.Employee;
import com.dong.domain.Employer;
import com.dong.domain.JobWant;
import com.dong.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-03-03
 */
@Controller
@RequestMapping("/complaints")
public class ComplaintsController {

    @Autowired
    private ComplaintsService complaintsService;

    @GetMapping("/post")
    public String postTask(Model model,HttpSession session) {
        if(session.getAttribute("employee") != null)
            return "employee/post_complaints";
        else if(session.getAttribute("employer") != null)
            return "employer/post_complaints";

        return "login";
    }

    @PostMapping("/post")
    public String postTask(HttpSession session, Complaints complaints, RedirectAttributes redirectAttributes) {

        if(session.getAttribute("employee") != null) {
            // 获取雇主信息
            Employee employee = (Employee) session.getAttribute("employee");

            // 添加任务
            complaints.setComplainant(employee.getId());
            complaintsService.postcomplaints(complaints);

            // 提示消息
            redirectAttributes.addFlashAttribute("msg", "投诉信息成功，感谢您提出宝贵的意见！");
            return "redirect:/complaints/post";
        }else if(session.getAttribute("employer") != null){
            // 获取雇主信息
            Employer employer = (Employer) session.getAttribute("employer");

            // 添加任务
            complaints.setComplainant(employer.getId());
            complaintsService.postcomplaints(complaints);

            // 提示消息
            redirectAttributes.addFlashAttribute("msg", "投诉信息成功，感谢您提出宝贵的意见！");
            return "redirect:/complaints/post";
        }
        return "login";
    }
}

