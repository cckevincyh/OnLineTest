package com.cc.onlinetest.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.onlinetest.dao.OnLineTestDao;
import com.cc.onlinetest.domain.Answer;
import com.cc.onlinetest.service.OnLineTestService;

public class OnLineTestDaoImpl extends HibernateDaoSupport implements OnLineTestDao{

	@Override
	public boolean addAnswer(Answer answer) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(answer);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}



}
