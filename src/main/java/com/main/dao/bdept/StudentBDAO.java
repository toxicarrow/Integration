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
//import com.main.entity.bdept.StudentB;
//
//@Repository
//public interface StudentBDAO extends JpaRepository<StudentB, Long>{
//
//
//	@Query("SELECT sb FROM StudentB sb WHERE sb.name = :name")
//	public List<StudentB> findByName(@Param("name") String name);
//
//	@Query("SELECT sb FROM StudentB sb WHERE sb.id = :id")
//	public List<StudentB> findById(@Param("id") String id);
//
//	@Query("DELETE FROM StudentB sb WHERE sb.id = :id")
//	@Modifying
//	public void DeleteById(@Param("id") String id);
//
//	@Query("SELECT sb FROM StudentB sb WHERE sb.major = :major")
//	public List<StudentB> findByMajor(@Param("major") String major);
//}
