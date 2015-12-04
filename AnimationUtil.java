package com.dsw.utils;

import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

/**
 * 常用的动画工具类
 * @author Administrator
 *
 */
public class AnimationUtil {
	private AnimationUtil(){};
	
	/**
	 * 创建一个平移动画
	 * @param fromXDelta	X轴起始位置
	 * @param toXDelta		X移动的距离
	 * @param fromYDelta	Y轴起始位置
	 * @param toYDelta		Y轴移动的距离
	 * @param duration		动画持续时间
	 * @param repeatCount	动画持续次数，-1为无限循环
	 * @param repeatMode	动画重复的模式，1：从新开始	2：倒叙开始
	 * @param animationListener		动画监听，可为null
	 * @return
	 */
	public static TranslateAnimation createTranslate(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
								int duration, int repeatCount, int repeatMode, AnimationListener animationListener){
		TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
		translateAnimation.setDuration(duration);
		translateAnimation.setRepeatCount(repeatCount);
		translateAnimation.setRepeatMode(repeatMode);
		if(animationListener != null)
			translateAnimation.setAnimationListener(animationListener);
		return translateAnimation;
	}
}
