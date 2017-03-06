package org.darcy.service.resource;

import org.darcy.framework.database.ObjectNotFoundException;
import org.darcy.framework.database.Page;
import org.darcy.service.resource.model.EopUser;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-11 下午02:02:19
 *         </p>
 * @version 1.0
 */
public interface IUserManager {


	/**
	 * 获取某用户的详细信息
	 * 
	 * @param userid
	 * @return
	 */
	public EopUser getCurrentMember(Integer userid);
 

}
