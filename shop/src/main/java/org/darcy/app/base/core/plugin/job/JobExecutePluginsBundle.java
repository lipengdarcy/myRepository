package org.darcy.app.base.core.plugin.job;

import java.util.List;

import org.darcy.framework.plugin.AutoRegisterPluginsBundle;
import org.darcy.framework.plugin.IPlugin;
import org.springframework.stereotype.Service;

/**
 * 任务执行器插件桩
 * @author kingapex
 *
 */
@Service
public class JobExecutePluginsBundle extends AutoRegisterPluginsBundle {

	@Override
	public String getName() {
		return "任务执行器插件桩";
	}

	/**
	 * 每天分钟执行
	 */
	public void everyMinutesExcecute(){
		List<IPlugin> plugins = this.getPlugins();
		 
		if(plugins!=null){ 
			for(IPlugin plugin:plugins){
				if( plugin instanceof IEveryMinutesExecuteEvent){
					
					if(this.loger.isDebugEnabled()){
						this.loger.debug("调度任务插件["+plugin.getClass()+"]everyMinutes开始执行...");
					}
					
					IEveryMinutesExecuteEvent event  = (IEveryMinutesExecuteEvent)plugin;
					event.everyMinutes();
					
					if(this.loger.isDebugEnabled()){
						this.loger.debug("调度任务插件["+plugin.getClass()+"]everyMinutes执行完成...");
					}
				}
			}
		}
	}
	
	
	
	/**
	 * 每天小时执行
	 */
	public void everyHourExcecute(){
		List<IPlugin> plugins = this.getPlugins();
		 
		if(plugins!=null){ 
			for(IPlugin plugin:plugins){
				if( plugin instanceof IEveryHourExecuteEvent){
					
					if(this.loger.isDebugEnabled()){
						this.loger.debug("调度任务插件["+plugin.getClass()+"]everyHour开始执行...");
					}
					
					IEveryHourExecuteEvent event  = (IEveryHourExecuteEvent)plugin;
					event.everyHour();
					
					if(this.loger.isDebugEnabled()){
						this.loger.debug("调度任务插件["+plugin.getClass()+"]everyHour执行完成...");
					}
				}
			}
		}
	}
	
	
	
	/**
	 * 每天执行
	 */
	public void everyDayExcecute(){
		
		try{
			List<IPlugin> plugins = this.getPlugins();
			
			if(plugins!=null){
				for(IPlugin plugin:plugins){
					if( plugin instanceof IEveryDayExecuteEvent){
						
						if(this.loger.isDebugEnabled()){
							this.loger.debug("调度任务插件["+plugin.getClass()+"]everyDay开始执行...");
						}
						
						
						IEveryDayExecuteEvent event  = (IEveryDayExecuteEvent)plugin;
						event.everyDay();
						
						if(this.loger.isDebugEnabled()){
							this.loger.debug("调度任务插件["+plugin.getClass()+"]everyDay执行完成...");
						}
					}
				}
			}
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	
	
	/**
	 * 每月执行 
	 */
	public void everyMonthExcecute(){
		List<IPlugin> plugins = this.getPlugins();
		
		if(plugins!=null){
			for(IPlugin plugin:plugins){
				if( plugin instanceof IEveryMonthExecuteEvent){
					
					if(this.loger.isDebugEnabled()){
						this.loger.debug("调度任务插件["+plugin.getClass()+"]everyMonth开始执行...");
					}
					
					IEveryMonthExecuteEvent event  = (IEveryMonthExecuteEvent)plugin;
					event.everyMonth();
					
					if(this.loger.isDebugEnabled()){
						this.loger.debug("调度任务插件["+plugin.getClass()+"]everyMonth执行完成...");
					}
				}
			}
		}
	}
	
	
	
	
	
}



