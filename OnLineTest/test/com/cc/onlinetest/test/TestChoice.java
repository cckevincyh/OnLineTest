package com.cc.onlinetest.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.onlinetest.domain.Admin;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.utils.Md5Utils;

public class TestChoice extends BaseSpring{

	
	@Test
	public void testSaveChoice(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Subject subject = (Subject) session.get(Subject.class, 1);
		Choice choice = new Choice();
		choice.setAnswer("A");
		choice.setQuestion("下列选项正确的是:");
		choice.setOptionA("选择A");
		choice.setOptionB("选择B");
		choice.setOptionC("选择C");
		choice.setOptionD("选择D");
		choice.setSubject(subject);
		session.save(choice);
		transaction.commit();
		session.close();
	}
}
