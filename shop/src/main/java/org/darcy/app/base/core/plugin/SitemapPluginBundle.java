package org.darcy.app.base.core.plugin;

import java.util.List;

import org.darcy.framework.plugin.AutoRegisterPluginsBundle;
import org.darcy.framework.plugin.IPlugin;
import org.springframework.stereotype.Service;
/**
 * 站点地图插件桩
 * @author Kanon 2015-9-24 version 1.1 添加注释
 *
 */
@Service
public class SitemapPluginBundle extends AutoRegisterPluginsBundle {

	public String getName() {
		return "站点地图插件桩";
	}
	
	/**
	 * 获取站点地图列表
	 */
	public void onRecreateMap(){
		//获取插件桩下所有插件
		List<IPlugin> plugins = this.getPlugins();
		
		//判断插件是否实现IRecreateMapEvent接口
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof IRecreateMapEvent) {
					IRecreateMapEvent event = (IRecreateMapEvent) plugin;
					 event.onRecreateMap();
				}
			}
		}
		
	}

}
