package org.darcy.service.solution.impl;

import org.darcy.service.framework.component.IComponentManager;
import org.darcy.service.solution.IInstaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 组件安装器
 * @author kingapex
 *
 */
@Service
public class ComponentInstaller implements IInstaller {

	@Autowired
	private IComponentManager componentManager;

	@Override
	public void install(String productId, Node fragment) {
		NodeList componentList = fragment.getChildNodes();
		int length = componentList.getLength();

		for (int i = 0; i < length; i++) {
			Node node = componentList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element componentEl = (Element) node;
				String componentid = componentEl.getAttribute("id");
				componentManager.install(componentid);
				componentManager.start(componentid);
			}
		}
	}


}
