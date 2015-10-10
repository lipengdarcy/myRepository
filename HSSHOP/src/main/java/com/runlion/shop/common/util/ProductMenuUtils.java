package com.runlion.shop.common.util;

import java.util.List;

import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.entity.ProductComboInfo;

public class ProductMenuUtils {

	public static List<ProductComboInfo> getMenuLink(int regionid) {

		List<ProductComboInfo> list = (List<ProductComboInfo>) EHCacheUtil
				.get("menuLink");
		for (ProductComboInfo p : list) {
			//

		}

		return list;
	}

}
