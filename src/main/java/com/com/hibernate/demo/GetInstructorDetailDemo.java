package com.com.hibernate.demo;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            int id = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, 2999);

            System.out.println("Instructor detail: " + instructorDetail);
            System.out.println("Instructor: " + instructorDetail.getInstructor());

            session.getTransaction().commit();

        } catch (Exception exc){
          exc.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

}
