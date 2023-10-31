package ru.kpfu.itis.dao;

import ru.kpfu.itis.dto.AccountRegistrationDto;
import ru.kpfu.itis.dto.AccountUpdateDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.util.PasswordUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public interface AccountDao {

    void save(Account account) throws DbException;

    void update(Account account) throws DbException;

    Account getByUsername(String username) throws DbException;

    Account getByEmail(String email) throws DbException;

    Account getById(UUID id) throws DbException;

    Account getUserByUsernameAndPassword(String username, String password) throws DbException;

    Account extract(ResultSet result) throws DbException;

}
