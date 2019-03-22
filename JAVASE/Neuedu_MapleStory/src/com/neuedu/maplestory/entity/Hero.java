package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

//Hero类的封装
public class Hero {
	/*
	 * 英雄所有的状态、动作图片组
	 */
	public static Image[] imgs = new Image[100];
	static {
		//朝右走的5张图组
		for(int i=0;i<5;i++) {
			imgs[i] = ImageUtil.getImage("hero_right_walk0"+i);
		}		
		//朝左走的5张图组
				for(int i=5;i<10;i++) {
					imgs[i] = ImageUtil.getImage("hero_left_walk0"+(i-5));
				}	
		//朝右射击的四张图
				for(int i=0;i<4;i++) {
					imgs[i] = ImageUtil.getImage("hero_right_bow"+i);
				}	
		//朝左射击的四张图
				for(int i=4;i<9;i++) {
					imgs[i] = ImageUtil.getImage("hero_left_bow"+(i-4));
				}	
	}
	public Image img;
	public int x;
	public int y;
	public boolean left,right,walk,jump,shoot,bow;
	public int speed=10;//增减速率
	public int width;//图片宽度
	public int height;
	public Direction dir;
	public Action action;
	public MapleStoryClient msc;
	/*//构造方法
	public Hero() {
		this.img = GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_0.png");	
		this.x = 400;
		this.y = 400;
		//this.speed=10;	
	}*/
	//构造方法的重载，参数赋值
	public Hero(MapleStoryClient msc) {
		this.msc = msc;
		this.img = ImageUtil.getImage("hero_left_stand");
		this.x = 100;
		this.width = img.getWidth(null); //图片对象的宽度
		this.height = img.getHeight(null);
		this.y = Constant.GAME_HEIGHT-210-height;
		//初始值方向默认朝右
		this.dir = Direction.RIGHT;//方向右
		this.action = Action.STAND;//动作站
		//this.speed=10;		
	}
	//动的方法
	public void move() {
		if(shoot) {
			shoot();
		}
		//跳
		if(jump) {
			jump();
		}
		if(left) {
			this.dir = Direction.LEFT;			
		}else if(right) {
			this.dir = Direction.RIGHT;			
		}
		//走
		if(walk) {
			if(left) {
				x -= speed;	
			}
			if(right) {
				x += speed;
			}
			this.action = Action.WALK;
			if(jump) {
				this.action = Action.JUMP;
			    if(bow) {
				   this.action = Action.SHOOT;
				      }
			}
		}else if(jump){//原地跳
			this.action = Action.JUMP;
		}else if(bow){//原地举起弓箭
			this.action = Action.SHOOT;
		}
		else {
			this.action = Action.STAND;
		}	
		outofBound();
	}	
	/*
	 * 解决越界问题
	 */
	public void outofBound() {
		if(x < 0) {
		   x = 3;
		}
		if(x > Constant.GAME_WIDTH-this.width) {
		   x = Constant.GAME_WIDTH-this.width;//减去图片的宽度
		}
	}
	public int count_right_walk = 0;
	public int count_left_walk = 5;
	public int count_right_bow = 0;
	public int count_left_bow= 5;
	
