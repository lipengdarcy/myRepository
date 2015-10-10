package com.runlion.shop.controller.home.center;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.runlion.shop.common.util.uploadfile.FileUpload;
import com.runlion.shop.common.util.uploadfile.UploadFileParameter;
import com.runlion.shop.common.util.uploadfile.xheditor.XheditorRoot;
import com.runlion.shop.vo.UpLoadFileInfor;

@Controller
@RequestMapping("/ucenter/uploadfile")
public class CenterUploadFileController {

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

	@RequestMapping(params = "method=xeupload", method = RequestMethod.POST)
	@ResponseBody
	public XheditorRoot xeupload(HttpServletRequest request,
			@RequestParam("filedata") MultipartFile file) throws Exception {

		XheditorRoot xheditorRoot = new XheditorRoot();

		UploadFileParameter up = new UploadFileParameter();
		up.setPathType(1);// 路径
		up.setRequest(request);// request
		up.setMultipartFile(file);
		up.setThumbWhether(false);// 是否生成缩略图
		UpLoadFileInfor fileInfor = FileUpload.imgFileUpLoad(up);

		String imgGetUrl = fileInfor.getXeDateFileName();

		xheditorRoot.setErr("");
		xheditorRoot.setMsg(imgGetUrl);

		return xheditorRoot;
	}

}
