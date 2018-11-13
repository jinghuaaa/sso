/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util.enums;

import com.bbd.commons.lang.enums.EnumInterface;

/**
 * BDSSO本地缓存状态枚举
 * 
 * @author byron
 * @version $Id: LocalCacheStatusEnum.java, v 0.1 Nov 20, 2017 2:02:32 PM byron Exp $
 */
public enum LocalCacheStatusEnum implements EnumInterface {

    /** 初始状态 */
    INITIAL("INITIAL", "初始状态"),

    /** 待刷新状态 */
    WAIT_FOR_REFRESH("WAIT_FOR_REFRESH", "等待刷新"),

    /** 刷新完成 */
    REFRESH_COMPLETE("REFRESH_COMPLETE", "刷新完毕");

    /** 枚举值 */
    private String code;

    /** 枚举描述 */
    private String desc;

    /**
     * 构造方法
     *
     * @param code
     * @param desc
     */
    private LocalCacheStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过code获取枚举
     * 
     * @param code          代码
     * @return              枚举
     */
    public static LocalCacheStatusEnum getEnumByCode(String code) {
        for (LocalCacheStatusEnum entry : LocalCacheStatusEnum.values()) {
            if (entry.getCode().equals(code)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    /**
     * 获取日志信息
     *
     * @return
     */
    public String getLogMessage() {
        StringBuffer sb = new StringBuffer();
        sb.append(toString());
        sb.append("[");
        sb.append(code);
        sb.append(":");
        sb.append(desc);
        sb.append("]");
        return sb.toString();
    }

}
