package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class Mob {
	public static Image[] imgs = new Image[100];
	static {
		for(int i=0;i<7;i++) {
			imgs[i] = ImageUtil.getImage("mob_left_stand"+i);
		}
	}
	public MapleStoryClient msc;
	public int x;
	public int y;
	public Direction dir;
	public int width;
	public int height;
	public boolean live =true;
	public int HP = Constant.MOB_HP;
	public BloodBar bb;
	public Mob(MapleStoryClient msc,int x) {
		this.msc = msc;
		this.width = imgs[0].getWidth(null);
		this.height = imgs[0].getWidth(null);
		this.x=x;//mob  x	
		this.y=Constant.GAME_HEIGHT-height-210;		
		this.dir = Direction.LEFT;
		this.bb = new BloodBar(this.x, this.y);
	}
	public int count = 0;
	public void draw(Graphics g) {
		if(!live) {
			msc.mobs.remove(this);
		}
		if(count>6) {
			count = 0;
		}
		g.drawImage(imgs[count++],x,y,null);	
		bb.draw(g);
	}
	public Rectangle getRectangle() {
		return new Rectangle(x,y,width,height);		
	}
	/*
	 * 
	 * 画血条
	 */
	class BloodBar{//内部类调用外部类的属性
		int x;
		int y;
		public BloodBar(int x,int y) {
			this.x = x;
			this.y = y;		
		}
		public void draw(Graphics g) {
			//换颜色
			Color c = g.getColor();
			g.setColor(Color.darkGray);//暗灰
			//画框
			g.drawRect(x, y, width, 5);
			//画填充
			g.setColor(Color.GREEN);
			g.fillRect(x, y, width*HP/100, 5);
			g.setColor(c);		
		}
	}
}
