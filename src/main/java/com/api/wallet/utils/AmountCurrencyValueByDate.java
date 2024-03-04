package com.api.wallet.utils;

import com.api.wallet.db.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AmountCurrencyValueByDate {
    public static BigDecimal getAmount(LocalDateTime date) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "SELECT amount FROM CurrencyValue WHERE CurrencyValue.date = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, date);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBigDecimal("amount");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'accès à la base de données", e);
        }
        return null;
    }
}
