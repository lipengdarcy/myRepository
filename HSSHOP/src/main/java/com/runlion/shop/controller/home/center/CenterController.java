package com.runlion.shop.controller.home.center;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.common.defaultval.AdminuserDefVal;
import com.runlion.shop.common.util.CookieHanler;
import com.runlion.shop.common.util.EncryptUtil;
import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.HtmlUtil;
import com.runlion.shop.common.util.HttpRequest;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.common.util.WebConfigHandler;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.controller.home.center.help.MailBinding;
import com.runlion.shop.controller.home.center.help.SendMail;
import com.runlion.shop.dao.BspCommentMapper;
import com.runlion.shop.dao.BspCreditlogsMapper;
import com.runlion.shop.dao.BspFavoritesMapper;
import com.runlion.shop.dao.BspInvoiceMapper;
import com.runlion.shop.dao.BspMailverificationMapper;
import com.runlion.shop.dao.BspOrderactionsMapper;
import com.runlion.shop.dao.BspOrderextMapper;
import com.runlion.shop.dao.BspOrderproductsMapper;
import com.runlion.shop.dao.BspOrdersMapper;
import com.runlion.shop.dao.BspProductsMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspShipaddressesMapper;
import com.runlion.shop.dao.BspUserdetailsMapper;
import com.runlion.shop.dao.BspUserranksMapper;
import com.runlion.shop.dao.BspUsersMapper;
import com.runlion.shop.dao.MessageMapper;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.entity.BspComment;
import com.runlion.shop.entity.BspCreditlogsExample;
import com.runlion.shop.entity.BspFavorites;
import com.runlion.shop.entity.BspFavoritesExample;
import com.runlion.shop.entity.BspMailverification;
import com.runlion.shop.entity.BspMailverificationExample;
import com.runlion.shop.entity.BspMessage;
import com.runlion.shop.entity.BspOrderactions;
import com.runlion.shop.entity.BspOrderactionsExample;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrderproductsExample;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspShipaddresses;
import com.runlion.shop.entity.BspShipaddressesExample;
import com.runlion.shop.entity.BspUserdetails;
import com.runlion.shop.entity.BspUserdetailsExample;
import com.runlion.shop.entity.BspUserranks;
import com.runlion.shop.entity.BspUsers;
import com.runlion.shop.entity.BspUsersExample;
import com.runlion.shop.entity.enums.OrderStateEnum;
import com.runlion.shop.service.BspCommentService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.OrderNoGenerator;
import com.runlion.shop.service.OrderService;
import com.runlion.shop.service.UserService;
import com.runlion.shop.service.business.BusinessService;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.CommentVO;
import com.runlion.shop.vo.MailConfigVO;

@Controller
@RequestMapping("/ucenter")
public class CenterController extends BaseController {
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BusinessService businessService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	BspUsersMapper bum;
	@Autowired
	BspCommentMapper bspCommentMapper;

	@Autowired
	BspUserranksMapper burm;

	@Autowired
	BspUserdetailsMapper budm;

	@Autowired
	BspRegionsMapper brm;

	@Autowired
	BspOrdersMapper bom;

	@Autowired
	BspOrderproductsMapper bopm;

	@Autowired
	BspOrderactionsMapper boam;

	@Autowired
	BspShipaddressesMapper bsam;

	@Autowired
	BspFavoritesMapper bfm;

	@Autowired
	BspProductsMapper bpm;

	@Autowired
	BspCreditlogsMapper bclm;

	@Autowired
	BspMailverificationMapper bmvm;

	@Autowired
	UserService userService;

	@Autowired
	BspOrderextMapper bspOrderextMapper;
	@Autowired
	BspInvoiceMapper bspInvoiceMapper;

	@Autowired
	MessageMapper messageMapper;
	@Autowired
	BspSaleaddressMapper bspSaleaddressMapper;
	@Autowired
	BspCommentService bspCommentService;

	// 加载菜单
	@RequestMapping()
	public String loadMenu(
			HttpServletRequest request,
			HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1", value = "userType") String userTypeParam,
			@RequestParam(required = false, defaultValue = "0", value = "navId") Integer navId,
			ModelMap m) {
		loggerinfo.info("加载用户类型信息");
		// session 中取不到
		// int userType = (Integer)
		// httpSession.getAttribute(ACCOUNT_SESSION_UserType);

		int userType = 11;
		if (EHCacheUtil.get("userType") != null)
			userType = (Integer) EHCacheUtil.get("userType");

		m.put("userType", userType);
		m.put("navId", navId);
		if (businessService.isFactoryUser(userType))
			m.put("isFactoryUser", 1);

		if (businessService.isStoreUser(userType))
			m.put("isStoreUser", 1);

		return "home/center/centermenu";
	}

	/**
	 * 用户信息
	 * */
	@RequestMapping("/userinfo")
	public ModelAndView userinfo(HttpServletRequest request,
			HttpSession httpSession) {
		loggerinfo.info("==========[CenterController userinfo] Start...");
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);

