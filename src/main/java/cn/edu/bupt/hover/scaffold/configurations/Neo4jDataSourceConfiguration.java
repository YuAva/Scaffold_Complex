package cn.edu.bupt.hover.scaffold.configurations;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = {"cn.edu.bupt.hover.scaffold.mapper.neo4j"},
        sqlSessionTemplateRef = "neo4jSqlSessionTemplate"
)
public class Neo4jDataSourceConfiguration {

    @Bean(name = "neo4jDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.neo4j")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "neo4jSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("neo4jDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(
                resolver.getResources("classpath*:cn/edu/bupt/hover/scaffold/mapper/**/*.xml")
        );
        sessionFactoryBean.setConfigLocation(resolver.getResource("classpath:config/mybatis-config.xml"));
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "neo4jSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("neo4jSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

    @Bean(name = "neo4jTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
