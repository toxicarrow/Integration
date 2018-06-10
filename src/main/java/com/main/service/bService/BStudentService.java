package com.main.service.bService;

import java.util.List;

import com.main.entity.bdept.*;

public interface BStudentService {

	//查询
	public List<StudentB> findById(String id);
	public List<StudentB> findByName(String name);
	
	//操作
	public boolean addStudent(StudentB student);
	public boolean deleteStudentById(String id);
	
}
