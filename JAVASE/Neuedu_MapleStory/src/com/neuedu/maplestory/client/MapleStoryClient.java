package com.neuedu.maplestory.client;

import java.awt.*;//*导入该包下的所有类
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import com.neuedu.maplestory.entity.BackGround;
import com.neuedu.maplestory.entity.Bullet;
import com.neuedu.maplestory.entity.Die;
import com.neuedu.maplestory.entity.Hero;
import com.neuedu.maplestory.entity.Mob;
import com.neuedu.maplestory.util.FrameUtil;
/*
 * (一)运行冒险岛项目的主文件，客户端
 * 生成窗口的步骤
 * 1.extends Frame类库
 * 2.自定义加载窗口的方法
 * 3.主函数启动窗口
 * （二）如何在窗口中化内容
 * 1.编写一个加载图片的方法<br>
 */
public class MapleStoryClient extends FrameUtil{
	public BackGround bg = new BackGround();
	public Hero hero=new Hero(this);//this当前类
	/*
	 * 存放子弹的容器
	 */
	public List<Bullet> bullets = new ArrayList<>();
	/*
	 * 存放怪物的容器
	 */
	public List<Mob> mobs = new ArrayList<>();
	/*
	 * 存放爆炸的容器
	 */
	public List<Die> dies = new ArrayList<>();
	//public Die die = new Die(this,600,600);
	//public Bullet b = new Bullet();	
	//public Mob mob = new Mob(this,500);
	/*
	 * g画笔
	 */
	@Override
		public void paint(Graphics g) {
		bg.draw(g);
		hero.draw(g);
	//die.draw(g);
		for(int i = 0;i<bullets.size();i++) {
			//得到容器中的每一个并画出来
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			//打一个连的怪
			bullet.hit(mobs);			
		}
		for(int i = 0;i<mobs.size();i++) {
			mobs.get(i).draw(g);
		}		
		for(int i = 0;i<dies.size();i++) {
			dies.get(i).draw(g);
		}
		//b.draw(g);
		Color c = g.getColor();
		Font f = g.getFont();
		g.setFont(new Font("微软雅黑", Font.BOLD,20));
		g.setColor(Color.white);
		g.drawString("英雄当前的方向："+hero.dir, 60, 60);
		g.drawString("英雄当前的状态："+hero.action, 60, 100);
		g.drawString("怪物容器的大小："+mobs.size(), 60, 140);
		g.drawString("爆炸容器的大小："+dies.size(), 60, 180);
		g.setColor(c);//还原
		g.setFont(f);//还原
		}	
	@Override
	public void loadFrame() {
		// 调用父类FrameUtil中的loadFrame()负载框架
		super.loadFrame();//拷贝loadFrame中的全部内容
		//添加键盘监听器
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				hero.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				hero.keyReleased(e);
			}
		});
		for(int i = 0;i<5;i++) {
			Mob mob = new Mob(this,200+100*i);
			mobs.add(mob);
		}		
	}
	public static void main(String args[]) {
		new MapleStoryClient().loadFrame();
	}
}
