package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.ImageUtil;

public class Bullet {
	public Image img;
	public int x;
	public int y;
	public Direction dir;
	public int speed = 20;
	public  MapleStoryClient msc;
	public int width;
	public int height;
	public boolean live = true;
	//¹¹Ôì·½·¨
	public Bullet() {
		this.x = 480;
	    this.y = 360;   		
	    this.dir = Direction.RIGHT;
	    if(dir == Direction.LEFT) {
	    	this.img = ImageUtil.getImage("hero_left_bullet");//×ó±ß
	    }else {
	    	this.img = ImageUtil.getImage("hero_right_bullet");//ÓÒ±ß
	    }    
	}
	
	public Bullet(MapleStoryClient msc,int x,int y,Direction dir) {
		this.msc = msc;
		this.x = x;
	    this.y = y; 		
	    this.dir = dir;
	    if(dir == Direction.LEFT) {
	    	this.img = ImageUtil.getImage("hero_left_bullet");//×ó±ß
	    }else {
	    	this.img = ImageUtil.getImage("hero_right_bullet");//ÓÒ±ß
	    }
	    this.width = img.getWidth(null);
	    this.height = img.getHeight(null);
	}
	
	public void draw(Graphics g) {
		if(!live) {
			msc.bullets.remove(this);
		}
		g.drawImage(img, x, y, null);
		move();
	}
	
	public void move() {
		 if(dir == Direction.LEFT) {
		    	this.x -= speed;
		    }else {
		    	this.x += speed;
		    }
	}
	
	public boolean hit(Mob mob) {
		//Åö×²Âß¼­
		if(this.live && mob.live && this.getRectangle().intersects(mob.getRectangle())) {
			/*System.out.println("´òµ½ÁË");*/
			//×Óµ¯ËÀ
			this.live = false;
			mob.HP-= 10;
			if(mob.HP <= 0) {
			//¹ÖÎïËÀ
			mob.live =false;
			Die die = new Die(msc,mob.x,mob.y,mob.dir);
			msc.dies.add(die);
			//msc.mobs.remove(mob);//ÒÆ³ýmob
			}
		}
		return false;	
	}
	
	public boolean hit(List<Mob> mobs) {
		for(int i=0;i<mobs.size();i++) {
			Mob mob = mobs.get(i);
			if(hit(mob)) {
				return true;
			}
		}
		return false;
		
	}
	public Rectangle getRectangle() {
		return new Rectangle(x,y,width,height);		
	}
}
