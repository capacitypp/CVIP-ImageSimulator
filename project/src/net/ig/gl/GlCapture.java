package net.ig.gl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import com.jogamp.opengl.GL2;

import net.ig.util.Bounds;



public class GlCapture {

	public GlCapture(GL2 gl2) {
		Bounds bounds = WindowManager.getBounds();
		gl2.glReadBuffer(GL2.GL_BACK);
		ByteBuffer buffer = ByteBuffer.allocateDirect(bounds.getWidth() * bounds.getHeight() * 3);
		gl2.glReadPixels(0, 0, bounds.getWidth(), bounds.getHeight(), GL2.GL_BGR, GL2.GL_BYTE, buffer);
		BufferedImage bImage = new BufferedImage(bounds.getWidth(), bounds.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		final int COLOR_NUM = 3;
		int[] colorArray = new int[COLOR_NUM];
		System.out.println("position : " + buffer.position());
		for (int i = 0; i < bounds.getHeight(); i++) {
			for (int j = 0; j < bounds.getWidth(); j++) {
				for (int k = 0; k < COLOR_NUM; k++)
					colorArray[k] = buffer.get();
				bImage.setRGB(j, bounds.getHeight() - i - 1, new Color(colorArray[2], colorArray[1], colorArray[0]).getRGB());
				//bImage.setRGB(j, i, new Color(255, 255, 255).getRGB());
			}
		}
		try {
			//デスクトップに保存
			String desktopPath = new File(System.getProperty("user.home"), "Desktop").getAbsolutePath();
			ImageIO.write(bImage, "jpg", new File(desktopPath, "output.jpg"));

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
