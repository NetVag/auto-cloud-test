package org.zzf.enums;

/**
 * 执行状态枚举
 *
 * @author 詹泽峰
 * @date 2025/12/31 16:05
 */
public enum ReportStateEnum {

    /**
     * 执行中
     */
    EXECUTING,

    /**
     * 统计报告
     */
    COUNTING_REPORT,

    /**
     * 执行成功
     */
    EXECUTE_SUCCESS,

    /**
     * 执行失败
     */
    EXECUTE_FAIL;
}
