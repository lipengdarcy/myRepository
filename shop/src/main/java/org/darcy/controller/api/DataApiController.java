package org.darcy.controller.api;

import org.darcy.framework.util.JsonResultUtil;
import org.darcy.service.dbsolution.DBSolutionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/core/admin/data")
public class DataApiController  {
	
	public Object export(String tb){
		
		try {
			String[] tables={tb};
			return DBSolutionFactory.dbExport(tables, false, "");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResultUtil.getErrorJson("导出失败");
		}
	}
	
}
