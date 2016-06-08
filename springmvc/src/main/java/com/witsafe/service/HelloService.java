package com.witsafe.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witsafe.dao.CompanyMapper;
import com.witsafe.model.Company;

/**
 * 企业信息
 * 
 * 
 */

@Service
public class HelloService {

	@Autowired
	private CompanyMapper mapper;



	/**
	 * 查询
	 */
	public Company get(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 新增
	 */
	public int create(Company record) {
		record.setRegistertime(new Date());
		record.setIsvalid((byte) 1);
		return mapper.insertSelective(record);
	}

	/**
	 * 删除
	 */
	public int delete(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 编辑
	 */
	public int update(Company record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

}
