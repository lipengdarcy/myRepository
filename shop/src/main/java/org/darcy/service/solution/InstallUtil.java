package org.darcy.service.solution;

import java.util.ArrayList;
import java.util.List;

import org.darcy.framework.context.webcontext.ThreadContextHolder;

public class InstallUtil {
	public static String installing = "installing";

	public static void putMessaage(String msg) {
		if (ThreadContextHolder.getSessionContext() != null) {
			List msgList = (List) ThreadContextHolder.getSessionContext().getAttribute("installMsg");
			if (msgList == null) {
				msgList = new ArrayList();
			}
			msgList.add(msg);
			ThreadContextHolder.getSessionContext().setAttribute("installMsg", msgList);
		}
	}
}
