package com.main.service.bService;

import java.util.List;

import com.main.entity.bdept.*;
import org.dom4j.Document;

public interface BLessonService {
	//查询数据
	public List<LessonB> findByShare(char share);
	public List<LessonB> findById(String id);
	public List<LessonB> findByName(String name);
	
	public List<SLessonB> findByLessonId(String lid);
	public List<SLessonB> findByStudentId(String sid);
	
	//操作数据
	public boolean addStudentToLesson(String lid,String sid);
	public boolean deleteStudentFromLesson(String lid,String sid);
	
	public boolean addLesson(LessonB lesson);
	public boolean deleteLessonById(String lid);

	Document sendShareClass();
	Boolean chooseShareClass(String sno,String cno);
	List<LessonB> get_AC_Class();
	Boolean solveShareChoose(Document xmlChoice);
}