	public void draw(Graphics g) {//没50ms调用一次
		//不会动的走的图组
		if(count_right_walk>4) {
			count_right_walk = 0;			
		}
		//g.drawImage(imgs[count_right_walk++], x, y,null);
		if(count_left_walk > 9) {
			count_left_walk = 5;		
		}
		
		if(count_right_bow>4) {
			count_right_bow = 0;	//从化第一张		
		}
		
		if(count_left_bow > 9) {
			count_left_bow = 5;		
		}
		//g.drawImage(imgs[count_left_walk++], x, y,null);
		//根据方向判断画哪张图
		switch (dir) {
		case LEFT://朝左站着
			switch (action) {
			   case STAND:
				  g.drawImage(ImageUtil.getImage("hero_left_stand"), x, y, null);
				  break;
			   case WALK:
				   g.drawImage(imgs[count_left_walk++], x, y,null);
				   break;	
			   case JUMP:
				   g.drawImage(ImageUtil.getImage("hero_left_jump"), x, y,null);
				   break;
			   case SHOOT:
				   g.drawImage(imgs[count_left_bow++], x, y,null);
				   break;	
			default:
				break;			
			}
			break;
		case RIGHT://朝右站着
			switch (action) {
			case STAND:
				g.drawImage(ImageUtil.getImage("hero_right_stand"), x, y, null);
				break;
			case WALK:
				g.drawImage(imgs[count_right_walk++], x, y,null);
				break;
			case JUMP:
				 g.drawImage(ImageUtil.getImage("hero_right_jump"), x, y,null);
				   break;
			 case SHOOT:
				   g.drawImage(imgs[count_right_bow++], x, y,null);
				   break;	
			default:
				break;			
			}		
		default:
			break;	
		}
		move();//调用动的方法。	
	}
	//keyPressed键盘按下
	public void keyPressed(KeyEvent e) {//KeyEvent键盘事件
		//System.out.println("按下了");
		//按键判断
		/*System.out.println(e.getKeyCode());//getKeyCode()键盘ascll码
		if(e.getKeyCode()==65) {//A键
			x -= 10;		
		}*/
		/*
		 * 按键触发移动的条件
		 */
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A://V:virtual虚拟；K:key键盘
		    //x -= 10;
			left = true;
			walk = true;
			break;
		/*case KeyEvent.VK_W://V:virtual虚拟；K:key键盘
		//	y -= 10;
			up = true;
			break;*/
		case KeyEvent.VK_D://V:virtual虚拟；K:key键盘
		//	x += 10;
			right = true;
			walk = true;
			break;			
		case KeyEvent.VK_K://V:virtual虚拟；K:key键盘
		    jump = true;
			break;
		case KeyEvent.VK_I://V:virtual虚拟；K:key键盘
		    bow = true;
			break;
		case KeyEvent.VK_J://V:virtual虚拟；K:key键盘
			/*if(shoot) {
				 shoot = false;
			}else {
				  shoot = true;
			}*/
			shoot = true;
			break;
	/*	case KeyEvent.VK_S://V:virtual虚拟；K:key键盘
		//	y += 10;
			down = true;
			break;*/
		default:
			break;		
		}
	}
	//keyReleased键盘抬起了
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A://V:virtual虚拟；K:key键盘
		    //x -= 10;
			left = false;
			walk = false;
			break;
		/*case KeyEvent.VK_W://V:virtual虚拟；K:key键盘
		//	y -= 10;
			up = false;
			break;*/
		case KeyEvent.VK_D://V:virtual虚拟；K:key键盘
		//	x += 10;
			right = false;
			walk = false;
			break;
		case KeyEvent.VK_J://V:virtual虚拟；K:key键盘
			/*if(shoot) {
				 shoot = false;
			}else {
				  shoot = true;
			}*/
			shoot = false;
			break;
	/*	case KeyEvent.VK_S://V:virtual虚拟；K:key键盘
		//	y += 10;
			down = false;
			break;*/
		default:
			break;				
		}
		//System.out.println("抬起了");
	}
/*
 * jump的算法
 */
public double v0 = 30;//竖直上抛的初速度
public double vt = 0;
public static final double g = 9.8;
public double t =0.5;
public double delta_height = 0;
public boolean jump_up = true;//竖直上抛，反之为自由落体。
public void jump() {
	//竖直上抛
	if(jump_up) {//一直向上
		vt = v0-g*t;
		delta_height=v0*t;
		v0=vt;
		y-=delta_height;
		if(vt <= 0) {
			vt = 0;
			v0 = 0;
			jump_up = false;//结束数值上抛运动
		}
	}	
	//自由落体
	else {
		vt = v0+g*t;
		delta_height=v0*t;
		v0=vt;
		y+=delta_height;
		if(y >=  Constant.GAME_HEIGHT-210-height) {
			y =  Constant.GAME_HEIGHT-210-height;
			v0=30;
			vt = 0;
			jump_up = true;
			jump = false;
		}		
	}
}
/*
 *发射弓箭的方法 
 * 
 */
   private void shoot() {
	/*System.out.println("发射了一发子弹");
	//new弓箭
	Bullet bullet =new Bullet(msc,x+this.width,y+this.height/2,dir);
	//每又一枚子弹创建，
	msc.bullets.add(bullet);*/
	   if(this.dir == Direction.LEFT) {
		//new弓箭
		 Bullet bullet =new Bullet(msc,x-38,y+this.height/2,dir);
		//每又一枚子弹创建，向容器中添加
		msc.bullets.add(bullet);		   
	   }
	   if(this.dir == Direction.RIGHT) {
		 //new弓箭
			 Bullet bullet =new Bullet(msc,x+this.width,y+this.height/2,dir);
			//每又一枚子弹创建，向容器中添加
			msc.bullets.add(bullet);
	   }
    }  
}
