package hibernate.demo;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesDemo {

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
            System.out.println("\nLoad student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());
            Course tempCourse1 = new Course("How to build a house");
            Course tempCourse2 = new Course("How to repair a car");
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);
            System.out.println("\nSaving the courses ...");
            session.save(tempCourse1);
            session.save(tempCourse2);







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
