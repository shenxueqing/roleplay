package com.ebupt.roleplay.server.north.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ImageUtil {
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	static {
		System.setProperty("java.awt.headless", "true");
	}

	public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = output.createGraphics();

		// This is what we want, but it only does hard-clipping, i.e. aliasing
		// g2.setClip(new RoundRectangle2D ...)

		// so instead fake soft-clipping by first drawing the desired clip shape
		// in fully opaque white with antialiasing enabled...
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

		// ... then compositing the image on top,
		// using the white shape from above as alpha source
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(image, 0, 0, null);

		g2.dispose();
		return output;
	}
/*
	public static String converImagToRoundedCorner(String oriPath) {
		try {

			BufferedImage icon = ImageIO.read(new File(oriPath));
			BufferedImage rounded = makeRoundedCorner(icon, 40);
			String targetPath = oriPath.substring(0, oriPath.lastIndexOf('.')) + ".rounded.png";
			ImageIO.write(rounded, "png", new File(targetPath));
			FileUtil.deleteFile(oriPath);
			return "/" + targetPath.replace(JhoNorthConfig.picDirectory, "");
		} catch (Exception e) {
			logger.error("e:{}", e);

		}
		return "/" + oriPath.replace(JhoNorthConfig.picDirectory, "");

	}
	

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder(JhoNorthConfig.picDirectory);
		sb.append(TimeUtil.getYearInStringFormat());
		sb.append("/");
		sb.append(TimeUtil.getMonthAndDayInStringFormat());
		sb.append("/");
		sb.append("aaa");
		sb.append(".jpg");
		System.out.println(converImagToRoundedCorner(sb.toString()));
		String q = new String("D:\\attachments\\1853742185.jpg");
		System.out.println(converImagToRoundedCorner(q));

	}
	*/

	public static byte[] mergeImageAndText(String imageFilePath, String text, Point textPosition) throws IOException {
		BufferedImage im = ImageIO.read(new File(imageFilePath));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(im, "png", baos);
		return baos.toByteArray();
	}

}
