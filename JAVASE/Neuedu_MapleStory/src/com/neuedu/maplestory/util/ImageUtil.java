package com.neuedu.maplestory.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
/*
 *�����Ŀ�����е�ͼƬ
 */
public class ImageUtil {
	/*
	 * ���ڴ����Ŀ�����е�ͼƬ��map�ṹ����ϣɢ���㷨��ţ�Ч�ʸߣ�
	 */
	public static Map<String,Image> imgs = new HashMap<>();
	/*
	 * ��ŵĹ���
	 */
	static {
		//��Ϸ����ͼ
		imgs.put("bg",GameUtil.getImage("com/neuedu/maplestory/img/bgimg.png"));
		//Ӣ����վ��
		imgs.put("hero_right_stand", GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_0.png"));
		//Ӣ����վ��
		imgs.put("hero_left_stand", GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_l/stand1_0.png"));
		//Ӣ��right walk
		for(int i=0;i<5;i++) {
			imgs.put("hero_right_walk0"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_"+i+".png"));
		}
		//Ӣ��left walk
		for(int i=0;i<5;i++) {
			imgs.put("hero_left_walk0"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_"+i+".png"));
		}
		//Ӣ��left jump
		imgs.put("hero_left_jump", GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_l.png"));
		//Ӣ��right jump
		imgs.put("hero_right_jump", GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_r.png"));
		//Ӣ��left bow
		for(int i=0;i<4;i++) {
		imgs.put("hero_left_bow"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot1_"+i+".png"));
		}
		//Ӣ��right bow
		for(int i=0;i<4;i++) {
				imgs.put("hero_right_bow"+i, GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot1_"+i+".png"));
				}
		//��ߵĹ��� left bullet
		imgs.put("hero_left_bullet", GameUtil.getImage("com/neuedu/maplestory/img/bullet/bullet_l.png"));
		//�ұߵĹ��� right bullet
		imgs.put("hero_right_bullet", GameUtil.getImage("com/neuedu/maplestory/img/bullet/bullet_r.png"));
		//����left stand
				for(int i=0;i<7;i++) {
						imgs.put("mob_left_stand"+i, GameUtil.getImage("com/neuedu/maplestory/img/mob/stand_l/"+i+".png"));
						}
		//����left die
			 for(int i=0;i<14;i++) {
						imgs.put("mob_left_die"+i, GameUtil.getImage("com/neuedu/maplestory/img/mob/die_l/"+i+".png"));
						}
	}
	
	public static Image getImage(String key) {
		return imgs.get(key);	
	}
}
