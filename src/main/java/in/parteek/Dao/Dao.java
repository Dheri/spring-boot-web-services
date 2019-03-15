/**
 * 
 */
package in.parteek.Dao;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.parteek.beans.Student;

/**
 * Created on : 2019-03-14, 9:27:23 a.m.
 *
 * @author Parteek Dheri
 */
public class Dao {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public int addStudent(Student student) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int id;
		synchronized (this) {

			id = (int) session.save(student);
		}

		session.getTransaction().commit();
		session.close();

		return id;
	}

	public void genereateDummyRecords() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 0; i < 10; i++) {

			session.save(new Student(i, "name " + i));

		}

		session.getTransaction().commit();
		session.close();
	}

	public List<Student> getStudents() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Student> students = session.createQuery("from Student").getResultList();
		session.getTransaction().commit();
		session.close();

		return students;
	}

	public int replaceStudents(List<Student> students) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int saved = 0;

		synchronized (this) {
			session.createQuery("DELETE from Student").executeUpdate();

			for (Student s : students) {
				saved += (int) session.save(s);
			}

		}

		session.getTransaction().commit();
		session.close();

		return saved;
	}

}
