package com.main.dao.adept;

import com.main.entity.adept.ClassA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassADao extends JpaRepository<ClassA,String> {
    ClassA findByCno(String cno);
    List<ClassA> findAll();
    List<ClassA> findAllByShare(String share);
}
