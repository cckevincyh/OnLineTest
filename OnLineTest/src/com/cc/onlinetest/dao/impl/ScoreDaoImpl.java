package com.cc.onlinetest.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.onlinetest.dao.ScoreDao;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;

public class ScoreDaoImpl extends HibernateDaoSupport implements ScoreDao{

	@Override
	public boolean addScore(Score score) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(score);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	@Override
	public Score getScore(Student student, Subject subject) {
		String hql= "from Score s where s.subject.subjectId=? and s.student.studentId=?";
		List list = this.getHibernateTemplate().find(hql, new Object[]{subject.getSubjectId(),student.getStudentId()});
		if(list!=null && list.size()>0){
			return (Score) list.get(0);
		}
		return null;
	}

}
