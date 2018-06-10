package com.main.dao.bdept;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.entity.bdept.SLessonB;
@Repository
public interface SLessonBDAO extends JpaRepository<SLessonB,Long> {

	@Query("SELECT slb FROM SLessonB slb WHERE slb.sid = :sid")
	public List<SLessonB> findByStudentId(@Param("sid") String sid);
	
	@Query("DELETE FROM SLessonB slb WHERE slb.sid = :sid")
	@Modifying
	public void deleteByStudentId(@Param("sid") String sid);
	
	@Query("SELECT slb FROM SLessonB slb WHERE slb.lid = :lid")
	public List<SLessonB> findByLessonId(@Param("lid") String lid);
	
	
	@Query("DELETE FROM SLessonB slb WHERE slb.lid = :lid")
	@Modifying
	public void deleteByLessonId(@Param("lid") String lid);
	
	
	@Query("SELECT slb FROM SLessonB slb WHERE slb.lid = :lid AND slb.sid = :sid")
	public List<SLessonB> findByLessonIdAndStudentId(@Param("lid") String lid,@Param("sid") String sid);
	
	@Query("DELETE FROM SLessonB slb WHERE slb.lid = :lid AND slb.sid = :sid")
	@Modifying
	public void deleteByLessonIdAndStudentId(@Param("lid") String lid,@Param("sid") String sid);
	
}
