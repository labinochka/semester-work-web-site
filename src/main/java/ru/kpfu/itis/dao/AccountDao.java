package ru.kpfu.itis.dao;

import ru.kpfu.itis.dto.AccountRegistrationDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.util.PasswordUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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


    public void save(AccountRegistrationDto account)
            throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);
            String date = String.format("%s-%s-%s", account.birthday().getYear(), account.birthday().getMonth(),
                    account.birthday().getDate());
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

    public Account findByUsername(String username) throws DbException {
        ResultSet resultPrepSet;
        try {
            PreparedStatement prepStatement = connectionProvider.getConnection().prepareStatement(SQL_GET_BY_USERNAME);
            prepStatement.setString(1, username);
            resultPrepSet = prepStatement.executeQuery();
            boolean res = resultPrepSet.next();
            if (res) {
                return extractAccount(resultPrepSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get account from db.", e);
        }
    }

    public Account findByEmail(String email) throws DbException {
        ResultSet resultPrepSet;
        try {
            PreparedStatement prepStatement = connectionProvider.getConnection().prepareStatement(SQL_GET_BY_EMAIL);
            prepStatement.setString(1, email);
            resultPrepSet = prepStatement.executeQuery();
            boolean res = resultPrepSet.next();
            if (res) {
                return extractAccount(resultPrepSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get account from db.", e);
        }
    }

    public Account getUserByUsernameAndPassword(String username, String password) throws DbException {
        Account account;
        if (username.contains("@")) {
            account = findByEmail(username);
        } else {
            account = findByUsername(username);
        }
        if (account != null) {
            if (account.password().equals(password)) {
                return account;
            }
        }
        return null;
    }

    public Account extractAccount(ResultSet result) throws DbException {
        Account account = null;
        try {
            account = new Account((UUID) result.getObject("uuid"),
                    result.getString("username"),
                    result.getString("name"),
                    result.getString("lastname"),
                    (Date) result.getObject("birthday"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("avatar"),
                    result.getString("about"));
            return account;

        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }

}
