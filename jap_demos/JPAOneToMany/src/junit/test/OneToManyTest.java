package junit.test;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import cn.itcast.bean.Order;
import cn.itcast.bean.OrderItem;

public class OneToManyTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void save(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		
		Order order=new Order();
		order.setAmount(34f);
		order.setOrderid("999");
		OrderItem orderItem1=new OrderItem();
		orderItem1.setProductName("×ãÇò");
		orderItem1.setSellPrice(90f);
		OrderItem orderItem2=new OrderItem();
		orderItem2.setProductName("è¤Ù¤Çò");
		orderItem2.setSellPrice(30f);
		order.addOrderItem(orderItem1);
		order.addOrderItem(orderItem2);
		
		em.persist(order);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
