package com.api.wallet.operation;

import com.api.wallet.db.ConnectionDB;
import com.api.wallet.db.entity.Currency;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CurrencyCrudOperation implements CrudOperations<Currency>{
    @Override
    public Currency findById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Currency currency = null;
        try{
            String sql = "select * from currency where currencyId = ?";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String currencyId = resultSet.getString("currencyId");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                currency = new Currency(currencyId,name,code);
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
        return currency;
    }

    @Override
    public List<Currency> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Currency> currencyList = new ArrayList<>();
        try{
            String sql = "select * from currency";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String currencyId = resultSet.getString("currencyId");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                currencyList.add(new Currency(currencyId,name,code));
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
        return currencyList;
    }

    @Override
    public Currency save(Currency toSave) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String sql = "INSERT INTO currency (currencyId, name, code) \n" +
                    "VALUES (?, ?, ?)\n" +
                    "ON CONFLICT (currencyId) \n" +
                    "DO UPDATE SET name = EXCLUDED.name, code = EXCLUDED.code;\n";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getCurrencyId());
            statement.setString(2,toSave.getName());
            statement.setString(3,toSave.getCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
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
        return findById(toSave.getCurrencyId());
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            for (Currency currency : toSave){
                String sql = "INSERT INTO currency (currencyId, name, code) \n" +
                        "VALUES (?, ?, ?)\n" +
                        "ON CONFLICT (currencyId) \n" +
                        "DO UPDATE SET name = EXCLUDED.name, code = EXCLUDED.code;\n";
                connection = ConnectionDB.createConnection();
                statement = connection.prepareStatement(sql);
                statement.setString(1,currency.getCurrencyId());
                statement.setString(2,currency.getName());
                statement.setString(3,currency.getCode());
                statement.executeUpdate();
                findById(currency.getCurrencyId());
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
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
        return toSave;
    }

    @Override
    public Currency update(Currency toUpdate) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String sql = "update currency set name = ? , code = ? where currencyId = ?";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,toUpdate.getName());
            statement.setString(2,toUpdate.getCode());
            statement.setString(3,toUpdate.getCurrencyId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Account with accountId " + toUpdate.getCurrencyId() + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
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
        return findById(toUpdate.getCurrencyId());
    }
}
