package com.changhong.sei.help.dto;


import com.changhong.sei.annotation.Remark;

/**
 * 实现功能：
 *
 * @author 马超(Vision.Mac)
 * @version 1.0.00  2019-10-31 17:02
 */
public enum CategoryType {

    /**
     * 主题
     */
    @Remark("主题分类")
    tab,
    /**
     * 业务
     */
    @Remark("业务分类")
    biz,
    /**
     * 统计
     */
    @Remark("统计分类")
    statis
}
