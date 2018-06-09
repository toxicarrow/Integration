package com.main.dao.adept;

import com.main.entity.adept.StClassA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StClassADao extends JpaRepository<StClassA,Integer> {
    List<StClassA> findAllBySno(String sno);
    List<StClassA> findAllByCno(String cno);

    @Transactional
    void deleteBySnoAndCno(String sno,String cno);
    StClassA findBySnoAndCno(String sno,String cno);
}
