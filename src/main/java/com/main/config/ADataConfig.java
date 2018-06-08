package com.main.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;
import java.util.Map;


@Configuration
@MapperScan(basePackages = {"com.main.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class ADataConfig {

    @Autowired
    @Qualifier("epsDataSource")
    private DataSource aDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 使用epsDataSource数据源, 连接业务库
        factoryBean.setDataSource(aDataSource);
//        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources("com.mapper/*.xml");
//        factoryBean.setMapperLocations(mapperLocations);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory());
        return template;
    }
}
