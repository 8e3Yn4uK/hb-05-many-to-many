package hibernate.demo;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourceAndReviewsDemo {

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

            Course tempCourse = new Course("Spring for beginners");

            tempCourse.addReview(new Review("not bad"));

            tempCourse.addReview(new Review("Great!!!"));

            tempCourse.addReview(new Review("not so bad"));

            session.save(tempCourse);

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
