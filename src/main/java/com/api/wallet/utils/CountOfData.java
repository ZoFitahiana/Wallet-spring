package com.api.wallet.utils;

import com.api.wallet.db.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.StringTemplate.STR;

public class CountOfData {
    public Long getCountOfData(String model) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long result = null;
        try {
            String sql = "select count(*) as count from " + model ;
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getLong("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

}
