package com.gmail.artemkrotenok.repository;

import java.sql.Connection;

public interface ConnectionRepository {

    Connection getConnection();

}