//package com.main.dao.bdept;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.main.entity.bdept.LessonB;
//@Repository
//public interface LessonBDAO extends JpaRepository<LessonB,Long> {
//
//	@Query("SELECT lb FORM LessonB lb WHERE lb.share = :share ")
//	public List<LessonB> findByShare(@Param("share")char share);
//
//	@Query("SELECT lb FORM LessonB lb WHERE lb.name = :name")
//	public List<LessonB> findByName(@Param("name") String name);
//
//	@Query("SELECT lb FORM LessonB lb WHERE lb.id = :id")
//	public List<LessonB> findById(@Param("id") String id);
//
//
//	@Query("DELETE FORM LessonB lb WHERE lb.id = :id")
//	@Modifying
//	public void deleteById(@Param("id") String id);
//}
