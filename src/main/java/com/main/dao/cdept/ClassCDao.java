package com.main.dao.cdept;

import com.main.entity.cdept.AccountC;
import com.main.entity.cdept.ClassC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassCDao extends JpaRepository<ClassC, String> {
    ClassC findByCnm(String className);

    List<ClassC> findAllByShare(String share);
}
