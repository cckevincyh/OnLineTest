package com.cc.onlinetest.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.onlinetest.dao.QuestionDao;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;

public class QuestionDaoImpl extends HibernateDaoSupport implements QuestionDao{

	@Override
	public boolean addChoice(Choice choice) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(choice);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	@Override
	public boolean addJudge(Judge judge) {
		// TODO Auto-generated method stub
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(judge);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	@Override
	public Choice getChoiceById(Choice choice) {
		String hql= "from Choice c where c.choiceId=?";
		List list = this.getHibernateTemplate().find(hql, choice.getChoiceId());
		if(list!=null && list.size()>0){
			return (Choice) list.get(0);
		}
		return null;
	}

	@Override
	public Judge getJudgeById(Judge judge) {
		String hql= "from Judge j where j.judgeId=?";
		List list = this.getHibernateTemplate().find(hql, judge.getJudgeId());
		if(list!=null && list.size()>0){
			return (Judge) list.get(0);
		}
		return null;
	}

}
