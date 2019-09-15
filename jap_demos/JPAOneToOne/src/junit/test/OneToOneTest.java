package junit.test;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import cn.itcast.bean.IDCard;
import cn.itcast.bean.Person;

public class OneToOneTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void save(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();		
		Person person=new Person("¿œ’≈");	
		person.setIdcard(new IDCard("1111122222"));
		em.persist(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
