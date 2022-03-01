package model.repository;

import model.utility.PostgresConnection;

import java.sql.Connection;
import java.sql.ResultSet;

public interface RepositoryInterface<E> extends BaseModel<E>{
    default Connection getConnection(){
        return PostgresConnection.connection;
    }
    E getResultSet(ResultSet resultSet);
}
