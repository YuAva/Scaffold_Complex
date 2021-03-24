package cn.edu.bupt.hover.scaffold.configurations;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import javax.sql.XADataSource;

@Configuration
@MapperScan(
        basePackages = {"cn.edu.bupt.hover.scaffold.mapper.mysql"},
        sqlSessionFactoryRef = "mysqlSqlSessionFactory",
        sqlSessionTemplateRef = "mysqlSqlSessionTemplate"
)
public class MySQLDataSourceConfiguration {

    @Bean(name = "mysqlXADataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public XADataSource XADataSource() {
        return new DruidXADataSource();
    }

    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource DataSource() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(XADataSource());
        atomikosDataSourceBean.setMaxPoolSize(20); // default 1
        return atomikosDataSourceBean;
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
}
