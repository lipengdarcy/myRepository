package com.runlion.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runlion.shop.dao.BspUsercarinforMapper;
import com.runlion.shop.dao.CusUsercarinforMapper;
import com.runlion.shop.entity.BspUsercarinfor;
import com.runlion.shop.entity.BspUsercarinforExample;
import com.runlion.shop.vo.SelUsercarinforVO;

@Service
public class BspUserCarService {

	@Autowired
	private BspUsercarinforMapper bspUsercarinforMapper;

	@Autowired
	private CusUsercarinforMapper cusUsercarinforMapper;

	/**
	 * 修改车辆信息最后使用时间
	 * 
	 * @param userCarinfor
	 * @return
	 */
	@Transactional
	public Map upUserCarinforLastuptime(BspUsercarinfor userCarinfor) {
		Map<String, Object> rsmap = new HashMap();

		BspUsercarinfor nuc = new BspUsercarinfor();
		nuc.setLastuptime(new Date());
		nuc.setLastusetime(new Date());

		BspUsercarinforExample example = new BspUsercarinforExample();
		example.createCriteria().andCarnumEqualTo(userCarinfor.getCarnum())
				.andMotornumEqualTo(userCarinfor.getMotornum())
				.andUseridEqualTo(userCarinfor.getUserid());

		int rsi = bspUsercarinforMapper.updateByExampleSelective(nuc, example);
		if (rsi > 0) {
			rsmap.put("state", "success");
		} else {
			rsmap.put("state", "faild");
			rsmap.put("msg", "保存失败");
		}
		return rsmap;
	}

	/**
	 * 添加车辆信息到数据库
	 * 
	 * @param userCarinfor
	 * @return
	 */
	@Transactional
	public Map addUserCarinfor(BspUsercarinfor userCarinfor) {
		Map<String, Object> rsmap = new HashMap();
		userCarinfor.setLastuptime(new Date());
		userCarinfor.setLastusetime(new Date());

		int rsi = cusUsercarinforMapper.insertSelective(userCarinfor);
		if (rsi > 0) {
			rsmap.put("state", "success");
		} else {
			rsmap.put("state", "faild");
			rsmap.put("msg", "保存失败");
		}
		return rsmap;
	}

	/**
	 * 按条件选择用户汽车信息
	 * 
	 * @param selWordsVo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BspUsercarinfor> selUsercarList(SelUsercarinforVO selWordsVo) {
		List<BspUsercarinfor> usercarList = null;
		String keyword = "";
		Integer userid = null;
		if (selWordsVo != null) {
			keyword = selWordsVo.getCarnumOrMotonum();
			userid = selWordsVo.getUid();
		}
		BspUsercarinforExample example = new BspUsercarinforExample();

		BspUsercarinforExample.Criteria c1 = example.createCriteria();
		c1.andMotornumLike("%" + keyword + "%");
		c1.andUseridEqualTo(userid);

		BspUsercarinforExample.Criteria c2 = example.createCriteria();
		c2.andCarnumLike("%" + keyword + "%");
		c2.andUseridEqualTo(userid);

		example.or(c2);

		example.setOrderByClause("lastusetime desc");
		usercarList = bspUsercarinforMapper.selectByExample(example);
		return usercarList;
	}

}
