package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

//Hero��ķ�װ
public class Hero {
	/*
	 * Ӣ�����е�״̬������ͼƬ��
	 */
	public static Image[] imgs = new Image[100];
	static {
		//�����ߵ�5��ͼ��
		for(int i=0;i<5;i++) {
			imgs[i] = ImageUtil.getImage("hero_right_walk0"+i);
		}		
		//�����ߵ�5��ͼ��
				for(int i=5;i<10;i++) {
					imgs[i] = ImageUtil.getImage("hero_left_walk0"+(i-5));
				}	
		//�������������ͼ
				for(int i=0;i<4;i++) {
					imgs[i] = ImageUtil.getImage("hero_right_bow"+i);
				}	
		//�������������ͼ
				for(int i=4;i<9;i++) {
					imgs[i] = ImageUtil.getImage("hero_left_bow"+(i-4));
				}	
	}
	public Image img;
	public int x;
	public int y;
	public boolean left,right,walk,jump,shoot,bow;
	public int speed=10;//��������
	public int width;//ͼƬ���
	public int height;
	public Direction dir;
	public Action action;
	public MapleStoryClient msc;
	/*//���췽��
	public Hero() {
		this.img = GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_0.png");	
		this.x = 400;
		this.y = 400;
		//this.speed=10;	
	}*/
	//���췽�������أ�������ֵ
	public Hero(MapleStoryClient msc) {
		this.msc = msc;
		this.img = ImageUtil.getImage("hero_left_stand");
		this.x = 100;
		this.width = img.getWidth(null); //ͼƬ����Ŀ��
		this.height = img.getHeight(null);
		this.y = Constant.GAME_HEIGHT-210-height;
		//��ʼֵ����Ĭ�ϳ���
		this.dir = Direction.RIGHT;//������
		this.action = Action.STAND;//����վ
		//this.speed=10;		
	}
	//���ķ���
	public void move() {
		if(shoot) {
			shoot();
		}
		//��
		if(jump) {
			jump();
		}
		if(left) {
			this.dir = Direction.LEFT;			
		}else if(right) {
			this.dir = Direction.RIGHT;			
		}
		//��
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
		}else if(jump){//ԭ����
			this.action = Action.JUMP;
		}else if(bow){//ԭ�ؾ��𹭼�
			this.action = Action.SHOOT;
		}
		else {
			this.action = Action.STAND;
		}	
		outofBound();
	}	
	/*
	 * ���Խ������
	 */
	public void outofBound() {
		if(x < 0) {
		   x = 3;
		}
		if(x > Constant.GAME_WIDTH-this.width) {
		   x = Constant.GAME_WIDTH-this.width;//��ȥͼƬ�Ŀ��
		}
	}
	public int count_right_walk = 0;
	public int count_left_walk = 5;
	public int count_right_bow = 0;
	public int count_left_bow= 5;
	
	public void draw(Graphics g) {//û50ms����һ��
		//���ᶯ���ߵ�ͼ��
		if(count_right_walk>4) {
			count_right_walk = 0;			
		}
		//g.drawImage(imgs[count_right_walk++], x, y,null);
		if(count_left_walk > 9) {
			count_left_walk = 5;		
		}
		
		if(count_right_bow>4) {
			count_right_bow = 0;	//�ӻ���һ��		
		}
		
		if(count_left_bow > 9) {
			count_left_bow = 5;		
		}
		//g.drawImage(imgs[count_left_walk++], x, y,null);
		//���ݷ����жϻ�����ͼ
		switch (dir) {
		case LEFT://����վ��
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
		case RIGHT://����վ��
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
		move();//���ö��ķ�����	
	}
	//keyPressed���̰���
	public void keyPressed(KeyEvent e) {//KeyEvent�����¼�
		//System.out.println("������");
		//�����ж�
		/*System.out.println(e.getKeyCode());//getKeyCode()����ascll��
		if(e.getKeyCode()==65) {//A��
			x -= 10;		
		}*/
		/*
		 * ���������ƶ�������
		 */
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A://V:virtual���⣻K:key����
		    //x -= 10;
			left = true;
			walk = true;
			break;
		/*case KeyEvent.VK_W://V:virtual���⣻K:key����
		//	y -= 10;
			up = true;
			break;*/
		case KeyEvent.VK_D://V:virtual���⣻K:key����
		//	x += 10;
			right = true;
			walk = true;
			break;			
		case KeyEvent.VK_K://V:virtual���⣻K:key����
		    jump = true;
			break;
		case KeyEvent.VK_I://V:virtual���⣻K:key����
		    bow = true;
			break;
		case KeyEvent.VK_J://V:virtual���⣻K:key����
			/*if(shoot) {
				 shoot = false;
			}else {
				  shoot = true;
			}*/
			shoot = true;
			break;
	/*	case KeyEvent.VK_S://V:virtual���⣻K:key����
		//	y += 10;
			down = true;
			break;*/
		default:
			break;		
		}
	}
	//keyReleased����̧����
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A://V:virtual���⣻K:key����
		    //x -= 10;
			left = false;
			walk = false;
			break;
		/*case KeyEvent.VK_W://V:virtual���⣻K:key����
		//	y -= 10;
			up = false;
			break;*/
		case KeyEvent.VK_D://V:virtual���⣻K:key����
		//	x += 10;
			right = false;
			walk = false;
			break;
		case KeyEvent.VK_J://V:virtual���⣻K:key����
			/*if(shoot) {
				 shoot = false;
			}else {
				  shoot = true;
			}*/
			shoot = false;
			break;
	/*	case KeyEvent.VK_S://V:virtual���⣻K:key����
		//	y += 10;
			down = false;
			break;*/
		default:
			break;				
		}
		//System.out.println("̧����");
	}
/*
 * jump���㷨
 */
public double v0 = 30;//��ֱ���׵ĳ��ٶ�
public double vt = 0;
public static final double g = 9.8;
public double t =0.5;
public double delta_height = 0;
public boolean jump_up = true;//��ֱ���ף���֮Ϊ�������塣
public void jump() {
	//��ֱ����
	if(jump_up) {//һֱ����
		vt = v0-g*t;
		delta_height=v0*t;
		v0=vt;
		y-=delta_height;
		if(vt <= 0) {
			vt = 0;
			v0 = 0;
			jump_up = false;//������ֵ�����˶�
		}
	}	
	//��������
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
 *���乭���ķ��� 
 * 
 */
   private void shoot() {
	/*System.out.println("������һ���ӵ�");
	//new����
	Bullet bullet =new Bullet(msc,x+this.width,y+this.height/2,dir);
	//ÿ��һö�ӵ�������
	msc.bullets.add(bullet);*/
	   if(this.dir == Direction.LEFT) {
		//new����
		 Bullet bullet =new Bullet(msc,x-38,y+this.height/2,dir);
		//ÿ��һö�ӵ������������������
		msc.bullets.add(bullet);		   
	   }
	   if(this.dir == Direction.RIGHT) {
		 //new����
			 Bullet bullet =new Bullet(msc,x+this.width,y+this.height/2,dir);
			//ÿ��һö�ӵ������������������
			msc.bullets.add(bullet);
	   }
    }  
}
