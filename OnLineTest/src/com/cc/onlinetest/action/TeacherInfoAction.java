package com.cc.onlinetest.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cc.onlinetest.domain.Teacher;
import com.cc.onlinetest.service.TeacherService;
import com.cc.onlinetest.utils.Md5Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TeacherInfoAction extends ActionSupport{

	private TeacherService  teacherService;


	
	
	
	/**
	 * @param teacherService the teacherService to set
	 */
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}



	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
	
	
	
	






	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}




	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}




	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}








	public String teacherPwd(){
		Teacher teacher = (Teacher) ServletActionContext.getContext().getSession().get("teacher");
		int state = -1;//原密码错误
		//取出原密码进行比对
		if(teacher.getPassword().equals(Md5Utils.md5(oldPwd))){
			if(newPwd.equals(confirmPwd)){
				state = 1;//修改成功
				teacher.setPassword(Md5Utils.md5(newPwd));
				teacher = teacherService.updateTeacherInfo(teacher);
				//重新存入session
				ServletActionContext.getContext().getSession().put("teacher", teacher);
			}else{
				state = 0;//确认密码不一致
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
}
