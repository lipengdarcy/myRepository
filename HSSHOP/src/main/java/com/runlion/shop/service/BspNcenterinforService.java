package com.runlion.shop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.runlion.shop.dao.BspNcenterinforMapper;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspNcenterinforExample;

@Service
public class BspNcenterinforService {

	@Autowired
	private BspNcenterinforMapper bspNcenterinforMapper;

	/**
	 * 获取nc中允销产品，正常情况下应该只返回一个值
	 * 
	 * @param ncpronum
	 * @return
	 */
	public List<BspNcenterinfor> getBspNcenterinfor(String ncpronum, String eid) {
		BspNcenterinforExample example = new BspNcenterinforExample();
		example.createCriteria().andPidEqualTo(ncpronum).andEidEqualTo(eid);
		return bspNcenterinforMapper.selectByExample(example);
	}

	/**
	 * 通过关键字获取nc产品列表
	 * 
	 * @param ncpronum
	 * @return
	 */
	public List<BspNcenterinfor> getBspNcProList(String keyWord,
			Integer pageNum, Integer numPerPage) {
		// 启用分页
		PageHelper.startPage(pageNum, numPerPage);
		// 获取有效的且符合查询关键字的NC产品信息
		BspNcenterinforExample example = new BspNcenterinforExample();
		BspNcenterinforExample.Criteria ec1 = example.createCriteria();
		ec1.andPidLike("%" + keyWord + "%").andStateEqualTo(0);

		BspNcenterinforExample.Criteria ec2 = example.createCriteria();
		ec2.andPnameLike("%" + keyWord + "%").andStateEqualTo(0);

		example.or(ec2);

		return bspNcenterinforMapper.selectByExample(example);
	}

	/**
	 * 获取NC推送的产品的总的条目数
	 * 
	 * @param keyWord
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public Integer countBspNcProList(String keyWord) {

		BspNcenterinforExample example = new BspNcenterinforExample();

		BspNcenterinforExample.Criteria ec1 = example.createCriteria();
		ec1.andPidLike("%" + keyWord + "%").andStateEqualTo(0);

		BspNcenterinforExample.Criteria ec2 = example.createCriteria();
		ec2.andPnameLike("%" + keyWord + "%").andStateEqualTo(0);

		example.or(ec2);

		return bspNcenterinforMapper.countByExample(example);
	}

	/**
	 * 
	 * @param ncprolist
	 * @return
	 */
	public boolean upNcenterinforList(List<BspNcenterinfor> ncprolist) {
		if (ncprolist != null) {
			for (int i = 0; i < ncprolist.size(); i++) {
				BspNcenterinfor ncpro = ncprolist.get(i);
				if (ncpro.getId() != null) {
					bspNcenterinforMapper.updateByPrimaryKeySelective(ncpro);
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean delNcenterinforById(Integer id) {
		int rsi = bspNcenterinforMapper.deleteByPrimaryKey(id);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过主键获取允销目录条目
	 * 
	 * @param id
	 * @return
	 */
	public BspNcenterinfor getNcenterinforById(Integer id) {
		return bspNcenterinforMapper.selectByPrimaryKey(id);
	}

	/**
	 * 添加新的信息到允销目录表
	 * 
	 * @param ncPro
	 * @return
	 */
	public boolean addNewNcenterinfor(BspNcenterinfor ncPro) {
		ncPro.setLastuptime(new Date());
		ncPro.setState(0);
		int rsi = bspNcenterinforMapper.insertSelective(ncPro);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 修改允销目录条目表
	 * 
	 * @param ncPro
	 * @return
	 */
	public boolean upNcenterinfor(BspNcenterinfor ncPro) {
		ncPro.setLastuptime(new Date());
		int rsi = bspNcenterinforMapper.updateByPrimaryKeySelective(ncPro);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}
}
