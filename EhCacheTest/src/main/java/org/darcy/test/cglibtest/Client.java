package org.darcy.test.cglibtest;

public class Client {

	public static void main(String[] args) {
		doMethod();
		doMethodWithProxy();
		doMethodWithProxyAndFilter();
	}

	public static void doMethod() {
		BookServiceBean service = BookServiceFactory.getInstance();
		service.create();
		service.update();
		service.query();
		service.delete();
	}

	public static void doMethodWithProxy() {
		BookServiceBean service = BookServiceFactory
				.getProxyInstance(new MyCglibProxy("boss"));
		service.create();
		BookServiceBean service2 = BookServiceFactory
				.getProxyInstance(new MyCglibProxy("john"));
		service2.create();
	}

	public static void doMethodWithProxyAndFilter() {

		BookServiceBean service = BookServiceFactory
				.getAuthInstanceByFilter(new MyCglibProxy("jhon"));
		service.create();
		BookServiceBean service2 = BookServiceFactory
				.getAuthInstanceByFilter(new MyCglibProxy("jhon"));
		service2.query();
	}
}