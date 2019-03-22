package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.ImageUtil;

public class Die {
	public static Image[] imgs = new Image[14];
    static {
    	for(int i=0;i<14;i++) {
    		imgs[i]=ImageUtil.getImage("mob_left_die"+i);
    	}
    }
    public int x;
    public int y;
    public MapleStoryClient msc;
    public boolean live = true;
    public Direction dir;
    public Die(MapleStoryClient msc,int x,int y,Direction dir) {
    	this.msc = msc;
    	this.x=x;
    	this.y=y;
    	this.dir = dir;
    }
    public int count = 0;
    public void draw(Graphics g) {
    	if(!live) {
    		msc.dies.remove(this);
    		return;
    	}
    	g.drawImage(imgs[count++], x, y, null);
    	if(count > 13) {	
    		live = false;
    		count = 0;
    	}  	
    }
}
