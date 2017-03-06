package com.enation.app.base.core.plugin.fdfs;

import java.io.File;
import java.util.List;

import org.darcy.framework.cache.ICache;
import org.darcy.framework.plugin.AutoRegisterPluginsBundle;
import org.darcy.framework.plugin.IPlugin;
import org.springframework.stereotype.Service;

/**
 * 文件分发插件桩
 * @author Chopper 
 */
@Service("fastdfsBundle")
public class FastdfsBundle extends AutoRegisterPluginsBundle implements IFileUploadEvent,IGetFDFSCacheEvent{

	@Override
	public String getName() { 
		return "文件分发插件装";
	}

	@Override
	public String BeAfterUpload(File file) {
		List<IPlugin> plugins =  this.getPlugins();
		if(plugins!=null){
			for (IPlugin plugin : plugins) {
				if(plugin instanceof IFileUploadEvent){
					return ((IFileUploadEvent)plugin).BeAfterUpload(file);
				}
			}
		}
		return null;
	}


	@Override
	public ICache getCache() {
		List<IPlugin> plugins =  this.getPlugins();
		if(plugins!=null){
			for (IPlugin plugin : plugins) {
				if(plugin instanceof IGetFDFSCacheEvent){
					return ((IGetFDFSCacheEvent)plugin).getCache();
				}
			}
		}
		return null;
	}

}
