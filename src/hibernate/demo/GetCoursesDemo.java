package hibernate.demo;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(Review.class)
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create a session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();
            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("\nLoaded student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());
            session.getTransaction().commit();

        } catch (Exception exc){

            exc.printStackTrace();
        }
        finally {

            session.close();
            factory.close();
        }
    }
}
