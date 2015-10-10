package com.runlion.shop.common.util.uploadfile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgMarkUtil {

	/**
	 * public final static String getPressImgPath() { return ApplicationContext
	 * .getRealPath("/template/data/util/shuiyin.gif"); }
	 */

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg
	 *            -- 水印文件
	 * @param targetImg
	 *            -- 目标文件
	 * @param x
	 *            --x坐标
	 * @param y
	 *            --y坐标
	 */
	public final static void pressImage(String pressImg, int posType,
			float alpha, UploadFileParameter up) {
		try {
			UploadFileUtils.mkDir(up.getSavePath() + up.getPathWater()
					+ up.getPathDate());
			// 目标文件
			File _file = new File(up.getSavePath() + up.getPathSource()
					+ up.getPathDateFileName());
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);

			Point p = ImgMarkUtil.getPoint(posType, wideth, height,
					wideth_biao, height_biao);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_ATOP, alpha));
			g.drawImage(src_biao, (int) p.getX(), (int) p.getY(), wideth_biao,
					height_biao, null);
			// 水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(up.getSavePath()
					+ up.getPathWater() + up.getPathDateFileName());
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param targetImg
	 *            -- 目标图片
	 * @param fontName
	 *            -- 字体名
	 * @param fontStyle
	 *            -- 字体样式
	 * @param color
	 *            -- 字体颜色
	 * @param fontSize
	 *            -- 字体大小
	 * @param x
	 *            -- 偏移量
	 * @param y
	 */

	public static void pressText(String pressText, String fontName,
			int fontStyle, Color color, int fontSize, int posType, float alpha,
			UploadFileParameter up) {
		try {
			UploadFileUtils.mkDir(up.getSavePath() + up.getPathWater()
					+ up.getPathDate());
			File _file = new File(up.getSavePath() + up.getPathSource()
					+ up.getPathDateFileName());
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			g.setColor(color);
			Font wfont = new Font(fontName, fontStyle, fontSize);
			g.setFont(wfont);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_ATOP, alpha));

			FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(wfont);
			Point p = ImgMarkUtil.getPoint(posType, wideth, height,
					fm.stringWidth(pressText), fm.getHeight());

			g.drawString(pressText, (int) p.getX(), (int) p.getY() + fontSize);
			g.dispose();
			FileOutputStream out = new FileOutputStream(up.getSavePath()
					+ up.getPathWater() + up.getPathDateFileName());
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Point getPoint(int posType, int srcw, int srch, int imgw,
			int imgh) {
		Point p = new Point();
		if (posType == 1) {// 左上
			p.setLocation(5, 5);
		} else if (posType == 2) {// 左下
			p.setLocation(5, srch - imgh - 5);
		} else if (posType == 3) {// 右上
			p.setLocation(srcw - imgw - 5, 5);
		} else if (posType == 4) {// 右下
			p.setLocation(srcw - imgw - 5, srch - imgh - 5);
		} else {
			p.setLocation((srcw - imgw) / 2, (srch - imgh) / 2);
		}
		return p;
	}

	public static void main(String[] args) {
		// pressImage("E:/xhr.png", "E:/1_mark.jpg", 5, 0.3f);
		// pressText("水印文字", "E:/1_mark.jpg", "宋体", Font.PLAIN, Color.RED, 18,
		// 5,
		// 0.8f);
	}
}
