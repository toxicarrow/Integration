package com.main.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
@Configuration
public class DataSourceConfig {
    /**
     * mysql数据库，jpa第一数据源
     * 即C院系
     * *实体在entity下cdept
     * @return
     */
    @Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * oracle数据库，jpa第二数据源
     * 即B院系
     * * 实体在entity下bdept
     * @return
     */

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 数据库，使用mybatis
     * (hibernate对sqlite支持太差)
     * 即A院系
     * dao在mapper包下
     * 实体在entity下adept
     * @return
     */
    @Bean(name = "thirdDataSource")
    @Qualifier("thirdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.third")
    public DataSource thirdDataSource() {
        return DataSourceBuilder.create().build();
    }
//    @Bean(name = "epsDataSource")
//    @Qualifier("epsDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.eps")
//    public DataSource ADataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "epsJdbcTemplate")
//    @Primary
//    @Qualifier("epsJdbcTemplate")
//    public NamedParameterJdbcTemplate primaryJdbcTemplate(
//            @Qualifier("epsDataSource") DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
}
