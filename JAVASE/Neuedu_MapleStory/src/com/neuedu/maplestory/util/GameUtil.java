package com.neuedu.maplestory.util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * ��Ϸ�����࣬���ڼ���ͼƬ��������Դ
 */
public class GameUtil {
/*
 * ����ͼƬ�ķ���
 */
	public static Image getImage(String imgPath) {
		URL u =GameUtil.class.getClassLoader().getResource(imgPath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return img;
		
	}
}
