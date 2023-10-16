package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.util.PasswordUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class AccountDao {

    private ConnectionProvider connectionProvider;

    public final static String SQL_SAVE = "insert into account(username, name, lastname, birthday, email, password) " +
            "values(?, ?, ?, cast(? as date), ?, ?)";

    public AccountDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }


    public void save(Account account)
            throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);
            String date = String.format("%s-%s-%s", account.birthday().getYear(), account.birthday().getMonth(),
                    account.birthday().getDay());
            preparedStatement.setString(1, account.username());
            preparedStatement.setString(2, account.name());
            preparedStatement.setString(3, account.lastname());
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, account.email());
            preparedStatement.setString(6, PasswordUtil.encrypt(account.password()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Can't save user", e);
        }
    }
}
