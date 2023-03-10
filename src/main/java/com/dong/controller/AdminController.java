package com.dong.controller;

import com.dong.domain.Admin;
import com.dong.domain.Employee;
import com.dong.service.*;
import com.dong.vo.ComplaintsVo;
import com.dong.vo.EmployeeVo;
import com.dong.vo.JobWantVo;
import com.dong.vo.TaskVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员控制器
 *
 * @author by yuu
 * @Classname AdminController
 * @Date 2019/10/13 16:26
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    /**
     * Spring 容器自动注入 AdminService
     */
    @Resource
    private AdminService adminService;

    @Resource
    private EmployerService employerService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private TaskService taskService;

    @Resource
    private JobWantService jobWantService;

    @Resource
    private ComplaintsService complaintsService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "admin/login";
    }

    /**
     * 管理员登录
     *
     * @param admin 管理员，在参数上使用实体类接收，可以自动接受 username,password 属性
     * @return
     */
    @PostMapping("login")
    public String login(Admin admin, RedirectAttributes redirectAttributes, HttpSession session) {
        // 调用 AdminService 处理登录的业务逻辑，返回一个 Admin 对象，如果为空登录失败，如果不为空登录成功
        Admin currAdmin = adminService.login(admin);

        // 如果为空，登录失败，返回一个消息给登录页面提示, RedirectAttributes 是重定向用于把消息存放在域对象中，供页面获取
        if (currAdmin == null) {
            // 将消息放入重定向的与对象中
            redirectAttributes.addFlashAttribute("msg", "用户名或密码错误");
            // 重定向到登录页
            return "redirect:/admin/login";
        }

        // 如果 Admin 不为空，登录成功
        else {
            // 把管理员信息放入 session 中，保持管理员登录状态
            session.setAttribute("admin", admin);
            // 重定向到管理员首页
            return "redirect:/admin/index";
        }
    }

    @GetMapping("settings/password")
    public String SettingPassword()
    {
        return "admin/settings_pass";
    }

    @PostMapping("settings/password")
    public String updatePass(String password, String newPassword, HttpSession session, RedirectAttributes redirectAttributes) {
        // 查询雇员登录信息
        Admin admin = (Admin) session.getAttribute("admin");
        String msg = adminService.updatePass(admin.getUsername(), password, newPassword);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/settings/password";
    }

    /**
     * 跳转到管理员首页
     *
     * @param model 用户将数据存放在域对象中的对象
     * @return
     */
    @GetMapping("index")
    public String index(Model model) {

        // 总雇主数量
        Integer employerCount = employerService.getAllCount();

        // 总雇员数量
        Integer employeeCount = employeeService.getAllCount();

        // 总任务成交数量
        Integer allTaskCompleteCount = taskService.getAllCompleteCount();

        // 总任务成交金额
        Double allTaskCompletePrice = taskService.getAllCompletePrice();

        // 最近 10 个注册雇员
        List<Employee> employees = employeeService.getRecently();

        // 获取最近成交的 10 个任务
        List<TaskVo> taskVos = taskService.getRecentlyComplete();

        // 将查询出来的数据，放置域对象中，供页面展示
        model.addAttribute("employerCount", employerCount);
        model.addAttribute("employeeCount", employeeCount);
        model.addAttribute("allTaskCompleteCount", allTaskCompleteCount);
        model.addAttribute("allTaskCompletePrice", allTaskCompletePrice);
        model.addAttribute("employees", employees);
        model.addAttribute("taskVos", taskVos);

        // 跳转到管理员首页
        return "admin/index";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        // 退出登录，只需要删除存放在 session 中的 admin 信息即可
        session.removeAttribute("admin");
        // 重定向到登录页
        return "redirect:/admin/login";
    }

    @GetMapping("jobWant/check")
    public String taskCheck(Model model) {
        // 查询所有待审核任务
        List<JobWantVo> jobWantVos = jobWantService.getUnCheckAll();

        // 设置到域对象中，提供给页面展示
        model.addAttribute("jobs", jobWantVos);
        return "admin/job";
    }

    @GetMapping("jobWant/checkSuccess")
    public String checkSuccess(Long taskId) {
        // 通过审核
        jobWantService.checkSuccess(taskId);
        return "redirect:/admin/jobWant/check";
    }

    @GetMapping("jobWant/unCheckSuccess")
    public String unCheckSuccess(Long taskId) {
        // 审核失败
        jobWantService.unCheckSuccess(taskId);
        return "redirect:/admin/jobWant/check";
    }

    @GetMapping("complaints/check")
    public String complaintsCheck(Model model) {
        // 查询所有待审核任务
        List<ComplaintsVo> jobWantVos = complaintsService.getUnCheckAll();

        // 设置到域对象中，提供给页面展示
        model.addAttribute("jobs", jobWantVos);
        return "admin/complaints";
    }

    @GetMapping("complaints/checkSuccess")
    public String complaintsCheckSuccess(Long taskId) {
        // 通过审核
        complaintsService.removeById(taskId);
        return "redirect:/admin/complaints/check";
    }

}
