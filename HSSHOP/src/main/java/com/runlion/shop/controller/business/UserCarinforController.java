package com.runlion.shop.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspUsercarinfor;
import com.runlion.shop.service.BspUserCarService;
import com.runlion.shop.vo.SelUsercarinforVO;

/**
 * 用户车辆信息控制器
 * 
 * @author hsrj05
 *
 */
@Controller
@RequestMapping("/ucarinfor")
public class UserCarinforController {

	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspUserCarService bspUserCarService;

	/**
	 * 获取添加车辆信息的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addcarpage")
	public ModelAndView getAddCarPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("business/dealproduct/addCarInfor");
		return mv;
	}

	/**
	 * 添加用户车辆信息
	 * 
	 * @param httpSession
	 * @param userCarInfor
	 * @return
	 */
	@RequestMapping(value = "/addcarinfor")
	@ResponseBody
	public Map addUsercarinfor(HttpSession httpSession,
			BspUsercarinfor userCarInfor) {
		Map<String, Object> rsmap = new HashMap();

		// 获取用户的uid
		Integer uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		Integer utype = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UserType);
		// 获取用户角色
		if (uid == null) {
			rsmap.put("state", "faild");
			rsmap.put("msg", "您还没有登陆");
		} else {
			userCarInfor.setUserid(uid);
			userCarInfor.setUserroleid(utype);
			rsmap = bspUserCarService.addUserCarinfor(userCarInfor);
			rsmap.put("carinfor", userCarInfor);
		}

		return rsmap;
	}

	@RequestMapping(value = "/selcarinforlist")
	@ResponseBody
	public Map selUsercarinforList(HttpSession httpSession,
			SelUsercarinforVO selWordsVo) {
		Map<String, Object> rsmap = new HashMap();
		// 获取用户的uid
		Integer uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		selWordsVo.setUid(uid);
		List<BspUsercarinfor> carinforList = bspUserCarService
				.selUsercarList(selWordsVo);
		rsmap.put("state", "success");
		rsmap.put("carlist", carinforList);
		return rsmap;
	}
}
