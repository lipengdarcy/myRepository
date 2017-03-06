package org.darcy.service.framework.component;

import java.util.List;

import org.darcy.framework.plugin.AutoRegisterPlugin;
import org.darcy.framework.plugin.IPluginBundle;
import org.darcy.service.framework.component.context.ComponentContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class ComponentLoader implements BeanPostProcessor {

	
	public Object postProcessAfterInitialization(Object bean, String arg1)
			throws BeansException {
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		 
		if(bean instanceof AutoRegisterPlugin){
			AutoRegisterPlugin plugin =  (AutoRegisterPlugin)bean;
			if(plugin.getBundleList()==null){
			}else{
				
				//plugin.register();
				List<IPluginBundle> pluginBundelList = plugin.getBundleList();
				for( IPluginBundle bundle :pluginBundelList){
					bundle.registerPlugin(plugin);
				}
			 
				
			}
		}
		
		if(bean instanceof IComponent){
		 
			IComponent component = (IComponent)bean;
			ComponentView componentView = new ComponentView();
			componentView.setComponent(component);
			componentView.setComponentid( beanName );
			ComponentContext.registerComponent(componentView);
		}
		
		return bean;
	}

}
