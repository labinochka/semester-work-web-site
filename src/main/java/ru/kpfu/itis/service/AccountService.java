package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.dto.AccountDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        try {
            accountDao = new AccountDao(ConnectionProvider.getInstance());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public void auth(Account account, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("account", account);
    }

    public boolean isNonAnonymous(Account account, HttpServletRequest req, HttpServletResponse resp) {
        return req.getSession().getAttribute("account") != null;
    }

    public void save(AccountDto account) {
        try {
            accountDao.save(account);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findByUsername(String username) {
        try {
            return accountDao.findByUsername(username);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findByEmail(String email) {
        try {
            return accountDao.findByEmail(email);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

}
