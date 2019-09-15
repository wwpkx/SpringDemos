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
		//-->sessionFactory-->session-->begin����
		//�ڵõ�sessionFactory��ʱ�򣬾ʹ�����
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//��ʼ����
		em.persist(new Person("���ǲ���"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	//��ѯ���ݲ���Ҫ��������ֻ�з������ݸ���ʱ����Ҫ��������
	@Test
	public void getPerson(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		Person person=em.find(Person.class, 1);//����get()
//		/**
//		 * ����һ����,���ݿ��еĸ�����������Ѿ����޸ģ���Ҫ��refresh����ȥ��ȡ���µ�����
//		 */
//		em.refresh(person);
		System.out.println(person.getName());
		em.close();
		factory.close();
	}
	//find��getReference������֮һ�������ݿ�û�иü�¼ʱ����ôfind���صĶ�����null��
	//getReference���صĶ��������ʱ���ᱨEntityNotFoundException�쳣,��ִ�У�2��ʱ����
	@Test
	public void getPerson2(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		Person person=em.getReference(Person.class, 1);//1
		//����load(),��õ�ֻ�Ǵ�����
		System.out.println(person.getName());//2
		em.close();
		factory.close();
	}
	//(������������״̬)new
	//managed �й�
	//����(�ѹ�)
	//ɾ��
	@Test
	public void updatePerson(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//��ʼ����
		Person person=em.find(Person.class, 1);
		//��������������й�״̬����������������ʱ����ֱ���޸Ķ�������
		person.setName("����");
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void updatePerson2(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//��ʼ����
		Person person=em.find(Person.class, 1);
		em.clear();//��ʵ��������е�����ʵ��������״̬
		person.setName("����");
		em.merge(person);//������״̬�µ�ʵ��bean����ͬ�������ݿ�
		em.getTransaction().commit();
		em.close();
		factory.close();
	}	
	
	@Test
	public void delete(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();//��ʼ����
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
		em.getTransaction().begin();//��ʼ����
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
		em.getTransaction().begin();//��ʼ����
		Query query=em.createQuery("update Person o set o.name=:name where o.id=:id");
		query.setParameter("name", "xxx");
		query.setParameter("id", 3);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}	
}
