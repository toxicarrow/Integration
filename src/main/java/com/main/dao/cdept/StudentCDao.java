package com.main.dao.cdept;

import com.main.entity.cdept.StudentC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCDao extends JpaRepository<StudentC,String>{
    String findBySnm(String snm);
}
