package com.runlion.shop.controller.app.b2c;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.util.EncryptUtil;
import com.runlion.shop.entity.BspUsers;
import com.runlion.shop.entity.common.JsonResponseData;
import com.runlion.shop.service.UserService;
import com.runlion.shop.vo.app.b2c.B2cIndexVo;

@Controller
@RequestMapping("/b2capp/api-login")
public class B2cAppLoginController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private UserService userService;

	/**
	 * B2C首页接口
	 * 
	 * @param app
	 * @return JsonResponseData<List<BspNews>> 返回类型
	 * @throws Exception
	 */
	@RequestMapping(params = "method=toLogin")
	@ResponseBody
	public JsonResponseData<B2cIndexVo> appLogin(BspUsers u) {

		B2cIndexVo appVo = new B2cIndexVo();
		JsonResponseData<B2cIndexVo> app = new JsonResponseData<B2cIndexVo>();

		String enPwd = EncryptUtil.encrypt(u.getPassword());
		Map<String, Object> user = userService.authUser(u.getUsername(), enPwd);
		if (user == null) {
			app.setErrorMsg("帐号或密码错误，请重新输入！");
			app.setErrorNo("appLogin1001");
			app.setSuccess(false);
		} else {
			// 登录成功返回
			appVo.setMapStringObject(user);
			app.setSuccess(true);
		}

		app.setResult(appVo);
		return app;
	}
}
