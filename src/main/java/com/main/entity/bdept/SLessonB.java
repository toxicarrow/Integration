package com.main.entity.bdept;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "slesson")
public class SLessonB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7582635544034697270L;
	@Id@NotNull
	private String lid;//课程编号
	@Id@NotNull
	private String sid;//学号
	private String score;//得分
	
	public SLessonB(String lid, String sid, String score) {
		// TODO Auto-generated constructor stub
		this.lid = lid;
		this.sid = sid;
		this.score = score;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}

}
