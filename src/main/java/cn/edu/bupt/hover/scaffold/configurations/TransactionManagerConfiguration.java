package cn.edu.bupt.hover.scaffold.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class TransactionManagerConfiguration {

    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManager (
            @Qualifier("mysqlTransactionManager") DataSourceTransactionManager mysqlTransactionManager,
            @Qualifier("neo4jTransactionManager") DataSourceTransactionManager neo4jTransactionManager) {
        return new ChainedTransactionManager(mysqlTransactionManager, neo4jTransactionManager);
    }
}
