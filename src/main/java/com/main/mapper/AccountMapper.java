package com.main.mapper;

import com.main.entity.adept.AccountA;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountMapper {
    // 根据 ID 查询
    @Select("SELECT * FROM account WHERE acc=#{acc}")
    AccountA findByAccount(String acc);
}
