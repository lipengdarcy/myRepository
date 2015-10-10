package com.runlion.shop.common.util;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.runlion.shop.vo.UpLoadFileInfor;

/**
 * Created by lwt Date: 15-07-10 Time:下午4:12
 */
public class FileUpload {

	public static final String FILE_PATH = "upload/product/";

	// 文件上传
	public static UpLoadFileInfor uploadFile(MultipartFile file,
			HttpServletRequest request, String cusPath) throws IOException {
		String tpath = FILE_PATH;
		if (cusPath != null) {
			tpath = cusPath;
		}
		String fileName = file.getOriginalFilename();
		int lindex = fileName.lastIndexOf(".");
		String markLFix = fileName.substring(lindex + 1);
		String path = request.getSession().getServletContext()
				.getRealPath(tpath);
		// 生成一个5位的随机数
		Random r = new Random();
		float nf = r.nextFloat();
		BigDecimal bd = new BigDecimal(nf);
		bd = bd.setScale(5, BigDecimal.ROUND_HALF_UP);
		nf = bd.floatValue();
		int tinf = (int) nf * 100000;
		// 使用时间戳+随机数生成文件名
		String saveFileName = new Date().getTime() + tinf + "." + markLFix;
		File tempFile = new File(path, saveFileName);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdir();
		}
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		file.transferTo(tempFile);

		UpLoadFileInfor fileInfor = new UpLoadFileInfor();

		//fileInfor.setFileName(fileName);
		//fileInfor.setFilePath(path);
		//fileInfor.setSaveFileName(saveFileName);
		//fileInfor.setUrlPath(tpath);

		return fileInfor;
	}

	public static UpLoadFileInfor imgFileUpLoad(MultipartFile file,
			HttpServletRequest request, String cusPath, String thumbPreFix,
			String[] thumbLastFixs, String imgMark, Integer markType,
			Integer posType, float alph, int fontSize, String fontFam)
			throws Exception {
		// 上传文件
		UpLoadFileInfor ufile = FileUpload.uploadFile(file, request, cusPath);
		// 获取上传文件的属性，准备处理图片，包括打水印和生成缩略图
		String saveFileName = "";//ufile.getSaveFileName();
		String saveImgName = "";//ufile.getSaveFileName();
		String path = "";//ufile.getSaveFileName();
		// 准备打水印
		if (imgMark != null && markType != null && markType != 0) {
			int lindex = saveFileName.lastIndexOf(".");
			String markLFix = saveFileName.substring(lindex + 1);
			String markFName = saveFileName.substring(0, lindex) + "_mark."
					+ markLFix;
			saveImgName = markFName;

			int posint = 1;
			if (posType != null) {
				posint = posType;
			}
			if (markType == 1) {// 文字水印
				ImgMarkUtil.pressText(imgMark, path + "\\" + saveFileName, path
						+ "\\" + markFName, fontFam, Font.PLAIN, Color.WHITE,
						fontSize, posint, alph);
			} else if (markType == 2) {// 图片水印
				ImgMarkUtil.pressImage(imgMark, path + "\\" + saveFileName,
						path + "\\" + markFName, posint, alph);
			}

		}
		// 生成缩略图
		if (thumbPreFix != null) {
			ImgThumbUtil imgThumb = new ImgThumbUtil();

			if (thumbLastFixs != null && thumbLastFixs.length > 0) {
				for (int ti = 0; ti < thumbLastFixs.length; ti++) {
					String lastFix = thumbLastFixs[ti];
					String[] thumbWH = lastFix.split("_");
					if (thumbWH.length >= 1) {
						String thumbW = thumbWH[0];
						int width = Integer.valueOf(thumbW);
						String thumbH = thumbWH[1];
						int height = Integer.valueOf(thumbH);
						// //////////////////
						String thumPath = request.getSession()
								.getServletContext()
								.getRealPath(thumbPreFix + lastFix);
						File thumFile = new File(thumPath);
						if (!thumFile.exists()) {
							thumFile.mkdir();
						}
						imgThumb.getThumbnail(path + "\\" + saveImgName,
								thumPath + "\\" + saveFileName, width, height);
					}

				}
			} else {
				String thumPath = request.getSession().getServletContext()
						.getRealPath(thumbPreFix + "350_350");
				File thumFile = new File(thumPath);
				if (!thumFile.exists()) {
					thumFile.mkdir();
				}
				imgThumb.getThumbnail(path + "\\" + saveImgName, path + "\\"
						+ thumbPreFix + "\\" + saveFileName, 350, 350);
			}
		}

		UpLoadFileInfor fileInfor = new UpLoadFileInfor();

		

		return fileInfor;

	}

	public static File getFile(String fileName, HttpServletRequest request,
			String cusPath) {
		String tpath = FILE_PATH;
		if (cusPath != null) {
			tpath = cusPath;
		}
		String path = request.getSession().getServletContext()
				.getRealPath(tpath);
		File file = new File(path, fileName);
		return file;
	}
}