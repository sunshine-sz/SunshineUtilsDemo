package com.sunshine.rxjavademo.api;

/**
 * 作者: Sunshine
 * 时间: 2016/10/27.
 * 邮箱: 44493547@qq.com
 * 描述: 闪光灯类型枚举 默认为关闭
 */

public enum FlashMode {
        /**
         * ON:拍照时打开闪光灯
         */
        ON,
        /**
         * OFF：不打开闪光灯
         */
        OFF,
        /**
         * AUTO：系统决定是否打开闪光灯
         */
        AUTO,
        /**
         * TORCH：一直打开闪光灯
         */
        TORCH
}
