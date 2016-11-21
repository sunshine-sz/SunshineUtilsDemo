package com.sunshine.rxjavademo.view;

import android.animation.TypeEvaluator;

/**
 * 作者: Sunshine
 * 时间: 2016/10/26.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class ColorEvaluator implements TypeEvaluator {
    private int currentRed = -1;
    private int currentBlue = -1;
    private int currentGreen = -1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //获取开始颜色、结束颜色的值
        String startColor = (String) startValue;
        String endColor = (String) endValue;
        //获取RGB的值
        int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);

        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);

        //初始化各个颜色的值
        if (currentRed == -1) {
            currentRed = startRed;
        }
        if (currentGreen == -1) {
            currentGreen = startGreen;
        }
        if (currentBlue == -1) {
            currentBlue = startBlue;
        }
        //计算颜色差
        int diffRed = Math.abs(startRed - endRed);
        int diffGreen = Math.abs(startGreen - endGreen);
        int diffBlue = Math.abs(startBlue - endBlue);
        int diffColor = diffRed + diffGreen + diffBlue;
        //计算当前颜色值
        if (currentRed!=endRed){
            currentRed = Math.abs(getCurrentColor(startRed, endRed, diffColor, 0, fraction));
        }else if (currentGreen!=endGreen){
            currentGreen = Math.abs(getCurrentColor(startGreen,endGreen,diffColor,diffRed,fraction));
        }else if (currentBlue!=endBlue){
            currentBlue = Math.abs(getCurrentColor(startBlue,endBlue,diffColor,diffRed+diffGreen,fraction));
        }

        //将当前的值合并成string颜色
        String currentColor = "#"+getHexString(currentRed)+getHexString(currentGreen)+getHexString(currentBlue);
        return currentColor;
    }

    private int getCurrentColor(int startColor, int endColor, int diffColor, int i, float fraction) {
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (fraction * endColor - i));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startColor - (fraction * endColor - i));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }


    private String getHexString(int color){
        String toHexString = Integer.toHexString(color);
        if (toHexString.length()==1){
            toHexString = "0"+toHexString;
        }
        return toHexString;
    }
}
