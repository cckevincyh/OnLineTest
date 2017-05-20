package com.cc.onlinetest.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.onlinetest.domain.Admin;
import com.cc.onlinetest.utils.Md5Utils;


public class TestAdmin extends BaseSpring{

	
	@Test
	public void testSaveAdmin(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword(Md5Utils.md5("admin"));
		System.out.println(Md5Utils.md5("cairou"));
		session.save(admin);
		transaction.commit();
		session.close();
	}
}
