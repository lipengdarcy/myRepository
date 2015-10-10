package com.runlion.shop.junit;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BaseTest {
	ApplicationContext context = null;

	@Before
	public void initContext() {
		this.context = new FileSystemXmlApplicationContext(
				"WebRoot/WEB-INF/classes/spring/application-context.xml");
	}

}
