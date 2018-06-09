package com.main.dao.adept;

import com.main.entity.adept.AccountA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountADao extends JpaRepository<AccountA, String> {
    AccountA findByAcc(String acc);
}
