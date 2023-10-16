package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        try {
            accountDao = new AccountDao(ConnectionProvider.getInstance());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Account account) {
        try {
            accountDao.save(account);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean findByUsername(String username) {
        try {
            return accountDao.findByUsername(username);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean findByEmail(String email) {
        try {
            return accountDao.findByEmail(email);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
