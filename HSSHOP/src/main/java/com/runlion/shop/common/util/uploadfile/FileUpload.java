package com.runlion.shop.common.util.uploadfile;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.WebConfigHandler;
import com.runlion.shop.vo.UpLoadConfVO;
import com.runlion.shop.vo.UpLoadFileInfor;

/**
 * Created by lwt Date: 15-07-10 Time:下午4:12 Update by zhaowei Date:15-8-27
 * Time:上午11:02
 */
/**
 * Filename: FileUpload.java <br>
 * Ttitle: jsp转换成html<br>
 * De.ion: TODO(这里用一句话描述这个类的作用)<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月10日 <br>
 * Time: 上午9:47:46 <br>
 * Version: 1.0.0 <br>
 */
public class FileUpload {

	// windows和linux下的路径分隔符存在一些区别:linux 下如果在应用名称后面加自己的文件路径的话,要转 "\\" 为 "/" ，因为
	// linux 下 "\\" 显示的是 "webapps/appname\filepath" 。
	public static final String FILE_PATH_SEP = System
			.getProperty("file.separator");

	// 上传路径upload
	public static final String FILE_PATH = FILE_PATH_SEP + "upload"
			+ FILE_PATH_SEP;

	/**
	 * TODO(获取上传路径)
	 * 
	 * @param f
	 * @return String 返回类型
	 */
	public static String getUploadFilePath(UploadFilePathEnum f) {
		return FILE_PATH + f.getDesc() + FILE_PATH_SEP;
	}

	// 文件上传
	public static UploadState uploadFile(UploadFileParameter up)
			throws IOException {

		UploadState state = null;

		try {
			state = UploadFileUtils.upload4Stream(up.getFileName(),
					up.getSavePath() + up.getPathSource() + up.getPathDate(),
					up.getFile());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return state;
	}

	public static UpLoadFileInfor imgFileUpLoad(UploadFileParameter up)
			throws Exception {

		UpLoadFileInfor ufile = new UpLoadFileInfor();
		try {

			if (up.getMultipartFile() == null
					|| up.getMultipartFile().getSize() == 0) {

				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) up
						.getRequest();
				MultipartFile file = multipartRequest.getFile("Filedata");

				if (file != null && file.getSize() > 0) {
					up.setMultipartFile(file);
				}
			}

			if (up.getMultipartFile() == null
					|| up.getMultipartFile().getSize() == 0) {
				ufile.setUploadState(UploadState.UPLOAD_ZEROSIZE);
				return ufile;
			}

			CommonsMultipartFile cf = (CommonsMultipartFile) up
					.getMultipartFile();
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File f = fi.getStoreLocation();

			up.setSavePath(up.getRequest().getSession().getServletContext()
					.getRealPath("/"));// 当前项目路径
			up.setOriginalFilename(up.getMultipartFile().getOriginalFilename());// name
			up.setFileName(UploadFileUtils.getRandomName(up
					.getOriginalFilename()));// filename
			up.setFile(f);// 文件

			// 获取上传配置信息
			UpLoadConfVO configVo = new UpLoadConfVO();
			WebConfigHandler.getConfigInfor(configVo, up.getSavePath()
					+ "/WEB-INF/classes/config/shop.config");

			String[] thumbLastFixs = configVo.getProductShowThumbSize().split(
					",");
			String mtype = configVo.getWatermarkType();
			int imtype = Integer.valueOf(mtype);
			String mpostype = configVo.getWatermarkPosition();
			int impostype = Integer.valueOf(mpostype);
			String alph = configVo.getWatermarkImgOpacity();
			float falph = Float.valueOf(alph);
			String wimgPath = up.getSavePath() + FILE_PATH;
			String textInfor = "";
			if (imtype == 1) {
				textInfor = configVo.getWatermarkText();
			} else if (imtype == 2) {
				textInfor = wimgPath + configVo.getWatermarkImg();
			}
			String fsize = configVo.getWatermarkTextSize();
			int ifize = Integer.valueOf(fsize);
			String ffam = configVo.getWatermarkTextFont();

			// 上传文件
			UploadState state = FileUpload.uploadFile(up);
			if (state == UploadState.UPLOAD_SUCCSSS) {

				String targetImg = up.getSavePath();

				// 准备打水印
				if (textInfor != null && imtype != 0) {
					int posint = 1;
					if (impostype != 0) {
						posint = impostype;
					}
					if (imtype == 1) {// 文字水印
						ImgMarkUtil.pressText(textInfor, ffam, Font.PLAIN,
								Color.WHITE, ifize, posint, falph, up);
					} else if (imtype == 2) {// 图片水印
						ImgMarkUtil.pressImage(textInfor, posint, falph, up);
					}

				} else {
					ImgMarkUtil.pressText("", ffam, Font.PLAIN, Color.WHITE,
							ifize, 1, falph, up);
				}

				// 生成缩略图
				if (up.getThumbWhether()) {
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
								String thumPath = targetImg + up.getPathThumb()
										+ lastFix + FILE_PATH_SEP
										+ up.getPathDate();
								UploadFileUtils.mkDir(thumPath);
								imgThumb.getThumbnail(
										targetImg + up.getPathSource()
												+ up.getPathDateFileName(),
										thumPath + up.getFileName(), width,
										height);
							}

						}
					} else {
						String thumPath = targetImg + up.getPathThumb()
								+ "350_350" + FILE_PATH_SEP + up.getPathDate();
						UploadFileUtils.mkDir(thumPath);
						imgThumb.getThumbnail(targetImg + up.getPathSource()
								+ up.getPathDateFileName(),
								thumPath + up.getFileName(), 350, 350);
					}
				}

				ufile.setUrlDateFileName(up.getUrlDateFileName());
				ufile.setXeDateFileName(up.getXeDateFileName());

			} else {
				ufile.setUploadState(UploadState.UPLOAD_ZEROSIZE);
				return ufile;
			}

			ufile.setUploadState(state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ufile;

	}

	public static File getFile(String fileName, HttpServletRequest request,
			String cusPath) {

		// 路径
		UploadFilePathEnum orderState = EnumsUtil.valueOf(
				UploadFilePathEnum.class, (byte)5);
		String source = FileUpload.getUploadFilePath(orderState);

		String tpath = source;
		if (cusPath != null) {
			tpath = cusPath;
		}
		String path = request.getSession().getServletContext()
				.getRealPath(tpath);
		File file = new File(path, fileName);
		return file;
	}

	public static void main(String[] args) {

		UpLoadFileInfor fileInfor = new UpLoadFileInfor();

		System.out.println(fileInfor);

		String fileName = ".jpg.jpgfewfwe.jpg.jpg";
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			System.out
					.println(fileName.substring(0, fileName.lastIndexOf(".")));
		}

		String str = "2015\\09\\10\\14418771783820.jpg";
		str = str.replaceAll("\\\\", "_");

		System.out.println("Result:" + str);

	}
}