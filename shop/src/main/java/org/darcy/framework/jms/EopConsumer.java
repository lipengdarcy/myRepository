package org.darcy.framework.jms;

import org.apache.log4j.Logger;
import org.darcy.eop.sdk.context.EopContext;
import org.darcy.framework.context.spring.SpringContextHolder;
import org.darcy.framework.util.StringUtil;

/**
 * 
 * Eop消费者
 * @author kingapex
 *
 */
public class EopConsumer {
	
	protected final Logger logger = Logger.getLogger(getClass());
	

	
	
	public void execute(IEopJmsMessage message){
		
		try{
			System.out.println("in...");
			
			//获取原上下文，设置为当前的上下文
			EopContext context = message.getEopContext();
			EopContext.setContext(context);
			
			Object data = message.getData();
			String processorid = message.getProcessorBeanId();
			
			IJmsProcessor processor = SpringContextHolder.getBean(processorid);
			processor.process(data);
			

			
			if(message instanceof ITaskView){
				ITaskView task = (ITaskView)message;
				task.setState(1); //执行成功
				TaskContainer.pushTask(task);
			}
		}catch(Exception e){
			
			this.logger.error("Jms消息执行出错",e);
			
			if(message instanceof ITaskView){
				
				ITaskView task = (ITaskView)message;
				task.setState(2); //标识为错误状态
				task.setErrorMessage(StringUtil.getStackTrace(e));
				TaskContainer.pushTask(task);
				
			}
			
		}finally{
			EopContext.remove();
		}
		
	}
	
	
	
}
  
