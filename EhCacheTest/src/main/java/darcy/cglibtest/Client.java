package darcy.cglibtest;

public class Client {

	public static void main(String[] args) {
		BookServiceBean service = BookServiceFactory.getInstance();
		doMethod(service);
	}

	public static void doMethod(BookServiceBean service) {
		service.create();
		service.update();
		service.query();
		service.delete();
	}
}