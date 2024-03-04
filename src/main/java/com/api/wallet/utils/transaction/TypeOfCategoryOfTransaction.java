package com.api.wallet.utils.transaction;

import com.api.wallet.db.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeOfCategoryOfTransaction {
    public  static String getTypeCategories(String id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String result = null;
        try{
            String sql = "select type from categories where categoriesId = ? ";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getString("type");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null ){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
