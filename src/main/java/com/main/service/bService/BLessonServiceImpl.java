package com.main.service.bService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.bdept.LessonBDAO;
import com.main.dao.bdept.SLessonBDAO;
import com.main.entity.bdept.LessonB;
import com.main.entity.bdept.SLessonB;

@Service
public class BLessonServiceImpl implements BLessonService{

	@Autowired
	private LessonBDAO lesson;
	@Autowired
	private SLessonBDAO slesson;
	
	@Override
	public List<LessonB> findByShare(char share) {
		// TODO Auto-generated method stub
		List<LessonB> list = lesson.findByShare(share);
		return list;
	}

	@Override
	public List<LessonB> findById(String id) {
		// TODO Auto-generated method stub
		List<LessonB> list = lesson.findById(id);
		return list;
	}

	@Override
	public List<LessonB> findByName(String name) {
		// TODO Auto-generated method stub
		List<LessonB> list = lesson.findByName(name);
		return list;
	}

	@Override
	public List<SLessonB> findByLessonId(String lid) {
		// TODO Auto-generated method stub
		List<SLessonB> list = slesson.findByLessonId(lid);
		return list;
	}

	@Override
	public List<SLessonB> findByStudentId(String sid) {
		// TODO Auto-generated method stub
		List<SLessonB> list = slesson.findByStudentId(sid);
		return list;
	}

	@Override
	public boolean addStudentToLesson(String sid, String lid) {
		// TODO Auto-generated method stub
		SLessonB slb = new SLessonB(lid,sid,"0");
		slesson.saveAndFlush(slb);
		return true;
	}

	@Override
	public boolean deleteStudentFromLesson(String sid, String lid) {
		// TODO Auto-generated method stub
		slesson.deleteByLessonIdAndStudentId(lid, sid);
		return true;
	}

	@Override
	public boolean addLesson(LessonB lesson) {
		// TODO Auto-generated method stub
		this.lesson.saveAndFlush(lesson);
		return true;
	}

	@Override
	public boolean deleteLessonById(String lid) {
		// TODO Auto-generated method stub
		lesson.deleteById(lid);
		return true;
	}

}
