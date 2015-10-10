package com.runlion.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspProductimagesMapper;
import com.runlion.shop.dao.BspProductsMapper;
import com.runlion.shop.entity.BspProductimages;
import com.runlion.shop.entity.BspProductimagesExample;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsExample;

@Service("productimagesService")
public class ProductimagesService {

	@Autowired
	private BspProductimagesMapper bspProductimagesMapper;

	@Autowired
	private BspProductsMapper mapper;

	public boolean saveProductimage(BspProductimages proImg) {
		int rsi = bspProductimagesMapper.insertSelective(proImg);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delProductimagesById(Integer id) {
		BspProductimages img = bspProductimagesMapper.selectByPrimaryKey(id);
		if (img.getIsmain() == 1) {
			// 删除产品表中的主图
			Integer pid = img.getPid();
			BspProducts pro = new BspProducts();
			pro.setPid(pid);
			pro.setShowimg("");
			mapper.updateByPrimaryKeySelective(pro);
		}

		int rsi = bspProductimagesMapper.deleteByPrimaryKey(id);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setMainImg(Integer pimgid, Integer pid, byte type,
			String imgName) {

		BspProductimages img = bspProductimagesMapper
				.selectByPrimaryKey(pimgid);
		if (img != null) {
			byte oldType = img.getIsmain();
			//
			BspProductimagesExample bpie = new BspProductimagesExample();
			bpie.createCriteria().andPidEqualTo(pid).andIsmainEqualTo(type);
			// 现将所有的与该类型相同的设置为0
			BspProductimages proImg = new BspProductimages();
			proImg.setIsmain((byte) 0);
			bspProductimagesMapper.updateByExampleSelective(proImg, bpie);
			// 然后将当前图片设置为请求的图片类型

			BspProductimagesExample mainbpie = new BspProductimagesExample();
			mainbpie.createCriteria().andPimgidEqualTo(pimgid);

			BspProductimages mainImg = new BspProductimages();
			mainImg.setIsmain(type);
			bspProductimagesMapper.updateByExampleSelective(mainImg, mainbpie);
			// 根据请求的类型和旧的类型来更改产品存储的主图的字段
			BspProductsExample pe = new BspProductsExample();
			pe.createCriteria().andPidEqualTo(pid);
			BspProducts pro = new BspProducts();
			if (oldType == 3) {// 如果更改的是首页小图的话需要将产品的主图字段清除
				pro.setShowimg("");
				mapper.updateByExampleSelective(pro, pe);
			}
			if (type == 3) {// 如果将当前图设置为首页小图需要设置主图到产品
				pro.setShowimg(imgName);
				mapper.updateByExampleSelective(pro, pe);
			}

		}

		return true;
	}
}
