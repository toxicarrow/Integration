package com.main.dao.cdept;

import com.main.entity.cdept.ClassC;
import com.main.entity.cdept.StClassC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StClassCDao extends JpaRepository<StClassC, Integer> {
    List<StClassC> findAllByCno(String classNo);
    List<StClassC> findAllBySno(String StudentNo);
    StClassC findByCnoAndSno(String cno,String sno);
    @Transactional
    void deleteByCnoAndSno(String cno,String sno);

    boolean existsByCnoAndSno(String cno,String sno);
}
