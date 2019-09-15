package junit.test;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import cn.itcast.bean.Student;
import cn.itcast.bean.Teacher;

public class ManyToManyTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void save(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new Student("С��"));
		em.persist(new Teacher("������ʦ"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	/**
	 * ����ѧ������ʦ�Ĺ�ϵ
	 */
	@Test
	public void buildTS(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Student student=em.find(Student.class, 1);
		student.addTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}	
	
	/**
	 * ���ѧ������ʦ�Ĺ�ϵ
	 */
	@Test
	public void deleteTS(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Student student=em.find(Student.class, 1);
		student.removeTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}		
	
	/**
	 * ɾ����ʦ
	 */
	@Test
	public void deleteTeacher(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Student student=em.find(Student.class, 1);
		Teacher teacher=em.getReference(Teacher.class, 1);
		student.removeTeacher(teacher);		
		em.remove(teacher);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}		
	/**
	 * ɾ��ѧ��
	 */
	@Test
	public void deleteStudent(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("itcast");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Student student=em.find(Student.class, 1);
		em.remove(student);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}			
	
	
	
}
