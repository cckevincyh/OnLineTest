package com.cc.onlinetest.domain;

import java.util.Set;

public class Student {

	private String studentId;
	private String studentName;
	private String password;
	private Integer lockState;
	
	
	
	
	/**
	 * @return the lockState
	 */
	public Integer getLockState() {
		return lockState;
	}
	/**
	 * @param lockState the lockState to set
	 */
	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
