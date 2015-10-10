package com.runlion.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.common.util.BaiDuMapUtil;
import com.runlion.shop.dao.BspNcenterinforMapper;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspWorktimeMapper;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspNcenterinforExample;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.vo.SalerAddressVO;

@Service
public class BspSaleaddressService {
	//
	@Autowired
	private BspSaleaddressMapper bspSaleaddressMapper;

	@Autowired
	private BspNcenterinforMapper bspNcenterinforMapper;

	@Autowired
	private BspWorktimeMapper bspWorktimeMapper;

	public boolean isinscope(String x, String y, String coordinates) {
		boolean rs = false;
		String[] point = coordinates.trim().split("，");
		String[] a = point[0].split("°");
		String x1 = a[0] + "." + a[1].replace("'", "").replace("''", "");

		String[] b = point[1].split("°");
		String y1 = b[0] + "." + b[1].replace("'", "").replace("''", "");
		System.out.println("(" + x1 + "," + y1 + ")");
		Double dis = BaiDuMapUtil.GetLongDistance(Double.parseDouble(x),
				Double.parseDouble(y), Double.parseDouble(x1),
				Double.parseDouble(y1));
		// coordinates5公里以内的店铺返回true
		if (dis < 5000) {
			System.out.println(dis);
			rs = true;
		}
		return rs;
	}

	public String[] turntoDouble(String coordinates) {
		String[] point = coordinates.trim().split("，");
		String[] a = point[0].split("°");
		String x = a[0] + "." + a[1].replace("'", "").replace("''", "");

		String[] b = point[1].split("°");
		String y = b[0] + "." + b[1].replace("'", "").replace("''", "");

		String[] rs = { x, y };
		return rs;
	}

	// 修改工厂或者门店的信息及相关表的信息
	public Map<String, Object> updateSaleAddress(SalerAddressVO saleAddress,
			String delProIds) {
		Map<String, Object> rsmap = new HashMap();
		BspSaleaddress saleaddr = saleAddress.getSaleAddress();
		bspSaleaddressMapper.updateByPrimaryKeySelective(saleaddr);
		// 修改允销目录列表信息
		List<BspNcenterinfor> ncprolist = saleAddress.getProList();
		if (ncprolist != null) {
			String nceid = saleaddr.getPkcorp();
			String ename = saleaddr.getName();
			for (int ni = 0; ni < ncprolist.size(); ni++) {
				BspNcenterinfor ncpro = ncprolist.get(ni);
				ncpro.setEid(nceid);
				ncpro.setEname(ename);
				if (ncpro.getId() != null && ncpro.getId() > 0) {
					bspNcenterinforMapper.updateByPrimaryKeySelective(ncpro);
				} else {
					if (ncpro.getId() != null && ncpro.getId() < 0) {
						ncpro.setState(0);
						ncpro.setId(null);
					}
					bspNcenterinforMapper.insertSelective(ncpro);
				}
			}
		}
		// 根据给定的ids删除允销目录
		if (delProIds != null && !delProIds.trim().equals("")) {
			String[] pidarr = delProIds.split(",");
			List<Integer> delList = new ArrayList();
			for (int pi = 0; pi < pidarr.length; pi++) {
				String delid = pidarr[pi];
				Integer idelid = Integer.valueOf(delid);
				delList.add(idelid);
			}
			BspNcenterinforExample ex = new BspNcenterinforExample();
			ex.createCriteria().andIdIn(delList);
			bspNcenterinforMapper.deleteByExample(ex);
		}
		// 修改门店工作时间信息
		List<BspWorktime> wtlist = saleAddress.getWorkTimeList();
		if (wtlist != null) {
			Integer sid = saleaddr.getId();
			for (int wi = 0; wi < wtlist.size(); wi++) {
				BspWorktime wt = wtlist.get(wi);
				wt.setWtpickpointid(sid);
				if (wt.getWtid() != null && wt.getWtid() > 0) {
					bspWorktimeMapper.updateByPrimaryKeySelective(wt);
				} else {
					if (wt.getWtid() != null && wt.getWtid() < 0) {
						wt.setWtid(null);
						bspWorktimeMapper.insertSelective(wt);
					}
				}
			}
		}
		rsmap.put("state", "success");
		return rsmap;
	}

	public List<BspNcenterinfor> getProlistByNcnum(String ncnum) {
		BspNcenterinforExample ex = new BspNcenterinforExample();
		ex.createCriteria().andEidEqualTo(ncnum);
		return bspNcenterinforMapper.selectByExample(ex);
	}

	public BspSaleaddress getBspSaleaddressById(Integer id) {
		return bspSaleaddressMapper.selectByPrimaryKey(id);
	}
}
