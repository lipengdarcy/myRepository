package com.runlion.shop.service.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspNcenterinforMapper;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspNcenterinforExample;

@Service
public class BspNcEntityService {
	@Autowired
	BspNcenterinforMapper bspNcenterinforMapper;

	public Map<String, Object> handNcProList(List<BspNcenterinfor> proList) {
		Map<String, Object> rsMap = new HashMap();
		List<BspNcenterinfor> newList = new ArrayList();
		// 首先修改原有数据
		for (int i = 0; i < proList.size(); i++) {
			BspNcenterinfor pro = proList.get(i);
			String pid = pro.getPid();
			String pkcorp = pro.getPkcorp();
			String eid = pro.getEid();
			pro.setLastuptime(new Date());
			BspNcenterinforExample bnfe = new BspNcenterinforExample();
			bnfe.createCriteria().andPidEqualTo(pid).andPkcorpEqualTo(pkcorp)
					.andEidEqualTo(eid);
			int rsi = bspNcenterinforMapper.updateByExampleSelective(pro, bnfe);
			if (rsi <= 0) {
				newList.add(pro);
			}
		}
		// 插入新的数据
		int failNum = 0;
		String failInfor = "";
		for (int i = 0; i < newList.size(); i++) {
			BspNcenterinfor pro = newList.get(i);
			pro.setLastuptime(new Date());
			int rsi = bspNcenterinforMapper.insertSelective(pro);
			if (rsi <= 0) {
				failNum++;
				failInfor += "pid:" + pro.getPid() + ",pkcorp:"
						+ pro.getPkcorp() + "###";
			}
		}
		if (failNum > 0) {
			rsMap.put("state", "failed");
			rsMap.put("msg", "保存时发生错误!有" + failNum + "条数据没有保存成功!具体信息如下:"
					+ failInfor);
		} else {
			rsMap.put("state", "success");
			rsMap.put("msg", "水泥电商已经将数据保存成功！");
		}
		return rsMap;
	}
}
