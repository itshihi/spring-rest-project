//package org.example.springrestproject.config;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@Configuration
//public class PostgreSQLConfig implements ApplicationRunner {
//    private final DataSource dataSource;
//    private final JdbcTemplate jdbcTemplate;
//
//
//    public PostgreSQLConfig(DataSource dataSource, JdbcTemplate jdbcTemplate) {
//        this.dataSource = dataSource;
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) {
//        try(Connection conn = dataSource.getConnection()) {
//            System.out.println("Connected to PostgreSQL database > " + dataSource.getClass());
//            System.out.println("URL > " + conn.getMetaData().getURL());
//            System.out.println("User > " + conn.getMetaData().getUserName());
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        jdbcTemplate.execute("INSERT INTO TBL_TEST VALUES (1, 'testing')");
//    }
//
//
//}
//
//
//
//
