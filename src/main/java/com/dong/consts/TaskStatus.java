package com.dong.consts;

/**
 * 任务状态常量
 *
 * @author by yuu
 * @Classname TaskStatus
 * @Date 2019/10/13 18:16
 */
public class TaskStatus {

    /**
     * 审核失败
     */
    public static final Integer CHECK_FAIL = -2;

    /**
     * 待审核
     */
    public static final Integer UNCHECK = -1;

    /**
     * 未中标
     */
    public static final Integer NO_BIT = 0;

    /**
     * 已中标
     */
    public static final Integer BIT = 1;

    /**
     * 雇员已经提交任务
     */
    public static final Integer SUBMIT = 2;

    /**
     * 任务交易成功
     */
    public static final Integer COMPLETE = 3;

}
