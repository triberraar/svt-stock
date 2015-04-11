package be.tribersoft.svt.stock;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static final String CONFIG_PATH = "context.xml";

	public static void main(String... args) {
		try (final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_PATH)) {
			Application application = context.getBean(Application.class);
			application.run();
		}
	}
}
