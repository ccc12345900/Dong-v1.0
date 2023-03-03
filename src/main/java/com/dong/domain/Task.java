package com.dong.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2023-03-02
 */
@EqualsAndHashCode(callSuper = false)
public class Task implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 任务ID
     */
      private Long id;

    /**
     * 任务分类ID
     */
    private Long categoryId;

    /**
     * 任务发布雇主ID
     */
    private Long employerId;

    /**
     * 任务标题
     */
    private String taskTitle;

    /**
     * 任务简介
     */
    private String taskProfile;

    /**
     * 任务描述
     */
    private String taskDesc;

    /**
     * 最低预算价格
     */
    private Double feesLow;

    /**
     * 最高预算价格
     */
    private Double feesHigh;

    /**
     * 任务附件地址
     */
    private String feesFile;

    /**
     * 附件文件名称
     */
    private String filename;

    /**
     * 完成任务雇员ID
     */
    private Long employeeId;

    /**
     * 任务成交价格
     */
    private Double taskPrice;

    /**
     * 任务状态
     */
    private Integer taskStatus;

    /**
     * 成交时间
     */
    private Date closeTime;

    /**
     * 中标时间
     */
    private Date bidTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskProfile() {
        return taskProfile;
    }

    public void setTaskProfile(String taskProfile) {
        this.taskProfile = taskProfile;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Double getFeesLow() {
        return feesLow;
    }

    public void setFeesLow(Double feesLow) {
        this.feesLow = feesLow;
    }

    public Double getFeesHigh() {
        return feesHigh;
    }

    public void setFeesHigh(Double feesHigh) {
        this.feesHigh = feesHigh;
    }

    public String getFeesFile() {
        return feesFile;
    }

    public void setFeesFile(String feesFile) {
        this.feesFile = feesFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
