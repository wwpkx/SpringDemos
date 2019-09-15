package junit.test;

import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import cn.itcast.bean.Person;

public class PersonTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void save(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		//-->sessionFactory-->session-->begin事务
		//在得到sessionFactory的时候，就创建表
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//开始事务
		em.persist(new Person("传智播客"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	//查询数据不需要开启事务，只有发生数据更改时才需要开启事务
	@Test
	public void getPerson(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		Person person=em.find(Person.class, 1);//类似get()
//		/**
//		 * 用了一分钟,数据库中的该条数据如果已经被修改，需要用refresh方法去获取最新的数据
//		 */
//		em.refresh(person);
		System.out.println(person.getName());
		em.close();
		factory.close();
	}
	//find和getReference的区别之一：当数据库没有该记录时，那么find返回的对象是null；
	//getReference返回的对象在输出时，会报EntityNotFoundException异常,在执行（2）时报错
	@Test
	public void getPerson2(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		Person person=em.getReference(Person.class, 1);//1
		//类似load(),获得的只是代理类
		System.out.println(person.getName());//2
		em.close();
		factory.close();
	}
	//(事务管理的四种状态)new
	//managed 托管
	//游离(脱管)
	//删除
	@Test
	public void updatePerson(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//开始事务
		Person person=em.find(Person.class, 1);
		//事务关联，处于托管状态，满足这两个条件时可以直接修改对象属性
		person.setName("老张");
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void updatePerson2(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//开始事务
		Person person=em.find(Person.class, 1);
		em.clear();//把实体管理器中的所有实体变成游离状态
		person.setName("老黎");
		em.merge(person);//把游离状态下的实体bean更新同步回数据库
		em.getTransaction().commit();
		em.close();
		factory.close();
	}	
	
	@Test
	public void delete(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//开始事务
		Person person=em.find(Person.class, 1);
		em.remove(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}	

	@Test
	public void query(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select o from Person o where o.id=?1");
		query.setParameter(1, 2);
		List<Person> persons=query.getResultList();
		for(Person person:persons)
			System.out.println(person.getName());
		em.close();
		factory.close();
	}
	@Test
	public void deletequery(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//开始事务
		Query query=em.createQuery("delete from Person o where o.id=?1");
		query.setParameter(1, 2);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}	
	
	@Test
	public void queryupdate(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//开始事务
		Query query=em.createQuery("update Person o set o.name=:name where o.id=:id");
		query.setParameter("name", "xxx");
		query.setParameter("id", 3);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}	
}
