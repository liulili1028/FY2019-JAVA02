package com.neuedu.maplestory.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.neuedu.maplestory.constant.Constant;

/*
 * 加载窗口相关属性和方法的工具类
 * FrameUtil  Frame 一个类的复用性
 */
public class FrameUtil extends Frame{

	/*
	 * 加载窗口的方法
	 */
		public void loadFrame() {
	        //1设置窗口大小
			this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	        //2.设定窗口生成时的位置
			//this.setLocation(0, 0);
			//上下左右居中显示
			this.setLocationRelativeTo(null);
			//3.设置可见性
			this.setVisible(true);
			//4.关闭窗口的方法,添加窗口的监听(匿名内部类)
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					//关闭窗口
					System.exit(0);
				}
			});
			//5.设置窗口游戏标题
			this.setTitle("冒险岛");
			//6.修改窗口背景颜色,取值【0-255】
			//this.setBackground(new Color(123,211,78));
			//7.启动重画线程
			new MyThread().start();
		}
		
		/*
		 * 多线程并行操作：一直重新画当前的位置。
		 * 
		 * 知识点扩展：进程-一个应用
		 *          多线程-各司其职互不干扰
		 */
		
	    class MyThread extends Thread{
	    	@Override
	    	public void run() {
	    		for(;;) {
	    			repaint();//自动调用paint()方法。人眼舒适帧数每秒24帧到25帧
	    			try {
						Thread.sleep(40);//毫秒：每秒刷新25次
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    	}
	    }
	    
	    
	    /* 图片闪烁解决方法：双缓冲
	     * 人眼看的频率和计算机刷新的频率不一致
	     */
	     Image backImg = null;
	     //重写update()方法，在窗口里层添加一个虚拟的图片
	     @Override
	     public void update(Graphics g) {
	     	if (backImg == null) {
	     		//如果虚拟图片不存在，创建一个和窗口一样大小的图片
	     		backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	     		
	     	}
	     	//获取到虚拟图片的画笔
	     	Graphics backg = backImg.getGraphics();
	     	Color c = backg.getColor();
	     	backg.setColor(Color.black);
	     	backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	     	backg.setColor(c);
	     	//调用虚拟图片的paint()方法，没50ms刷新一次
	     	paint(backg);
	     	g.drawImage(backImg, 0, 0, null);
	     }
	     
}
