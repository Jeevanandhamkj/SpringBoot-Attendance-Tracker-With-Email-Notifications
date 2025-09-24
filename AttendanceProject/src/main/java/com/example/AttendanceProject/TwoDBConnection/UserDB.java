package com.example.AttendanceProject.TwoDBConnection;

import com.example.AttendanceProject.AuthModel.User;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.AttendanceProject.AuthRepo",
entityManagerFactoryRef = "UserentityManagerFactory",
transactionManagerRef = "UsertractionManager")
public class UserDB {
    @Bean
    @ConfigurationProperties("spring.user.datasource")
    public DataSourceProperties userDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource userDatasource(){
        return userDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
    @Bean(name = "UserentityManagerFactory")
    public LocalContainerEntityManagerFactoryBean factoryBean(EntityManagerFactoryBuilder builder){
        Map<String,Object> map=new HashMap<>();
        map.put("hibernate.hbm2ddl.auto","update");
        map.put("hibernate.show_sql",true);
        return builder.dataSource(userDatasource())
                .packages("com.example.AttendanceProject.AuthModel")
                .properties(map).persistenceUnit("User").build();
    }
    @Bean(name = "UsertractionManager")
    public PlatformTransactionManager manager(@Qualifier("UserentityManagerFactory")LocalContainerEntityManagerFactoryBean bean){
        return new JpaTransactionManager(bean.getObject());
    }
}
