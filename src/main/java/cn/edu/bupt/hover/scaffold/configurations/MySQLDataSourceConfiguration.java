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
        basePackages = {"cn.edu.bupt.hover.scaffold.mapper.mysql"},
        sqlSessionTemplateRef = "mysqlSqlSessionTemplate"
)
public class MySQLDataSourceConfiguration {

    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Primary
    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(
                resolver.getResources("classpath*:cn/edu/bupt/hover/scaffold/mapper/**/*.xml")
        );
        sessionFactoryBean.setConfigLocation(resolver.getResource("classpath:config/mybatis-config.xml"));
        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
