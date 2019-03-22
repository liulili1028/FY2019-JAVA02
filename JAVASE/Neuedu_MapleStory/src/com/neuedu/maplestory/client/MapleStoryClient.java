package com.neuedu.maplestory.client;

import java.awt.*;//*����ð��µ�������
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
 * (һ)����ð�յ���Ŀ�����ļ����ͻ���
 * ���ɴ��ڵĲ���
 * 1.extends Frame���
 * 2.�Զ�����ش��ڵķ���
 * 3.��������������
 * ����������ڴ����л�����
 * 1.��дһ������ͼƬ�ķ���<br>
 */
public class MapleStoryClient extends FrameUtil{
	public BackGround bg = new BackGround();
	public Hero hero=new Hero(this);//this��ǰ��
	/*
	 * ����ӵ�������
	 */
	public List<Bullet> bullets = new ArrayList<>();
	/*
	 * ��Ź��������
	 */
	public List<Mob> mobs = new ArrayList<>();
	/*
	 * ��ű�ը������
	 */
	public List<Die> dies = new ArrayList<>();
	//public Die die = new Die(this,600,600);
	//public Bullet b = new Bullet();	
	//public Mob mob = new Mob(this,500);
	/*
	 * g����
	 */
	@Override
		public void paint(Graphics g) {
		bg.draw(g);
		hero.draw(g);
	//die.draw(g);
		for(int i = 0;i<bullets.size();i++) {
			//�õ������е�ÿһ����������
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			//��һ�����Ĺ�
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
		g.setFont(new Font("΢���ź�", Font.BOLD,20));
		g.setColor(Color.white);
		g.drawString("Ӣ�۵�ǰ�ķ���"+hero.dir, 60, 60);
		g.drawString("Ӣ�۵�ǰ��״̬��"+hero.action, 60, 100);
		g.drawString("���������Ĵ�С��"+mobs.size(), 60, 140);
		g.drawString("��ը�����Ĵ�С��"+dies.size(), 60, 180);
		g.setColor(c);//��ԭ
		g.setFont(f);//��ԭ
		}	
	@Override
	public void loadFrame() {
		// ���ø���FrameUtil�е�loadFrame()���ؿ��
		super.loadFrame();//����loadFrame�е�ȫ������
		//��Ӽ��̼�����
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
