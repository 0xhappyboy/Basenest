package com.basenest.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @ClassName ServiceDataSourceConfig
 * @Description Service datasource config (basenest-service).
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Configuration
@MapperScan(
        basePackages = "com.basenest.core.dao.mapper",
        sqlSessionFactoryRef = "serviceSqlSessionFactory",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "com\\.basenest\\.core\\.dao\\.mapper\\.system\\..*"
        )
)
public class ServiceDataSourceConfig {

    @Primary
    @Bean(name = "serviceDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.service")
    public DataSource serviceDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "serviceSqlSessionFactory")
    public SqlSessionFactory serviceSqlSessionFactory(
            @Qualifier("serviceDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.basenest.core.dao.dos");
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/**/*.xml"));
        org.apache.ibatis.session.Configuration cfg = new org.apache.ibatis.session.Configuration();
        cfg.setMapUnderscoreToCamelCase(true);
        cfg.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        bean.setConfiguration(cfg);
        return bean.getObject();
    }
}