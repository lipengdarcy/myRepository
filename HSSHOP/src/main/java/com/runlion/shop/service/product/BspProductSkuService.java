package com.runlion.shop.service.product;

import static ch.lambdaj.Lambda.by;
import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.on;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.lambdaj.group.Group;

import com.runlion.shop.dao.BspProductsMapper;
import com.runlion.shop.dao.BspProductskusMapper;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsExample;
import com.runlion.shop.entity.BspProductskus;
import com.runlion.shop.entity.BspProductskusExample;
import com.runlion.shop.vo.ProductSkuVO;

@Service
public class BspProductSkuService {
	@Autowired
	private BspProductsMapper bspProductsMapper;
	@Autowired
	private BspProductskusMapper bspProductskusMapper;

	private int genSkuGid() {
		Random random = new Random();

		int gid = random.nextInt();
		// 此处应该查询数据库查看是否已经生成过该值
		if (gid < 0) {
			gid = 0 - gid;
		}

		BspProductsExample bpe = new BspProductsExample();
		bpe.createCriteria().andSkugidEqualTo(gid);
		int count = bspProductsMapper.countByExample(bpe);
		if (count > 0) {
			gid = genSkuGid();
		}

		return gid;
	}

	public List<BspProductskus> getProductSkuListByGroup(int skugid) {
		return bspProductskusMapper.getProductSkuListByGroup(skugid);
	}

	// 删除产品sku
	public int deleteProductSku(Integer skugid) {
		BspProductskusExample e = new BspProductskusExample();
		e.or().andSkugidEqualTo(skugid);
		return bspProductskusMapper.deleteByExample(e);
	}

	// 更新产品表的产品名称
	public int updateProductName(BspProducts record) {
		return bspProductsMapper.updateByPrimaryKeySelective(record);
	}

	// 更新产品sku
	public boolean updateProductSku(ProductSkuVO u, String[] skuNames) {
		int newskugid = genSkuGid();
		// 1.先删除原先的sku信息
		int skugid = u.getProduct().getSkugid();
		List<BspProductskus> oldpidList = this.getProductSkuListByGroup(skugid);
		int deleteCount = this.deleteProductSku(skugid);
		// 2.再更新产品表的产品名称
		// 2.1比较新的sku和老的sku的产品数量
		HashSet<Integer> newpidSet = new HashSet<Integer>();
		for (BspProductskus p : u.getSkuList()) {
			newpidSet.add(p.getPid());
		}
		int flag = skuNames.length - oldpidList.size();
		// 2.2数量一致，产品表只做更新
		if (flag == 0) {
			int i = 0;
			for (BspProductskus p : oldpidList) {
				BspProducts b = bspProductsMapper
						.selectByPrimaryKey(p.getPid());
				String name = skuNames[i++];
				b.setName(name);
				this.updateProductName(b);
			}
		}
		// 2.3新的数量多，除了更新产品表，还要新增记录
		else if (flag > 0) {
			int i = 0;
			for (BspProductskus p : oldpidList) {
				BspProducts b = bspProductsMapper
						.selectByPrimaryKey(p.getPid());
				String name = skuNames[i++];
				b.setName(name);
				this.updateProductName(b);
			}
			// 剩余的直接插入产品表
			for (int j = i; j < skuNames.length; j++) {
				BspProducts b = bspProductsMapper.selectByPrimaryKey(oldpidList
						.get(0).getPid());
				BspProducts entity = (BspProducts) b.clone();
				String name = skuNames[j];
				entity.setName(name);
				bspProductsMapper.insertSelective(entity);
			}

		}
		// 2.4老的数量多，除了更新产品表，还要删除记录
		else {
			int i = 0;
			for (BspProductskus p : oldpidList) {
				BspProducts b = bspProductsMapper
						.selectByPrimaryKey(p.getPid());
				String name = skuNames[i++];
				b.setName(name);
				this.updateProductName(b);
			}
			// 剩余的直接删除
			for (int j = 0; j < 0 - flag; j++) {
				int pid = oldpidList.get(j).getPid();
				bspProductsMapper.deleteByPrimaryKey(pid);
			}

		}

		// 3.最后增加新的sku

		BspProducts product = u.getProduct();
		product.setAddtime(new Date());

		product.setSkugid(skugid);

		List<BspProductskus> skuList = u.getSkuList();
		// pid分组

		Group<BspProductskus> group = group(skuList,
				by(on(BspProductskus.class).getPid()));

		// 处理SKUList
		Map<Integer, List> pidSLMap = new HashMap();
		// 首先将产品sku关联信息按照产品虚拟id分组
		for (int li = 0; li < skuList.size(); li++) {
			BspProductskus psku = skuList.get(li);
			psku.setSkugid(skugid);
			Integer pid = psku.getPid();
			List tpskList = pidSLMap.get(pid);
			if (tpskList == null) {
				tpskList = new ArrayList();
				pidSLMap.put(pid, tpskList);
			}
			tpskList.add(psku);
		}
		// 存储分组后的产品及其相关联的sku属性信息列表
		List<ProductSkuVO> saveList = new ArrayList();
		Iterator ite = pidSLMap.keySet().iterator();
		if (pidSLMap.keySet().size() != skuNames.length) {
			return false;
		}
		int sni = 0;
		while (ite.hasNext()) {
			Integer tpid = (Integer) ite.next();
			BspProducts newPro = product.clone();
			newPro.setName(newPro.getName() + " " + skuNames[sni]);
			sni++;
			List tpskList = pidSLMap.get(tpid);

			ProductSkuVO newPskVO = new ProductSkuVO();
			newPskVO.setProduct(newPro);
			newPskVO.setSkuList(tpskList);

			saveList.add(newPskVO);
		}

		for (int i = 0; i < saveList.size(); i++) {
			ProductSkuVO psvo = saveList.get(i);
			// boolean rs = this.saveProductSku(psvo);

			BspProducts p = psvo.getProduct();
			int rsi = bspProductsMapper.insertSelective(p);
			if (rsi > 0) {
				int pid = p.getPid();
				List<BspProductskus> skuList2 = psvo.getSkuList();
				for (int si = 0; si < skuList2.size(); si++) {
					BspProductskus skuItem = skuList2.get(si);
					skuItem.setPid(pid);

					bspProductskusMapper.insertSelective(skuItem);

				}
				return true;
			} else {
				return false;
			}

		}
		return true;

	}

}
