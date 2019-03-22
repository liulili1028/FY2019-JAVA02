package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class BackGround {
	public Image img;
	public int x;
	public int y;
	public int height;
	
	public BackGround() {
		this.img = ImageUtil.getImage("bg");
		this.x = 0;
		this.y =0 ;
		this.height = img.getHeight(null);
	}
	
	public void draw(Graphics g) {
	   g.drawImage(img, x, Constant.GAME_HEIGHT-this.height, null);
	}

}
