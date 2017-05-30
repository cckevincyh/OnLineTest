package com.cc.onlinetest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.onlinetest.dao.ScoreDao;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.PageBean;
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
	/**
     * 
     * @param hql传入的hql语句
     * @param pageCode当前页
     * @param pageSize每页显示大小
     * @return
     */
    public List doSplitPage(final String hql,final int pageCode,final int pageSize){
        //调用模板的execute方法，参数是实现了HibernateCallback接口的匿名类，
        return (List) this.getHibernateTemplate().execute(new HibernateCallback(){
            //重写其doInHibernate方法返回一个object对象，
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                //创建query对象
                Query query=session.createQuery(hql);
                //返回其执行了分布方法的list
                return query.setFirstResult((pageCode-1)*pageSize).setMaxResults(pageSize).list();
                 
            }
             
        });
         
    }
	
	@Override
	public PageBean<Score> findScoreByPage(int pageCode, int pageSize) {
		PageBean<Score> pb = new PageBean<Score>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List scoreList = null;
		try {
			String sql = "SELECT count(*) FROM Score";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from Score";
			//分页查询
			scoreList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(scoreList!=null && scoreList.size()>0){
			pb.setBeanList(scoreList);
			return pb;
		}
		return null;
	}

	@Override
	public PageBean<Score> queryMyScore(Subject subject, int pageCode,
			int pageSize) {
		PageBean<Score> pb = new PageBean<Score>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb_sql = new StringBuilder();
		String sql = "SELECT count(*) FROM Score s where 1=1 ";
		String hql= "from Score s where 1=1 ";
		sb.append(hql);
		sb_sql.append(sql);
		if(subject.getCourse().getCourseId()!=-1){
			sb.append(" and s.subject.course.courseId = "+subject.getCourse().getCourseId());
			sb_sql.append(" and s.subject.course.courseId ="+subject.getCourse().getCourseId());
		}
		if(!"".equals(subject.getSubjectName().trim())){
			sb.append(" and s.subject.subjectName like '%" + subject.getSubjectName() +"%'");
			sb_sql.append(" and s.subject.subjectName  like '%" + subject.getSubjectName() +"%'");
		}
		try{
			
			List list = this.getSession().createQuery(sb_sql.toString()).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			
			List<Score> scoreList = doSplitPage(sb.toString(),pageCode,pageSize);
			if(scoreList!=null && scoreList.size()>0){
				pb.setBeanList(scoreList);
				return pb;
			}
		}catch (Throwable e1){
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return null;
	}

	@Override
	public PageBean<Score> findMyScoreByPage(Student student, int pageCode,
			int pageSize) {
		PageBean<Score> pb = new PageBean<Score>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List scoreList = null;
		try {
			String sql = "SELECT count(*) FROM Score s WHERE s.student.studentId=" + student.getStudentId();
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from Score s WHERE s.student.studentId=" + student.getStudentId();
			//分页查询
			scoreList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(scoreList!=null && scoreList.size()>0){
			pb.setBeanList(scoreList);
			return pb;
		}
		return null;
	}

	@Override
	public PageBean<Score> queryScore(Subject subject, Student student,
			int pageCode, int pageSize) {
		PageBean<Score> pb = new PageBean<Score>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb_sql = new StringBuilder();
		String sql = "SELECT count(*) FROM Score s where 1=1 ";
		String hql= "from Score s where 1=1 ";
		sb.append(hql);
		sb_sql.append(sql);
		if(subject.getCourse().getCourseId()!=-1){
			sb.append(" and s.subject.course.courseId = "+subject.getCourse().getCourseId());
			sb_sql.append(" and s.subject.course.courseId ="+subject.getCourse().getCourseId());
		}
		if(!"".equals(subject.getSubjectName().trim())){
			sb.append(" and s.subject.subjectName like '%" + subject.getSubjectName() +"%'");
			sb_sql.append(" and s.subject.subjectName  like '%" + subject.getSubjectName() +"%'");
		}
		if(!"".equals(student.getStudentId().trim())){
			sb.append(" and s.student.studentId = " + student.getStudentId() );
			sb_sql.append(" and s.student.studentId = " + student.getStudentId());
		}
		if(!"".equals(student.getStudentName().trim())){
			sb.append(" and s.student.studentName like '%" + student.getStudentName() +"%'");
			sb_sql.append(" and s.student.studentName like '%" + student.getStudentName()+"%'");
		}
		try{
			
			List list = this.getSession().createQuery(sb_sql.toString()).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			
			List<Score> scoreList = doSplitPage(sb.toString(),pageCode,pageSize);
			if(scoreList!=null && scoreList.size()>0){
				pb.setBeanList(scoreList);
				return pb;
			}
		}catch (Throwable e1){
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return null;
	}

}
