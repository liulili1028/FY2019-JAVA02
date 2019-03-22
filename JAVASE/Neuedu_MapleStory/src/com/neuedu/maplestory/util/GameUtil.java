package com.neuedu.maplestory.util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * 游戏工具类，用于加载图片、声音资源
 */
public class GameUtil {
/*
 * 加载图片的方法
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
