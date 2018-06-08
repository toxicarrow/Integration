package com.main.dao.cdept;

import com.main.entity.cdept.ClassC;
import com.main.entity.cdept.StClassC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StClassCDao extends JpaRepository<StClassC, Integer> {
    Page<StClassC> findAllByCno(String classNo);
    Page<StClassC> findAllBySno(String StudentNo);

}
