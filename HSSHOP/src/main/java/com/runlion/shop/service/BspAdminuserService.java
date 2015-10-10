package com.runlion.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspAdminuserMapper;
import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.entity.BspAdminuserExample;

@Service
public class BspAdminuserService {
	@Autowired
	private BspAdminuserMapper mapper;

	@Autowired
	private BspAttributevaluesMapper bspAttributevaluesMapper;

	public BspAdminuser selectByPrimaryKey(int pid) {
		BspAdminuser entity = mapper.selectByPrimaryKey(pid);
		
		return entity;
	}
	
	public BspAdminuser selectByNameAndPassword(String name,String password){
		BspAdminuserExample example=new BspAdminuserExample();
		
		example.createCriteria().andUsernameEqualTo(name).andPasswordEqualTo(password);
	
		List<BspAdminuser> rsList= mapper.selectByExample(example);
		if(rsList!=null && !rsList.isEmpty()){
			return rsList.get(0);
		}else{
			return null;
		}
	}
	
	public List<BspAdminuser> selectByKeyWord(String keyWord){
		List<BspAdminuser> rsList= mapper.selectByKeyWord(keyWord);
		return rsList;
	}

	public int updateByPrimaryKey(BspAdminuser record){
		return mapper.updateByPrimaryKey(record);
	}
	
	public int updateByPrimaryKeySelective(BspAdminuser record){
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	public int insertSelective(BspAdminuser record){
		return mapper.insertSelective(record);
	}
	
	public int deleteByPrimaryKey(Integer uid){
		return mapper.deleteByPrimaryKey(uid);
	}
}
