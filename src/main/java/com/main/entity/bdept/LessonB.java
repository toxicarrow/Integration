package com.main.entity.bdept;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lesson")
public class LessonB  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7746505615582569142L;
	@Id@NotNull
	private String id;//编号
	private String name;//名称
	private String time;//课时
	private String point;//学分
	private String teacher;//老师
	private String place;//地点
	private char share;//共享
	
	public void setId(String i) {
		id = i;
	}
	public String getId() {
		return id;
	}
	
	public void setName(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	
	public void setTime(String t) {
		time = t;
	}
	public String getTime() {
		return time;
	}
	
	public void setPoint(String point) {
		this.point = point;
	}
	public String getPoint() {
		return point;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getTeacher() {
		return teacher;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public char getShare() {
		return share;
	}
	public void setShare(char share) {
		this.share = share;
	}
}
