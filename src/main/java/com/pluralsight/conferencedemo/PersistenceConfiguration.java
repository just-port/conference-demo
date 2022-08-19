package com.pluralsight.conferencedemo;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/conference_demo");
        System.out.println("My custom datasource bean has been initialized and set");
        return builder.build();
    }
}
