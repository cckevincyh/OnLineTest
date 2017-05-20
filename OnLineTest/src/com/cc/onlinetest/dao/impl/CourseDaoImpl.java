package com.cc.onlinetest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.onlinetest.dao.CourseDao;
import com.cc.onlinetest.domain.Course;
import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.service.CourseService;

public class CourseDaoImpl extends HibernateDaoSupport implements CourseDao{

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
	public PageBean<Course> findCourseByPage(int pageCode, int pageSize) {
		PageBean<Course> pb = new PageBean<Course>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List courseList = null;
		try {
			String sql = "SELECT count(*) FROM Course";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from Course";
			//分页查询
			courseList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(courseList!=null && courseList.size()>0){
			pb.setBeanList(courseList);
			return pb;
		}
		return null;
	}

	@Override
	public boolean addCourse(Course course) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(course);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	@Override
	public Course getCourseByName(Course course) {
		String hql= "from Course c where c.courseName=?";
		List list = this.getHibernateTemplate().find(hql, course.getCourseName());
		if(list!=null && list.size()>0){
			return (Course) list.get(0);
		}
		return null;
	}

	@Override
	public Course getCourseById(Course course) {
		String hql= "from Course c where c.courseId=?";
		List list = this.getHibernateTemplate().find(hql, course.getCourseId());
		if(list!=null && list.size()>0){
			return (Course) list.get(0);
		}
		return null;
	}

	@Override
	public Course updateCourse(Course course) {
		Course newCourse = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			newCourse = (Course) this.getHibernateTemplate().merge(course);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return newCourse;
	}

	@Override
	public boolean deleteCourse(Course course) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().delete(course);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

}
