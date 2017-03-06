package com.enation.app.base.core.plugin.fdfs;

import org.darcy.framework.cache.ICache;

/**
 * 获取fdfs专用缓存
 * @author Chopper
 *
 */
public interface IGetFDFSCacheEvent {

	public ICache getCache(); 
	
}
