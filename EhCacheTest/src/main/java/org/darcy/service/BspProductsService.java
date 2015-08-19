package org.darcy.service;

import java.util.List;

import org.darcy.dao.BspProductsMapper;
import org.darcy.entity.BspProducts;
import org.darcy.entity.BspProductsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

@Service("bspProductsService")
public class BspProductsService {
	@Autowired
	private BspProductsMapper mapper;

	public BspProducts selectByPrimaryKey(int pid) {
		BspProducts entity = mapper.selectByPrimaryKey(pid);
		return entity;
	}

	// 分页查询 测试
	public List<BspProducts> selectByExample(BspProductsExample example,
			int pageNumber, int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		List<BspProducts> list = mapper.selectByExample(example);
		return list;
	}

	// 产品搜索
	public List<BspProducts> search(String keywords, int pageNumber,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		BspProductsExample e = new BspProductsExample();
		String[] keys = keywords.split(" ");
		for (String key : keys) {
			if (key.equals(""))
				continue;
			// System.out.println(key);
			e.or().andNameLike(key);
		}

		List<BspProducts> list = mapper.selectByExample(e);
		return list;
	}

}
