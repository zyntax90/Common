package ch.common.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.common.utils.HibernateUtil;

class HibernateTests {

	private final static String TestValue = "TestValue";
	private static TestPojo testObj;

	@BeforeAll
	static void initializeTestData() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory("hibernate.cfg.xml", "HibernateTests.hbm.xml");

		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			testObj = new TestPojo();
			testObj.TestColumn = TestValue;
			session.save(testObj);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();

		}
	}

	@Test
	void testSelectData() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory("hibernate.cfg.xml", "HibernateTests.hbm.xml");

		Session session = null;

		try {
			session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			List<TestPojo> list = session.createQuery("from TestPojo where TestColumn = :testValue")
					.setParameter("testValue", TestValue).list();
			assertTrue(list.size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();

		}
	}

	@AfterAll
	static void cleardown() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory("hibernate.cfg.xml", "HibernateTests.hbm.xml");

		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			session.delete(testObj);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
			HibernateUtil.destroyRegistryBuilder();
		}
	}

}
