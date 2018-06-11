//package com.main.service.bService;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.main.dao.bdept.StudentBDAO;
//import com.main.entity.bdept.StudentB;
//
//@Service
//public class BStudentServiceImpl implements BStudentService {
//
//	@Autowired
//	private StudentBDAO student;
//
//	@Override
//	public List<StudentB> findById(String id) {
//		// TODO Auto-generated method stub
//		List<StudentB> list = student.findById(id);
//		return list;
//	}
//
//	@Override
//	public List<StudentB> findByName(String name) {
//		// TODO Auto-generated method stub
//		List<StudentB> list = student.findByName(name);
//		return list;
//	}
//
//	@Override
//	public boolean addStudent(StudentB student) {
//		// TODO Auto-generated method stub
//		this.student.saveAndFlush(student);
//		return true;
//	}
//
//	@Override
//	public boolean deleteStudentById(String id) {
//		// TODO Auto-generated method stub
//		this.student.DeleteById(id);
//		return true;
//	}
//
//}
