package com.runlion.shop.controller.home.tool;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.uploadfile.FileUpload;
import com.runlion.shop.common.util.uploadfile.UploadFileParameter;
import com.runlion.shop.common.util.uploadfile.UploadFilePathEnum;
import com.runlion.shop.vo.UpLoadFileInfor;

/**
 * Filename: UploadFileController.java <br>
 * Title: 上传文件<br>
 * De.ion: TODO(这里用一句话描述这个类的作用)<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月9日 <br>
 * Time: 下午5:39:53 <br>
 * Version: 1.0.0 <br>
 */
@Controller
@RequestMapping("/uploadfile/upload")
public class UploadFileController {

	@RequestMapping(params = "method=touploadimg")
	public String touploadImg(ModelMap m) {
		List<UploadFilePathEnum> u = EnumsUtil
				.valueOfList(UploadFilePathEnum.class);
		m.put("OrderStateEnum", u);
		return "home/tool/uploadfile/touploadImg";
	}

	@RequestMapping(params = "method=uploadimg", method = RequestMethod.POST)
	@ResponseBody
	public UpLoadFileInfor uploadImg(Integer pathType,
			HttpServletRequest request) throws Exception {

		UploadFileParameter up = new UploadFileParameter();
		up.setPathType(pathType);// 路径
		up.setRequest(request);// request
		up.setThumbWhether(true);// 是否生成缩略图
		UpLoadFileInfor fileInfor = FileUpload.imgFileUpLoad(up);

		return fileInfor;
	}

}
