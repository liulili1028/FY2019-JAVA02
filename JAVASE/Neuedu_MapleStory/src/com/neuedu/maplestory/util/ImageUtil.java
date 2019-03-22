package com.neuedu.maplestory.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
/*
 *存放项目中所有的图片
 */
public class ImageUtil {
	/*
	 * 用于存放项目中所有的图片的map结构（哈希散列算法存放，效率高）
	 */
	public static Map<String,Image> imgs = new HashMap<>();
	/*
	 * 存放的过程
	 */
	static {
		//游戏背景图
		imgs.put("bg",GameUtil.getImage("com/neuedu/maplestory/img/bgimg.png"));
		//英雄右站立
		imgs.put("hero_right_stand", GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_0.png"));
		//英雄左站立
		imgs.put("hero_left_stand", GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_l/stand1_0.png"));
		//英雄right walk
		for(int i=0;i<5;i++) {
			imgs.put("hero_right_walk0"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_"+i+".png"));
		}
		//英雄left walk
		for(int i=0;i<5;i++) {
			imgs.put("hero_left_walk0"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_"+i+".png"));
		}
		//英雄left jump
		imgs.put("hero_left_jump", GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_l.png"));
		//英雄right jump
		imgs.put("hero_right_jump", GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_r.png"));
		//英雄left bow
		for(int i=0;i<4;i++) {
		imgs.put("hero_left_bow"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot1_"+i+".png"));
		}
		//英雄right bow
		for(int i=0;i<4;i++) {
				imgs.put("hero_right_bow"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot1_"+i+".png"));
				}
		//左边的弓箭 left bullet
		imgs.put("hero_left_bullet", GameUtil.getImage("com/neuedu/maplestory/img/bullet/bullet_l.png"));
		//右边的弓箭 right bullet
		imgs.put("hero_right_bullet", GameUtil.getImage("com/neuedu/maplestory/img/bullet/bullet_r.png"));
		//怪物left stand
				for(int i=0;i<7;i++) {
						imgs.put("mob_left_stand"+i, GameUtil.getImage("com/neuedu/maplestory/img/mob/stand_l/"+i+".png"));
						}
		//怪物left die
			 for(int i=0;i<14;i++) {
						imgs.put("mob_left_die"+i, GameUtil.getImage("com/neuedu/maplestory/img/mob/die_l/"+i+".png"));
						}
	}
	
	public static Image getImage(String key) {
		return imgs.get(key);	
	}
}
