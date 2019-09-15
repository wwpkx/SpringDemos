package junit.test;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import cn.itcast.bean.AirLine;

public class AirLineTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void save(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new AirLine("PEK", "SHA", "北京飞上海"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
