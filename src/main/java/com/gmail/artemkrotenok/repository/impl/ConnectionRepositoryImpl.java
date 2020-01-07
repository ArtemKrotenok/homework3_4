package com.gmail.artemkrotenok.repository.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gmail.artemkrotenok.repository.ConnectionRepository;
import com.gmail.artemkrotenok.util.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.gmail.artemkrotenok.repository.constant.ConnectionConstant.DATABASE_PASSWORD;
import static com.gmail.artemkrotenok.repository.constant.ConnectionConstant.DATABASE_URL;
import static com.gmail.artemkrotenok.repository.constant.ConnectionConstant.DATABASE_USERNAME;

public class ConnectionRepositoryImpl implements ConnectionRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private PropertyUtil propertyUtil = new PropertyUtil();

    @Override
    public Connection getConnection() {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            return DriverManager.getConnection(
                    propertyUtil.getProperty(DATABASE_URL),
                    propertyUtil.getProperty(DATABASE_USERNAME),
                    propertyUtil.getProperty(DATABASE_PASSWORD)
            );
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot get connection to database");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot find MySQL driver at classpath");
        }
    }

}