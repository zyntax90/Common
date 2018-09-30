package ch.common.utils;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry standardServiceRegistryBuilder;

	public static SessionFactory getSessionFactory(String configurationPath, String mappingPath) {
		if (sessionFactory == null) {

			Configuration configuration = new Configuration();
			configuration.configure(configurationPath);
			configuration.addResource(mappingPath);
			standardServiceRegistryBuilder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(standardServiceRegistryBuilder);
		}
		return sessionFactory;
	}

	public static void destroyRegistryBuilder() {
		StandardServiceRegistryBuilder.destroy(standardServiceRegistryBuilder);
	}

}
