package com.neuedu.maplestory.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.neuedu.maplestory.constant.Constant;

/*
 * ���ش���������Ժͷ����Ĺ�����
 * FrameUtil  Frame һ����ĸ�����
 */
public class FrameUtil extends Frame{

	/*
	 * ���ش��ڵķ���
	 */
		public void loadFrame() {
	        //1���ô��ڴ�С
			this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	        //2.�趨��������ʱ��λ��
			//this.setLocation(0, 0);
			//�������Ҿ�����ʾ
			this.setLocationRelativeTo(null);
			//3.���ÿɼ���
			this.setVisible(true);
			//4.�رմ��ڵķ���,��Ӵ��ڵļ���(�����ڲ���)
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					//�رմ���
					System.exit(0);
				}
			});
			//5.���ô�����Ϸ����
			this.setTitle("ð�յ�");
			//6.�޸Ĵ��ڱ�����ɫ,ȡֵ��0-255��
			//this.setBackground(new Color(123,211,78));
			//7.�����ػ��߳�
			new MyThread().start();
		}
		
		/*
		 * ���̲߳��в�����һֱ���»���ǰ��λ�á�
		 * 
		 * ֪ʶ����չ������-һ��Ӧ��
		 *          ���߳�-��˾��ְ��������
		 */
		
	    class MyThread extends Thread{
	    	@Override
	    	public void run() {
	    		for(;;) {
	    			repaint();//�Զ�����paint()��������������֡��ÿ��24֡��25֡
	    			try {
						Thread.sleep(40);//���룺ÿ��ˢ��25��
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    	}
	    }
	    
	    
	    /* ͼƬ��˸���������˫����
	     * ���ۿ���Ƶ�ʺͼ����ˢ�µ�Ƶ�ʲ�һ��
	     */
	     Image backImg = null;
	     //��дupdate()�������ڴ���������һ�������ͼƬ
	     @Override
	     public void update(Graphics g) {
	     	if (backImg == null) {
	     		//�������ͼƬ�����ڣ�����һ���ʹ���һ����С��ͼƬ
	     		backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	     		
	     	}
	     	//��ȡ������ͼƬ�Ļ���
	     	Graphics backg = backImg.getGraphics();
	     	Color c = backg.getColor();
	     	backg.setColor(Color.black);
	     	backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	     	backg.setColor(c);
	     	//��������ͼƬ��paint()������û50msˢ��һ��
	     	paint(backg);
	     	g.drawImage(backImg, 0, 0, null);
	     }
	     
}
