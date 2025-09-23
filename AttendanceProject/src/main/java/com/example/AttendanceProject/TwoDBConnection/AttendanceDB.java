package com.example.AttendanceProject.TwoDBConnection;

import com.example.AttendanceProject.Model.Attendance;
import com.example.AttendanceProject.Model.Student;
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
@EnableJpaRepositories(basePackages = "com.example.AttendanceProject.Repo",
entityManagerFactoryRef = "attendancemanagerfactory",
transactionManagerRef = "attendanceManager")
public class AttendanceDB {
    @Bean
    @ConfigurationProperties("spring.attendance.datasource")
    public DataSourceProperties attendanceData(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource attedenceDatasource(){
        return attendanceData().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean(name = "attendancemanagerfactory")
    public LocalContainerEntityManagerFactoryBean factoryBean(EntityManagerFactoryBuilder builder){
        Map<String,Object> map=new HashMap<>();
        map.put("hibernate.hbm2ddl.auto","update");
        map.put("hibernate.show_sql",true);
        return builder.dataSource(attedenceDatasource())
                .packages(Student.class,Attendance.class).properties(map).persistenceUnit("attendance").build();
    }
    @Bean(name = "attendanceManager")
    public PlatformTransactionManager manager(@Qualifier("attendancemanagerfactory")LocalContainerEntityManagerFactoryBean factoryBean){
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
