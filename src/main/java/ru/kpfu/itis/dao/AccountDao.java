package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.util.PasswordUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class AccountDao {

    private ConnectionProvider connectionProvider;

    //language=sql
    final static String SQL_SAVE = "insert into account(username, name, lastname, birthday, email, password) " +
            "values(?, ?, ?, cast(? as date), ?, ?)";

    //language=sql
    final String SQL_GET_BY_USERNAME = "select * from account where username = ?";

    //language=sql
    final String SQL_GET_BY_EMAIL = "select * from account where email = ?";

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

    public Boolean findByUsername(String username) throws DbException {
        ResultSet resultPrepSet = null;
        Boolean res = null;
        try {
            PreparedStatement prepStatement = connectionProvider.getConnection().prepareStatement(SQL_GET_BY_USERNAME);
            prepStatement.setString(1, username);
            resultPrepSet = prepStatement.executeQuery();
            res = resultPrepSet.next();
        } catch (SQLException e) {
            throw new DbException("Can't get account from db.", e);
        }
        return res;
    }

    public Boolean findByEmail(String email) throws DbException {
        ResultSet resultPrepSet = null;
        Boolean res = null;
        try {
            PreparedStatement prepStatement = connectionProvider.getConnection().prepareStatement(SQL_GET_BY_EMAIL);
            prepStatement.setString(1, email);
            resultPrepSet = prepStatement.executeQuery();
            res = resultPrepSet.next();
        } catch (SQLException e) {
            throw new DbException("Can't get account from db.", e);
        }
        return res;
    }

}
