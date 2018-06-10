package com.main.dao.adept;

import com.main.entity.adept.StudentA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentADao extends JpaRepository<StudentA,String> {
    StudentA findBySno(String sno);
    StudentA findByAccount(String account);
    List<StudentA> findAll();
    StudentA findBySnm(String snm);
}
