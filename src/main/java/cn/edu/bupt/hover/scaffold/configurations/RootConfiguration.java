package cn.edu.bupt.hover.scaffold.configurations;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan({"cn.edu.bupt.hover.scaffold.mapper"})
@EnableTransactionManagement
public class RootConfiguration {

}
