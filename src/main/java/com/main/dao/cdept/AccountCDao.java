package com.main.dao.cdept;

import com.main.entity.cdept.AccountC;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * C院系账户
 */
public interface AccountCDao  extends JpaRepository<AccountC, String> {
}
