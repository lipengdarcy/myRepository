package darcy.cglibtest;

public class BookServiceFactory {
	private static BookServiceBean service = new BookServiceBean();

	private BookServiceFactory() {
	}

	public static BookServiceBean getInstance() {
		return service;
	}
}