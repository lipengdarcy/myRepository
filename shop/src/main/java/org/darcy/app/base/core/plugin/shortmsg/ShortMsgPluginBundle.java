package org.darcy.app.base.core.plugin.shortmsg;

import java.util.ArrayList;
import java.util.List;

import org.darcy.app.base.core.model.ShortMsg;
import org.darcy.framework.plugin.AutoRegisterPluginsBundle;
import org.darcy.framework.plugin.IPlugin;
import org.springframework.stereotype.Service;

/**
 * 短消息插件桩
 * @author kingapex
 *
 */
@Service
public class ShortMsgPluginBundle extends AutoRegisterPluginsBundle {

	@Override
	public String getName() {
		return "短消息插件桩";
	}
	
	public List<ShortMsg> getMessageList(){
		List<ShortMsg> msgList  = new ArrayList<ShortMsg>();
		List<IPlugin> plugins = this.getPlugins();
		
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if(plugin instanceof IShortMessageEvent){
					IShortMessageEvent event = (IShortMessageEvent)plugin;
					List<ShortMsg> somemsgList = event.getMessage();
					//判断是否为空
					if(somemsgList!=null){
						msgList.addAll(somemsgList);
					}
				}
			}
		}
		return msgList;
	}

}
