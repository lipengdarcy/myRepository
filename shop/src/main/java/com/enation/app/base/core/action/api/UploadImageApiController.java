package com.enation.app.base.core.action.api;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.JsonResultUtil;

/**
 * 图片上传API
 * @author kanon 2015-9-22 version 1.1 添加注释
 */
@Controller
@Scope("prototype")
@RequestMapping("/api/base/uploadImage")
public class UploadImageApiController  {

	 	/**
	 	 *  上传图片
	 	 * @param image 图片
	 	 * @param imageFileName 图片名称
	 	 * @param subFolder 存放文件夹名称
	 	 * @return 上传成功返回： 图片地址，失败返回上传图片错误信息
	 	 */
		@ResponseBody
		@RequestMapping(value="/upload")
	 	public Object upload(MultipartFile image ,String  subFolder){
	 		try{
	 			if (image!=null) {
	 				if(!FileUtil.isAllowUpImg(image.getOriginalFilename())){
	 					return JsonResultUtil.getErrorJson("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
	 				}else{
	 					String fsImgPath =UploadUtil.upload(image, subFolder);
	 		 			return "{\"img\":\""+UploadUtil.replacePath(fsImgPath)+"\",\"fsimg\":\""+fsImgPath+"\"}";
	 				}
	 				
	 			}
	 			
	 			
	 		}catch(Throwable e){
	 			return JsonResultUtil.getErrorJson("上传出错"+e.getLocalizedMessage());
	 		}
			return JsonResultUtil.getErrorJson("请重试");
	 	}
		
		@ResponseBody
		@RequestMapping(value="/sd", produces =MediaType.APPLICATION_JSON_VALUE)
		public Object dsss(){
			return "asdd";
		}
}
