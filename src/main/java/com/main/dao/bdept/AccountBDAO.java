package com.main.dao.bdept;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.entity.bdept.AccountB;
@Repository
public interface AccountBDAO extends JpaRepository<AccountB,Long> {

	@Query("SELECT ab FROM AccountB ab WHERE ab.username = :username")
	public List<AccountB> findByUserName(@Param("username")String username);
	
	@Query("DELETE FROM AccountB ab WHERE ab.username = :username")
	@Modifying
	public void deleteByUserName(@Param("username")String username);
	
}
