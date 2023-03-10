package com.dong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.domain.*;
import com.dong.service.*;
import com.dong.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 雇员控制器
 *
 * @author by yuu
 * @Classname EmployeeController
 * @Date 2019/10/15 0:36
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HomeBowerService homeBowerService;

    @Resource
    private EmployeeBookmarkedService employeeBookmarkedService;

    @Resource
    private BidService bidService;

    @Resource
    private JobWantService jobWantService;

    /**
     * 雇员退出登录
     *
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("employee");
        return "redirect:/index";
    }


    /**
     * 跳转到个人中心
     *
     * @return
     */
    @GetMapping("dashboard")
    public String dashboard(HttpSession session, Model model) {

        // 获取雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 查询雇员任务投标总中标数
        Integer bidCount = taskService.getByBidEmployeeId(employee.getId());

        // 主页总浏览次数
        Integer bowerCount = employeeService.getBowerCount(employee.getId());

        // 最新主页浏览情况
        List<HomeBowerVo> homeBowerVos = homeBowerService.getByRecentlyEmployeeId(employee.getId());

        // 放置域对象中，供页面展示
        model.addAttribute("bidCount", bidCount);
        model.addAttribute("bowerCount", bowerCount);
        model.addAttribute("homeBowers", homeBowerVos);

        return "employee/dashboard";
    }

    /**
     * 跳转到个人信息设置页面
     *
     * @return
     */
    @GetMapping("settings/base")
    public String settings(HttpSession session, Model model) {

        // 获取 session 中的雇员信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 获取视图展示对象，主要是为了展示技能信息，因为 Employee 中只有 技能 ID 没有技能名称
        EmployeeVo employeeVo = employeeService.getById(employee.getId());

        model.addAttribute("employee", employeeVo);
        return "employee/settings_base";
    }

    /**
     * 根据id修改用户个人信息
     */
    @GetMapping("settings/byid")
    public String settingsById(Long id,HttpSession session, Model model) {

        // 获取 session 中的雇员信息
        EmployeeVo employee = employeeService.getById(id);
        session.setAttribute("employee",employee);
        // 获取视图展示对象，主要是为了展示技能信息，因为 Employee 中只有 技能 ID 没有技能名称
        EmployeeVo employeeVo = employeeService.getById(employee.getId());

        model.addAttribute("employee", employeeVo);
        return "employee/settings_base2";
    }

    /***
     * 评论雇员
     */
    @GetMapping("comment/employee")
    public String commentemployee(Long employeeId,HttpSession session, Model model)
    {
        // 获取 session 中的雇员信息
        EmployeeVo employee = employeeService.getById(employeeId);
        session.setAttribute("employee",employee);
        // 获取视图展示对象，主要是为了展示技能信息，因为 Employee 中只有 技能 ID 没有技能名称
        EmployeeVo employeeVo = employeeService.getById(employee.getId());

        model.addAttribute("employee", employeeVo);
        return "employee/settings_base3";
    }

    /**
     * 保存个人基本信息
     *
     * @param employee
     * @return
     */
    @PostMapping("settings/base/save")
    public String saveBase(Employee employee, HttpSession session) {
        // 更新个人信息到数据库
        Employee currEmployee = employeeService.saveEmployee(employee);

        // 更新 session 中的个人信息
        session.setAttribute("employee", currEmployee);
        return "redirect:/employee/settings/base";
    }

    @PostMapping("settings/comment/save")
    public String saveCommentBase(Employee employee, HttpSession session) {
        // 更新个人信息到数据库
        Employee currEmployee = employeeService.saveEmployeeComment(employee);

        return "redirect:/employer/myTasks";
    }


    /**
     * 跳转到修改密码页
     *
     * @return
     */
    @GetMapping("settings/password")
    public String updatePass() {
        return "employee/settings_pass";
    }

    /**
     * 修改密码
     *
     * @param password 原来的密码
     * @param newPassword 新密码
     * @return
     */
    @PostMapping("settings/password")
    public String updatePass(String password, String newPassword, HttpSession session, RedirectAttributes redirectAttributes) {
        // 查询雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");
        String msg = employeeService.updatePass(employee.getId(), password, newPassword);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/employee/settings/password";
    }

    /**
     * 跳转到雇员简介页面
     *
     * @return
     */
    @GetMapping("profile")
    public String profile(Long employeeId, Model model, HttpSession session) {
        // 查询雇员信息
        EmployeeVo employee = employeeService.getById(employeeId);

        // 查询历史完成任务
        List<TaskVo> taskVos = taskService.getByEmployeeId(employeeId);

        // 查询雇员总完成任务数
        Integer completeCount = taskService.getCompletedByEmployeeId(employeeId).size();

        // 如果雇主登录了，主页访问次数加 1
        Employer employer = (Employer) session.getAttribute("employer");
        if (employer != null) {
            employeeService.bower(employeeId, employer.getId());
        }

        // 主页总浏览次数
        Integer bowerCount = employeeService.getBowerCount(employee.getId());
        model.addAttribute("employee", employee);
        model.addAttribute("tasks", taskVos);
        model.addAttribute("completeCount", completeCount);
        model.addAttribute("bowerCount", bowerCount);
        return "employee_profile";
    }

    /**
     * 添加技能
     *
     * @param skillName 技能名称
     * @return
     */
    @PostMapping("skill/add")
    @ResponseBody
    public String addSkill(String skillName, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (!"".equals(skillName)) {
            employeeService.addSkill(employee.getId(), skillName);
        }
        return "添加技能";
    }

    /**
     * 删除技能
     *
     * @param skillId
     * @return
     */
    @PostMapping("skill/delete")
    @ResponseBody
    public String deleteSkill(Long skillId) {
        employeeService.deleteSkill(skillId);
        return "删除技能";
    }

    /**
     * 收藏或取消收藏任务
     *
     * @param taskId
     * @return
     */
    @PostMapping("bookmarked")
    @ResponseBody
    public EmployeeBookmarked bookmarked(Long taskId, HttpSession session) {
        // 获取雇员的登录信息
        Employee employee = (Employee) session.getAttribute("employee");
        // 收藏或取消收藏任务
        EmployeeBookmarked employeeBookmarked = employeeBookmarkedService.bookmarked(employee.getId(), taskId);
        return employeeBookmarked;
    }

    /**
     * 跳转到我的收藏页面
     *
     * @return
     */
    @GetMapping("bookmarks")
    public String bookmarks(HttpSession session, Model model) {
        // 获取雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 获取收藏的任务集合
        List<EmployeeBookmarkedVo> bookMarks = employeeBookmarkedService.getByEmployeeId(employee.getId());

        // 放置到域对象中，方便页面展示
        model.addAttribute("bookMarks", bookMarks);

        return "employee/bookmarks";
    }

    /**
     * 删除收藏任务
     *
     * @param taskId 任务 ID
     * @return
     */
    @PostMapping("bookmarks/remove")
    @ResponseBody
    public String bookmarks(Long taskId, HttpSession session) {
        // 获取雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 删除收藏信息
        employeeBookmarkedService.remove(employee.getId(), taskId);

        return "删除收藏信息";
    }

    /**
     * 查询已完成任务
     *
     * @param session
     * @return
     */
    @GetMapping("/task/completed")
    public String completedTask(HttpSession session, Model model) {
        // 获取雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 查询出已完成的订单
        List<TaskVo> taskVos = taskService.getCompletedByEmployeeId(employee.getId());

        // 放置到域对象中
        model.addAttribute("tasks", taskVos);
        return "employee/completed_task";
    }

    /**
     * 跳转到待完成任务
     *
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/task/uncompleted")
    public String unCompletedTask(HttpSession session, Model model) {
        // 获取雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 查询出未完成的订单
        List<TaskVo> taskVos = taskService.getUnCompletedByEmployeeId(employee.getId());

        // 放置到与对象中
        model.addAttribute("tasks", taskVos);

        // 跳转到待完成任务列表页
        return "employee/uncompleted_task";
    }

    /**
     * 雇员提交任务
     *
     * @param session
     * @return
     */
    @PostMapping("/task/submit")
    @ResponseBody
    public String submitTask(Long taskId, HttpSession session) {
        // 获取雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 雇员提交信息
        taskService.submitTask(employee.getId(), taskId);

        return "任务提交成功 ，等待雇主确认！";
    }

    /**
     * 跳转到我的竞标页面
     *
     * @param session
     * @return
     */
    @GetMapping("mybids")
    public String myBid(HttpSession session, Model model) {
        // 获取雇员登录信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 查询所有未中标的信息
        List<BidVo> bidVos = bidService.getNoBitByEmployeeId(employee.getId());

        // 放置域对象中，供页面展示
        model.addAttribute("bids", bidVos);

        return "employee/my_bids";
    }

    /**
     * 删除竞标信息
     *
     * @param bid
     * @return
     */
    @GetMapping("bid/delete")
    public String deleteBid(Long bid) {
        bidService.removeById(bid);
        return "redirect:/employee/mybids";
    }

    @GetMapping("jobWant/post")
    public String postTask(Model model) {
        return "employee/post_job";
    }

    /**
     * 雇主发布一个任务
     *
     * @param session
     * @return
     */
    @PostMapping("jobWant/post")
    public String postTask(HttpSession session, JobWant jobWant, RedirectAttributes redirectAttributes) {
        // 获取雇主信息
        Employee employee = (Employee) session.getAttribute("employee");

        // 添加任务
        jobWant.setEmployeeId(employee.getId());
        jobWantService.postJob(jobWant);

        // 提示消息
        redirectAttributes.addFlashAttribute("msg", "求职信息成功，等待管理员审核！");
        return "redirect:/employee/jobWant/post";
    }


    @GetMapping("/deletebyid")
    public String deleteById(Long id,Model model)
    {
        EmployeeVo byId = employeeService.getById(id);
        employeeService.removeById(byId);
        int num = bidService.removeByEmployeeId(byId.getId());
        if(num != 0)
        {
            QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
            taskQueryWrapper.eq("employee_id",id);
            List<Task> list = taskService.list(taskQueryWrapper);
            for(Task t:list)
            {
                if(t.getTaskStatus()!=3)
                {
                    t.setTaskStatus(0);
                    t.setEmployeeId(null);
                    t.setBidTime(null);
                    t.setTaskPrice(null);
                    t.setCloseTime(null);
                    taskService.updateTask(t);
                }
            }
        }
        // 查询所有分类
        List<Employee> employees = employeeService.getAll();

        // 设置到域对象中，提供给页面展示
        model.addAttribute("employees", employees);
        return "admin/employee";
    }
}
