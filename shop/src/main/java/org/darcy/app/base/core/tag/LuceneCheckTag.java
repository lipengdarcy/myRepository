/**
 * 
 */
package org.darcy.app.base.core.tag;

import java.util.Map;

import org.darcy.eop.SystemSetting;
import org.darcy.framework.action.WWAction;
import org.darcy.framework.taglib.BaseFreeMarkerTag;
import org.springframework.stereotype.Component;

import freemarker.template.TemplateModelException;

/**
 * lucene是否开启检测标签 
 * 如果开启返回ON，没有开启返回OFF
 * @author kingapex
 *2015-5-20
 */
@Component
public class LuceneCheckTag extends BaseFreeMarkerTag {

	/* (non-Javadoc)
	 * @see com.enation.framework.taglib.BaseFreeMarkerTag#exec(java.util.Map)
	 */
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		 
		return SystemSetting.getLucene()==1?"ON":"OFF";
	}
 

}
