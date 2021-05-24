package com.com.hibernate.demo;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {

    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            int id = 1;
            Query<Instructor> query = session.createQuery("SELECT i FROM Instructor i"
                    + "JOIN FETCH i.courses "
                    + "WHERE i.id=:instructorId",
                    Instructor.class);

            query.setParameter("instructorId", id);

            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor: " + instructor);

            System.out.println("Courses: " + instructor.getCourses());

            session.getTransaction().commit();
            session.close();

            System.out.println("Courses: " + instructor.getCourses());

        }finally {
            session.close();
            factory.close();
        }
    }
}
