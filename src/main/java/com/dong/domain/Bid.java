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
 * @since 2023-03-03
 */
@EqualsAndHashCode(callSuper = false)
public class Bid implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 投标ID
     */
      private Long id;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 职业者ID
     */
    private Long employeeId;

    /**
     * 投标价格
     */
    private Double bidPrice;

    /**
     * 交货时间描述，例如 1 天
     */
    private String deliveryDesc;

    /**
     * 交货时间
     */
    private Date deliveryTime;

    /**
     * 竞标状态
     */
    private Integer bidStatus;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