			BspUsers bu = bum.selectByPrimaryKey(uid);
			BspUserranks bur = new BspUserranks();
			BspRegions br = new BspRegions();
			BspUserdetails bud = new BspUserdetails();
			bud = budm.selectByPrimaryKey(uid);
			String cid = "";
			if (bud.getRegionid() != 0) {
				br = brm.selectByPrimaryKey(bud.getRegionid());
				cid = getCid(br, bud.getRegionid().toString());
			}
			Short userrid = bu.getUserrid().shortValue();
			if (bu.getUserrid().toString() != "") {
				bur = burm.selectByPrimaryKey(userrid);
			}

			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			mv.setViewName("home/center/userinfo");
			mv.addObject("userinfo", bu);
			mv.addObject("userdetails", bud);
			mv.addObject("regions", br);
			mv.addObject("userrank", bur);
			mv.addObject("formate", formate);
			mv.addObject("cid", cid);
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo.info("==========[CenterController userinfo] End...");
		return mv;
	}

	/**
	 * 修改用户信息
	 * */
	@RequestMapping("/edituser")
	@ResponseBody
	public Map<String, String> edituser(HttpServletRequest request,
			HttpSession httpSession, String userName, String nickName,
			String realName, String avatar, byte gender, String idCard,
			String bday, int regionId, String address, String bio,
			String email, String mobile) throws Exception {
		loggerinfo.info("==========[CenterController edituser] Start...");
		Map<String, String> result = new HashMap<String, String>();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		BspUserdetails bud = new BspUserdetails();
		BspUsers bu = new BspUsers();
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		bud.setUid(uid);
		bu.setUid(uid);
		bu.setNickname(nickName);
		bu.setEmail(email);
		bu.setMobile(mobile);
		bud.setRealname(realName);
		bu.setAvatar(avatar);
		bud.setGender(gender);
		bud.setIdcard(idCard);
		bud.setBday(formate.parse(bday));
		bud.setRegionid(regionId);
		bud.setAddress(address);
		bud.setBio(bio);

		BspUsers oldbu = bum.selectByPrimaryKey(uid);
		if (!StringHandler.isEmail(email)) {
			result.put("state", "full");
			result.put("content", "邮箱格式有错误！");
		} else if (oldbu.getVerifymobile() == 0
				&& !StringHandler.isMobile(mobile)) {
			result.put("state", "full");
			result.put("content", "请填写正确的手机号码！");
		} else {

			// 如果号码改变则需要将手机的绑定状态重置

			if (oldbu != null) {
				String telNum = oldbu.getMobile();
				if (telNum != null && !telNum.equals(mobile)) {
					bu.setVerifymobile(Byte.valueOf("0"));
				}
			}

			BspUserdetailsExample bude = new BspUserdetailsExample();
			bude.or().andUidEqualTo(uid);
			budm.updateByExampleSelective(bud, bude);
			BspUsersExample bue = new BspUsersExample();
			bue.or().andUidEqualTo(uid);

			bum.updateByExampleSelective(bu, bue);
			result.put("state", "success");
			result.put("content", "修改成功！");
		}
		loggerinfo.info("==========[CenterController edituser] End...");
		return result;
	}

	/**
	 * 账户安全
	 * */
	@RequestMapping("/safeinfo")
	public ModelAndView safeinfo(HttpServletRequest request,
			HttpSession httpSession) {
		loggerinfo.info("==========[CenterController safeinfo] Start...");
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			BspUsers bu = bum.selectByPrimaryKey(uid);
			String mb = bu.getMobile();
			String mobile = "";
			if (mb == null || mb.length() != 11)
				mobile = "";
			else {
				StringBuffer s = new StringBuffer(mb);
				s.replace(3, 7, "****");
				mobile = s.toString();
			}
			mv.setViewName("home/center/safeinfo");
			mv.addObject("mobile", mobile);
			mv.addObject("user", bu);
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo.info("==========[CenterController safeinfo] End...");
		return mv;
	}

	/**
	 * 账户安全相关修改
	 * */
	@RequestMapping("/safeverify")
	public ModelAndView safeverify(HttpServletRequest request,
			HttpSession httpSession, String act, String telnum) {
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			BspUsers bu = bum.selectByPrimaryKey(uid);
			mv.addObject("user", bu);
			if (act != null) {
				if (act.equals("bindMobile")) {
					if (telnum != null) {
						mv.addObject("telnum", telnum);
					}
					mv.setViewName("home/center/bindMobile");
				} else if (act.equals("updateMobile")) {
					if (telnum != null) {
						mv.addObject("telnum", telnum);
					}
					mv.setViewName("home/center/upBindMobile");
				} else if (act.equals("updateEmail")) {
					mv.setViewName("home/center/safeverify");
				}
			} else {

			}

		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		return mv;
	}

	/**
	 * 需要绑定的手机号发送验证码
	 * 
	 * @throws Exception
	 * */
	@RequestMapping("/sendVerifyTelcode")
	@ResponseBody
	public Map<String, String> sendVerifyTelcode(HttpServletRequest request,
			HttpSession httpSession, String telnum) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (uid > 0) {
			if (!StringHandler.isMobile(telnum)) {
				result.put("state", "fail");
				result.put("msg", "请填写正确的手机号码！");
			} else {

				// 随机生成一个验证码，
				String verificationCode = OrderNoGenerator
						.getVerificationCode();
				String msg = "您的红狮水泥商城手机绑定验证验证码为:" + verificationCode
						+ "。十分钟后过期。";
				// 发送手机验证码
				String rs = HttpRequest.sendMsm(telnum, msg);
				// 如果发送成功则将发送人的id 验证码 发送时间 过期时间存入数据库
				if (rs.equals("1")) {
					// 十分钟后过期
					long currentTime = System.currentTimeMillis() + 10 * 60 * 1000;
					Date date = new Date(currentTime);
					DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
					BspMessage bspMessage = new BspMessage();
					bspMessage.setMobile(telnum);
					bspMessage.setMessage(msg);
					bspMessage.setState("0");
					bspMessage.setValid(df.format(date));
					bspMessage.setCode(verificationCode);
					// 在订单号节点存储用户的id形式为u:123
					bspMessage.setOrderNumber(AdminuserDefVal.TEL_USERID_PREFIX
							+ uid);
					orderService.insertMessageAll(bspMessage);
					// orderService.updateOrderVerificationByOrder(orderNum,
					// verificationCode);
					result.put("state", "success");

				} else {
					result.put("state", "fail");
					result.put("msg", "短信发送失败，请单击按钮重新发送。");
				}
			}

		} else {
			result.put("state", "fail");
			result.put("msg", "您还没有登陆。请登录。");
		}

		return result;
	}

	/**
	 * 进行手机号的绑定
	 * */
	@RequestMapping("/bindTelnum")
	@ResponseBody
	public Map<String, String> bindTelnum(HttpServletRequest request,
			HttpSession httpSession, String telnum, String verCode) {
		Map<String, String> result = new HashMap<String, String>();
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (uid > 0) {
			// 进行手机号的绑定
			List<BspMessage> msgList = orderService.getUserMessageValidity(
					AdminuserDefVal.TEL_USERID_PREFIX + uid, telnum, verCode);
			if (msgList.size() > 0) {
				BspMessage msg = msgList.get(0);
				String savedCode = msg.getCode();
				if (verCode != null && verCode.equals(savedCode)) {
					long currentTime = System.currentTimeMillis();
					Date date = new Date(currentTime);
					DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
					String sdate = df.format(date);
					Long lcur = Long.parseLong(sdate);
					String valid = msg.getValid();
					Long lvalid = Long.parseLong(valid);

					// 验证成功
					if (lvalid >= lcur) {
						// 修改用户表的手机号和手机号的验证状态
						BspUsers userInfor = new BspUsers();
						userInfor.setUid(uid);
						userInfor.setVerifymobile(Byte.valueOf("1"));
						userInfor.setMobile(telnum);
						userService.saveUpUserInfor(userInfor);
						result.put("state", "success");
						result.put("msg", "手机绑定成功。");
						// 验证码已过期
					} else {
						result.put("state", "fail");
						result.put("msg", "验证码已过期。");
					}

					// 验证码不正确
				} else {
					result.put("state", "fail");
					result.put("msg", "验证码不正确。");
				}
			} else {
				result.put("state", "fail");
				result.put("msg", "验证码不正确。");
			}
			// 用户未登陆，不进行验证
		} else {
			result.put("state", "fail");
			result.put("msg", "您还没有登陆。请登录。");
		}

		return result;
	}

	/**
	 * 向安全邮箱发送验证码
	 * */
	@RequestMapping("/sendVerifyEmail")
	@ResponseBody
	public Map<String, String> sendVerifyEmail(HttpServletRequest request,
			HttpSession httpSession, String email) {
		Map<String, String> result = new HashMap<String, String>();
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		SendMail sm = new SendMail();
		MailBinding mb = new MailBinding();
		String verification = mb.generateCode();

		MailConfigVO configVo = new MailConfigVO();
		String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/config/email.config");
		WebConfigHandler.getConfigInfor(configVo, path);
		BspMailverificationExample bmve = new BspMailverificationExample();
		bmve.createCriteria().andUidEqualTo(uid).andEmailEqualTo(email)
				.andIdentEqualTo(1);
		List<BspMailverification> bmvlist = bmvm.selectByExample(bmve);
		if (bmvlist.size() > 0) {
			for (BspMailverification bmvf : bmvlist) {
				BspMailverification bmv2 = new BspMailverification();
				bmv2.setIdent(0);
				BspMailverificationExample bmve2 = new BspMailverificationExample();
				bmve2.or().andMidEqualTo(bmvf.getMid());
				bmvm.updateByExampleSelective(bmv2, bmve2);
			}
		}
		sm.sendMail(verification, email, configVo);
		BspMailverification bmv = new BspMailverification();
		bmv.setUid(uid);
		bmv.setEmail(email);
		bmv.setVerification(verification);
		bmv.setIdent(1);
		bmvm.insert(bmv);
		result.put("state", "success");
		return result;
	}

	/**
	 * 身份认证
	 * */
	@RequestMapping("/verifyEmail")
	@ResponseBody
	public Map<String, String> verifyEmail(HttpServletRequest request,
			HttpSession httpSession, String email, String code) {
		Map<String, String> result = new HashMap<String, String>();
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		BspMailverificationExample bmve = new BspMailverificationExample();
		bmve.createCriteria().andEmailEqualTo(email)
				.andVerificationEqualTo(code).andUidEqualTo(uid)
				.andIdentEqualTo(1);
		List<BspMailverification> bmvlist = bmvm.selectByExample(bmve);
		if (bmvlist.size() > 0) {
			result.put("state", "success");
			BspMailverification bmv = new BspMailverification();
			BspMailverificationExample bmve2 = new BspMailverificationExample();
			bmve2.or().andMidEqualTo(bmvlist.get(0).getMid());
			bmv.setIdent(0);
			bmvm.updateByExampleSelective(bmv, bmve2);
			BspUsers userInfor = new BspUsers();
			userInfor.setUid(uid);
			userInfor.setVerifyemail(Byte.valueOf("1"));
			userService.saveUpUserInfor(userInfor);
		} else {
			result.put("state", "error");
		}
		return result;
	}

	/**
	 * 认证后去修改密码页面
	 * */
	@RequestMapping("/goeditpassword")
	public ModelAndView goeditpassword(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			mv.setViewName("home/center/safeeditpassword");
			mv.addObject("uid", uid);
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		return mv;
	}

	/**
	 * 认证后修改密码
	 * */
	@RequestMapping("/editpassword")
	@ResponseBody
	public Map<String, String> editpassword(HttpServletRequest request,
			HttpSession httpSession, String password, String newpassword) {
		Map<String, String> map = new HashMap<String, String>();
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		BspUsersExample bue = new BspUsersExample();
		bue.createCriteria().andUidEqualTo(uid)
				.andPasswordEqualTo(EncryptUtil.encrypt(password));
		List<BspUsers> bulist = bum.selectByExample(bue);
		if (bulist.size() > 0) {
			BspUsersExample bue2 = new BspUsersExample();
			bue2.or().andUidEqualTo(uid);
			BspUsers bu = new BspUsers();
			bu.setPassword(EncryptUtil.encrypt(newpassword));
			bum.updateByExampleSelective(bu, bue2);
			map.put("state", "success");
		} else {
			map.put("state", "error");
		}
		return map;
	}

	/**
	 * 去修改完成页面
	 * */
	@RequestMapping("/goeditover")
	public ModelAndView goeditover(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			mv.setViewName("home/center/goeditover");
			mv.addObject("uid", uid);
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		return mv;
	}

	/**
	 * 修改安全电话
	 * */
	@RequestMapping("/editmobile")
	@ResponseBody
	public Map<String, String> editmobile(HttpServletRequest request,
			HttpSession httpSession, String oldmobile, String nowmobile) {
		loggerinfo.info("==========[CenterController editmobile] Start...");
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		Map<String, String> result = new HashMap<String, String>();
		BspUsers bu = bum.selectByPrimaryKey(uid);
		if (oldmobile.equals(bu.getMobile())) {
			BspUsers bur = new BspUsers();
			bur.setMobile(nowmobile);
			BspUsersExample bue = new BspUsersExample();
			bue.or().andUidEqualTo(uid);
			bum.updateByExampleSelective(bur, bue);
			StringBuffer s = new StringBuffer(nowmobile);
			s.replace(3, 7, "****");
			String mobile = s.toString();
			result.put("mobile", mobile);
			result.put("content", "修改成功！");
		} else {
			result.put("content", "手机号码错误！");
		}
		loggerinfo.info("==========[CenterController editmobile] End...");
		return result;
	}

	/**
	 * 订单列表
	 * */
	@RequestMapping("/orderlist")
	public ModelAndView orderlist(
			HttpServletRequest request,
			HttpSession httpSession,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		loggerinfo.info("==========[CenterController orderlist] Start...");
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			int startNum = (pageNumber - 1) * pageSize;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("START_NUM", startNum);
			map.put("LIMIT_NUM", pageSize);
			map.put("UID", uid);
			int cnt = bom.countOrderPage(map);
			List<BspOrders> orderlist = bom.selectOrderPage(map);

			if (orderlist.size() > 0) {
				for (BspOrders bo : orderlist) {

					BspOrderproductsExample bope = new BspOrderproductsExample();
					bope.or().andOidEqualTo(bo.getOid());
					List<BspOrderproducts> bop = new ArrayList<BspOrderproducts>();
					bop = bopm.selectByExample(bope);

					bo.setBoplist(bop);
					// begin 如果快照文件不存在，则再次生成
					for (int i = 0; i < bop.size(); i++) {

						BspOrderproducts p = bop.get(i);
						// begin 如果产品下架不存在，则不生成
						BspAdminuser bspAdminuser = (BspAdminuser) request
								.getSession().getAttribute("adminuser");
						String areaId = CookieHanler
								.getLastAreaFromCookie(request);
						BspProducts entity = bspProductsService
								.selectByPrimaryKeyState(p.getPid(),
										bspAdminuser, Integer.valueOf(areaId));
						if (entity == null) {
							p.setIsExists(0);
						}
						// end
					}
					// end
				}
			}
			SimpleDateFormat forday = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fortime = new SimpleDateFormat("HH:mm:ss");
			mv.setViewName("home/center/order");
			mv.addObject("orderlist", orderlist);
			mv.addObject("forday", forday);
			mv.addObject("fortime", fortime);
			String newPath = "orderlist.do?pageNumber=";
			mv.addObject("pageDiv", StringHandler.generatePageDiv(cnt,
					pageSize, pageNumber, newPath));
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo.info("==========[CenterController orderlist] End...");
		return mv;
	}

	/**
	 * 订单详情
	 * */
	@RequestMapping("/orderinfo")
	public ModelAndView orderinfo(HttpServletRequest request, int oid) {
		loggerinfo.info("==========[CenterController orderinfo:" + oid
				+ "] Start...");
		ModelAndView mv = new ModelAndView();

		Map map = orderService.queryOrderInforById(oid);
		mv.addObject("orderinfo", map.get("orderinfo"));
		mv.addObject("regions", map.get("regions"));
		mv.addObject("boalist", map.get("boalist"));
		mv.addObject("formate", map.get("formate"));
		mv.addObject("boplist", map.get("boplist"));
		mv.addObject("orderext", map.get("orderext"));
		mv.addObject("invoice", map.get("invoice"));

		mv.addObject("sender", map.get("sender"));
		mv.addObject("payorder", map.get("payorder"));

		mv.addObject("reason", map.get("reason"));// 订单取消原因
		mv.addObject("cancelpicList", map.get("cancelpicList"));// 订单取消图片

		mv.addObject("saleraddr", map.get("saleraddr"));

		BspOrders order = (BspOrders) map.get("orderinfo");
		mv.addObject("orderStateName",
				EnumsUtil.valueOf(OrderStateEnum.class, order.getOrderstate())
						.getDesc());

		mv.setViewName("home/center/orderinfo");
		// mv.addObject("orderinfo", b);
		// mv.addObject("regions", br);
		// mv.addObject("boalist", boalist);
		// mv.addObject("formate", formate);
		// mv.addObject("boplist", boplist);
		// mv.addObject("orderext", orderext);
		// mv.addObject("invoice", invoice);
		// mv.addObject("saleraddr", saleraddr);

		loggerinfo.info("==========[CenterController orderinfo:" + oid
				+ "] End...");
		return mv;
	}

	/**
	 * 评价订单详情
	 * */
	@RequestMapping("/commenorderInfo")
	public ModelAndView commenorderInfo(HttpSession session, int oid) {
		loggerinfo.info("==========[CenterController commenorderInfo:" + oid
				+ "] Start...");

		ModelAndView mv = new ModelAndView();
		Integer uid = (Integer) session
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (uid == null) {
			mv.setViewName("home/login/login");
			return mv;
		}
		BspOrders b = bom.selectByPrimaryKey(oid);
		// 订单跟踪
		BspOrderactionsExample boae = new BspOrderactionsExample();
		boae.or().andOidEqualTo(oid);
		List<BspOrderactions> boalist = boam.selectByExample(boae);
		// 商品清单
		BspOrderproductsExample bope = new BspOrderproductsExample();
		bope.or().andOidEqualTo(oid);
		List<BspOrderproducts> boplist = new ArrayList<BspOrderproducts>();
		boplist = bopm.selectByExample(bope);
		BspRegions br = brm.selectByPrimaryKey(b.getRegionid());
		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mv.setViewName("home/center/commenorder");
		mv.addObject("orderinfo", b);
		mv.addObject("regions", br);
		mv.addObject("boalist", boalist);
		mv.addObject("formate", formate);
		mv.addObject("boplist", boplist);
		loggerinfo.info("==========[CenterController orderinfo:" + oid
				+ "] End...");
		return mv;
	}

	/**
	 * 评价
	 * */
	@RequestMapping("/reviewProduct")
	@ResponseBody
	public int reviewProduct(HttpServletRequest request, HttpSession session,
			int uid, int pid, int recordid, String content, Double score,
			int oid) {
		loggerinfo.info("==========[CenterController reviewProduct:" + pid
				+ "] Start...");
		Integer sessionid = (Integer) session
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (sessionid == null) {
			return 0;
		}
		BspOrderproducts product = bopm.selectByPrimaryKey(recordid);
		product.setIsreview((byte) 1);
		bopm.updateByPrimaryKey(product);
		BspComment record = new BspComment();
		// 去除html标签
		content = HtmlUtil.getTextFromHtml(content);
		System.out.println(content);
		record.setComment(content);
		record.setPid(pid);
		record.setUid(uid);
		record.setScore(score);
		record.setCreatdate(new Date());
		record.setOid(oid);
		record.setIp(HttpRequest.getRemortIP(request));
		record.setStatus(0);
		System.out.println(HttpRequest.getRemortIP(request));
		bspCommentMapper.insert(record);
		loggerinfo.info("==========[CenterController reviewProduct:" + pid
				+ "] End...");
		return 1;
	}

	/**
	 * 取消订单
	 * 
	 * @throws Exception
	 * */
	@RequestMapping("/cancelorder")
	@ResponseBody
	public Map<String, String> cancelorder(HttpSession session, int oid)
			throws Exception {
		loggerinfo.info("==========[CenterController orderedit:" + oid
				+ "] Start...");

		Map<String, String> result = new HashMap<String, String>();
		boolean flag = false;
		Integer userId = (Integer) session
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (userId != null) {
			flag = orderService.processOrder(userId, oid,
					(byte) OrderStateEnum.Cancel.ordinal(), (short) 0, null,
					null);
			if (flag) {
				result.put("state", "success");
				result.put("content", oid + "");
			} else {
				result.put("state", "error");
				result.put("content", "取消订单失败");
			}
		} else {
			result.put("state", "NOT_LOGIN");
			result.put("content", "请先登录");
		}

		loggerinfo.info("==========[CenterController orderedit:" + oid
				+ "] End...");
		return result;
	}

	/**
	 * 收货地址
	 * */
	@RequestMapping("/shipaddresslist")
	public ModelAndView shipaddresslist(HttpServletRequest request,
			HttpSession httpSession) {
		loggerinfo
				.info("==========[CenterController shipaddresslist] Start...");
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			BspShipaddressesExample bsae = new BspShipaddressesExample();
			bsae.or().andUidEqualTo(uid);
			List<BspShipaddresses> bsalist = bsam.selectByExample(bsae);
			int size = bsalist.size();
			if (size > 0) {
				for (BspShipaddresses bsa : bsalist) {
					bsa.setRegions(brm.selectByPrimaryKey(bsa.getRegionid()));
				}
			}
			mv.setViewName("home/center/shipaddresslist");
			mv.addObject("bsalist", bsalist);
			mv.addObject("size", size);
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo.info("==========[CenterController shipaddresslist] End...");
		return mv;
	}

	/**
	 * 设置默认地址
	 * */
	@RequestMapping("/setdefaultshipaddress")
	@ResponseBody
	public Map<String, String> setdefaultshipaddress(
			HttpServletRequest request, HttpSession httpSession, int saId) {
		loggerinfo
				.info("==========[CenterController setdefaultshipaddress:设置默认地址] Start...");
		Map<String, String> result = new HashMap<String, String>();
		// 修改其它项
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		BspShipaddresses record2 = new BspShipaddresses();
		record2.setIsdefault((byte) 0);
		BspShipaddressesExample bsae2 = new BspShipaddressesExample();
		bsae2.or().andUidEqualTo(uid);
		bsam.updateByExampleSelective(record2, bsae2);
		// 设置默认的地址
		BspShipaddresses record1 = new BspShipaddresses();
		record1.setIsdefault((byte) 1);
		BspShipaddressesExample bsae1 = new BspShipaddressesExample();
		bsae1.or().andSaidEqualTo(saId);
		bsam.updateByExampleSelective(record1, bsae1);

		result.put("state", "success");
		result.put("content", saId + "");

		loggerinfo
				.info("==========[CenterController setdefaultshipaddress:设置默认地址] End...");
		return result;
	}

	/**
	 * 新增收货地址
	 * */
	@RequestMapping("/addshipaddress")
	@ResponseBody
	public Map<String, String> addshipaddress(HttpServletRequest request,
			HttpSession httpSession, String alias, String consignee,
			String mobile, String phone, String email, String zipcode,
			Integer regionId, String address, Byte isDefault) {
		loggerinfo.info("==========[CenterController addshipaddress] Start...");
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		Map<String, String> result = new HashMap<String, String>();
		if (!StringHandler.isMobile(mobile)) {
			result.put("state", "full");
			result.put("msg", "请填写正确的手机号码！");
		} else if (!StringHandler.isEmpty(email)
				&& !StringHandler.isEmail(email)) {
			result.put("state", "full");
			result.put("msg", "邮箱格式有错误！");
		} else {
			BspShipaddressesExample bsae = new BspShipaddressesExample();
			bsae.or().andUidEqualTo(uid);
			int count = bsam.countByExample(bsae);
			if (count < 12) {
				if (isDefault == 1) {
					BspShipaddresses record2 = new BspShipaddresses();
					record2.setIsdefault((byte) 0);
					BspShipaddressesExample bsae2 = new BspShipaddressesExample();
					bsae2.or().andUidEqualTo(uid);
					bsam.updateByExampleSelective(record2, bsae2);
				}
				BspShipaddresses bsa = new BspShipaddresses();
				bsa.setUid(uid);
				bsa.setAlias(alias);
				bsa.setConsignee(consignee);
				bsa.setMobile(mobile);
				bsa.setPhone(phone);
				bsa.setEmail(email);
				bsa.setZipcode(zipcode);
				bsa.setRegionid(regionId);
				bsa.setAddress(address);
				bsa.setIsdefault(isDefault);
				bsam.insertSelective(bsa);
				result.put("state", "success");
				result.put("saId", bsa.getSaid() + "");
			} else {
				result.put("state", "full");
				result.put("msg", "最多只能添加12个收货地址！");
			}
		}
		loggerinfo.info("==========[CenterController addshipaddress] End...");
		return result;
	}

	/**
	 * 获取要编辑的地址信息
	 * */
	@RequestMapping("/shipaddressinfo")
	public ModelAndView shipaddressinfo(Integer saId) {
		ModelAndView mv = new ModelAndView();
		BspShipaddresses bsa = bsam.selectByPrimaryKey(saId);
		BspRegions br = brm.selectByPrimaryKey(bsa.getRegionid());
		String cid = getCid(br, bsa.getRegionid().toString());
		mv.setViewName("home/center/shipaddressedit");
		mv.addObject("shipaddress", bsa);
		mv.addObject("cid", cid);
		return mv;
	}

	/**
	 * 编辑收货地址
	 * */
	@RequestMapping("/editshipaddress")
	@ResponseBody
	public Map<String, String> editshipaddress(HttpServletRequest request,
			HttpSession httpSession, Integer said, String alias,
			String consignee, String mobile, String phone, String email,
			String zipcode, Integer regionId, String address, Byte isDefault) {
		loggerinfo
				.info("==========[CenterController editshipaddress] Start...");
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		Map<String, String> result = new HashMap<String, String>();
		if (!StringHandler.isMobile(mobile)) {
			result.put("state", "full");
			result.put("msg", "请填写正确的手机号码！");
		} else if (!StringHandler.isEmpty(email)
				&& !StringHandler.isEmail(email)) {
			result.put("state", "full");
			result.put("msg", "邮箱格式有错误！");
		} else {

			BspShipaddresses bsa = new BspShipaddresses();
			if (isDefault == 1) {
				BspShipaddresses record2 = new BspShipaddresses();
				record2.setIsdefault((byte) 0);
				BspShipaddressesExample bsae2 = new BspShipaddressesExample();
				bsae2.or().andUidEqualTo(uid);
				bsam.updateByExampleSelective(record2, bsae2);
			}
			bsa.setSaid(said);
			bsa.setAlias(alias);
			bsa.setConsignee(consignee);
			bsa.setMobile(mobile);
			bsa.setPhone(phone);
			bsa.setEmail(email);
			bsa.setZipcode(zipcode);
			bsa.setRegionid(regionId);
			bsa.setAddress(address);
			bsa.setIsdefault(isDefault);
			BspShipaddressesExample bsae = new BspShipaddressesExample();
			bsae.or().andSaidEqualTo(said);
			bsam.updateByExampleSelective(bsa, bsae);
			result.put("state", "success");
		}
		loggerinfo.info("==========[CenterController editshipaddress] End...");
		return result;
	}

	/**
	 * 删除收货地址
	 * */
	@RequestMapping("/delshipaddress")
	@ResponseBody
	public Map<String, String> delshipaddress(Integer saId) {
		loggerinfo.info("==========[CenterController delshipaddress] Start...");
		Map<String, String> result = new HashMap<String, String>();
		bsam.deleteByPrimaryKey(saId);
		result.put("state", "success");
		result.put("content", saId + "");
		loggerinfo.info("==========[CenterController delshipaddress] End...");
		return result;
	}

	/**
	 * 收藏商品列表
	 * */
	@RequestMapping("/favoriteproductlist")
	public ModelAndView favoriteproductlist(
			HttpServletRequest request,
			HttpSession httpSession,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		loggerinfo
				.info("==========[CenterController favoriteproductlist] Start...");
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			int startNum = (pageNumber - 1) * pageSize;
			BspFavoritesExample bfe = new BspFavoritesExample();
			bfe.createCriteria().andUidEqualTo(uid).andStateEqualTo((byte) 0);
			int cnt = bfm.countByExample(bfe);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("START_NUM", startNum);
			map.put("LIMIT_NUM", pageSize);
			map.put("UID", uid);
			List<BspFavorites> bflist = bfm.selectFavoritesPage(map);
			int size = bflist.size();
			if (size > 0) {
				for (BspFavorites bf : bflist) {
					BspProducts bp = new BspProducts();
					bp = bpm.selectByPrimaryKey(bf.getPid());
					bf.setProduct(bp);
				}
			}
			mv.setViewName("home/center/favoriteproductlist");
			mv.addObject("bflist", bflist);
			mv.addObject("size", size);
			String newPath = "favoriteproductlist.do?pageNumber=";
			mv.addObject("pageDiv", StringHandler.generatePageDiv(cnt,
					pageSize, pageNumber, newPath));
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo
				.info("==========[CenterController favoriteproductlist] End...");
		return mv;
	}

	/**
	 * 取消收藏商品
	 * */
	@RequestMapping("/cancelfavoriteproduct")
	@ResponseBody
	public Map<String, String> cancelfavoriteproduct(int id) {

		Map<String, String> result = new HashMap<String, String>();

		BspFavorites record = new BspFavorites();
		record.setRecordid(id);
		record.setState((byte) 1);
		bfm.updateByPrimaryKeySelective(record);
		result.put("state", "success");
		result.put("content", "取消成功！");

		return result;
	}

	/**
	 * 删除收藏商品
	 * */
	@RequestMapping("/delfavoriteproduct")
	@ResponseBody
	public Map<String, String> delfavoriteproduct(int recordid, String pid) {
		loggerinfo
				.info("==========[CenterController delfavoriteproduct] Start...");
		Map<String, String> result = new HashMap<String, String>();
		bfm.deleteByPrimaryKey(recordid);
		result.put("state", "success");
		result.put("content", pid);
		loggerinfo
				.info("==========[CenterController delfavoriteproduct] End...");
		return result;
	}

	/**
	 * 我的预存款
	 * */
	@RequestMapping("/depositlist")
	public ModelAndView depositlist(
			HttpServletRequest request,
			HttpSession httpSession,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		loggerinfo.info("==========[CenterController depositlist] Start...");
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			BspUsers bu = bum.selectByPrimaryKey(uid);

			int startNum = (pageNumber - 1) * pageSize;
			BspCreditlogsExample bcle = new BspCreditlogsExample();
			bcle.or().andUidEqualTo(uid);
			int cnt = bclm.countByExample(bcle);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("START_NUM", startNum);
			map.put("LIMIT_NUM", pageSize);
			map.put("UID", uid);
			List<Map<String, Object>> bcllist = bclm.selectCreditlogsPage(map);
			SimpleDateFormat formate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			mv.setViewName("home/center/deposit");
			mv.addObject("formate", formate);
			mv.addObject("bcllist", bcllist);
			mv.addObject("bu", bu);
			String newPath = "depositlist.do?pageNumber=";
			mv.addObject("pageDiv", StringHandler.generatePageDiv(cnt,
					pageSize, pageNumber, newPath));
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo.info("==========[CenterController depositlist] End...");
		return mv;
	}

	/**
	 * 短信重发
	 * 
	 * @throws Exception
	 * */
	@RequestMapping("/repeatMessage")
	@ResponseBody
	public Map<String, String> repeatMessage(Integer orderid,
			HttpServletRequest request) throws Exception {
		loggerinfo
				.info("==========[CenterController delfavoriteproduct] Start...");
		Map<String, String> result = new HashMap<String, String>();
		String LOGINNAME = (String) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_LOGINNAME);
		boolean isOk = orderService.sendOrderMsg(orderid, LOGINNAME);
		if (!isOk) {// 短信发送失败的做法
			result.put("state", "failed");
		} else {
			result.put("state", "success");
		}

		loggerinfo
				.info("==========[CenterController delfavoriteproduct] End...");
		return result;
	}

	@RequestMapping("/commentlist")
	public ModelAndView commentlist(
			HttpServletRequest request,
			HttpSession httpSession,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		ModelAndView mv = new ModelAndView();
		try {
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			int startNum = (pageNumber - 1) * pageSize;

			List<CommentVO> commentlist = bspCommentService.getVOList(startNum,
					pageSize, uid);
			/*
			 * BspCommentExample e = new BspCommentExample();
			 * e.createCriteria().andUidEqualTo(uid); List<BspComment>
			 * commentlist = bspCommentMapper.selectByExample(e);
			 */

			int cnt = bspCommentService.countVOList(uid);
			SimpleDateFormat forday = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fortime = new SimpleDateFormat("HH:mm:ss");
			mv.addObject("forday", forday);
			mv.addObject("fortime", fortime);
			mv.setViewName("home/center/comment");
			mv.addObject("status", "success");
			mv.addObject("commentlist", commentlist);
			mv.addObject("numPerPage", pageSize);
			String newPath = "commentlist.do?pageNumber=";
			mv.addObject("pageDiv", StringHandler.generatePageDiv(cnt,
					pageSize, pageNumber, newPath));
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return mv;
	}

	/**
	 * 使用订单号和验证码验证订单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeOrderState")
	@ResponseBody
	public Map changeOrderState(Integer orderid, Byte state, String remarks,
			HttpSession httpSession) throws Exception {
		Integer uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		Map<String, Object> map = new HashMap();
		boolean isok = orderService.processOrder(uid, orderid, state, uid,
				remarks, "");
		if (isok) {
			map.put("state", "success");

		} else {
			map.put("state", "failed");
			map.put("errmsg", "验证出错！请联系管理人员！");
		}
		return map;
	}

	/**
	 * 商户的待处理订单列表
	 * */
	@RequestMapping("/veforderlist")
	public ModelAndView veforderlist(
			HttpServletRequest request,
			HttpSession httpSession,
			Integer roleid,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		loggerinfo.info("==========[CenterController orderlist] Start...");
		ModelAndView mv = new ModelAndView();
		try {
			// 这里获取客户关联的门户或者工厂的id
			int uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			int utype = businessService.getUserType(uid);
			// 获取工厂或者门店的id
			int sfId = businessService.getStoreId(uid, roleid);
			int startNum = (pageNumber - 1) * pageSize;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("START_NUM", startNum);
			map.put("LIMIT_NUM", pageSize);
			map.put("SALER_ID", sfId);// 这里应该是工厂或者门店的id
			int cnt = bom.countSalerOrderPage(map);
			List<BspOrders> orderlist = bom.selectSalerOrderPage(map);

			if (orderlist.size() > 0) {
				for (BspOrders bo : orderlist) {

					BspOrderproductsExample bope = new BspOrderproductsExample();
					bope.or().andOidEqualTo(bo.getOid());
					List<BspOrderproducts> bop = new ArrayList<BspOrderproducts>();
					bop = bopm.selectByExample(bope);

					bo.setBoplist(bop);
					// begin 如果快照文件不存在，则再次生成
					for (int i = 0; i < bop.size(); i++) {

						BspOrderproducts p = bop.get(i);
						// begin 如果产品下架不存在，则不生成
						BspAdminuser bspAdminuser = (BspAdminuser) request
								.getSession().getAttribute("adminuser");
						String areaId = CookieHanler
								.getLastAreaFromCookie(request);
						BspProducts entity = bspProductsService
								.selectByPrimaryKeyState(p.getPid(),
										bspAdminuser, Integer.valueOf(areaId));
						if (entity == null) {
							p.setIsExists(0);
						}
						// end
					}
					// end
				}
			}
			int userType = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UserType);
			if (businessService.isFactoryUser(userType))
				mv.addObject("isFactoryUser", 1);
			if (businessService.isStoreUser(userType))
				mv.addObject("isStoreUser", 1);

			mv.addObject("userType", userType);
			SimpleDateFormat forday = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fortime = new SimpleDateFormat("HH:mm:ss");
			mv.setViewName("home/center/vefOrder");
			mv.addObject("orderlist", orderlist);
			mv.addObject("forday", forday);
			mv.addObject("fortime", fortime);
			String newPath = "orderlist.do?pageNumber=";
			mv.addObject("pageDiv", StringHandler.generatePageDiv(cnt,
					pageSize, pageNumber, newPath));
		} catch (Exception e) {
			loggerinfo.info(e.toString());
			mv.setViewName("home/login/login");
		}
		loggerinfo.info("==========[CenterController orderlist] End...");
		return mv;
	}

	/**
	 * 判断发送信息
	 * */
	@RequestMapping("/verifyMessage")
	@ResponseBody
	public Map<String, String> verifyMessage(String telnum) throws Exception {
		loggerinfo.info("==========[CenterController verifyMessage] Start...");
		Map<String, String> result = new HashMap<String, String>();
		if (!StringHandler.isMobile(telnum)) {
			result.put("state", "full");
			result.put("content", "请填写正确的手机号码！");
		} else {

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			long currentTime = System.currentTimeMillis();
			Date end = new Date(currentTime);
			// 相同手机号，相同内容，10分钟内不能超过3条
			long second = end.getTime() - 10 * 1000 * 60;// 10分钟
			String d = format.format(second);
			Date start = format.parse(d);
			Map<String, Object> par = new HashMap<String, Object>();
			par.put("start", start);
			par.put("end", end);
			par.put("mobile", telnum);
			par.put("key", "手机绑定");
			List<Map<String, Object>> same = messageMapper
					.countMessagebyphone(par);
			Long countSame = (long) 0;
			if (same != null && same.size() == 1) {
				countSame = (Long) same.get(0).get("num");
			}
			result.put("state", "success");
			result.put("countSame", countSame + "");
			// result.put("countDif", countDif + "");
		}
		loggerinfo.info("==========[CenterController verifyMessage] End...");
		return result;
	}

}
