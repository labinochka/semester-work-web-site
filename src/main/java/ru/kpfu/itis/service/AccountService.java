package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.dto.AccountAuthorDto;
import ru.kpfu.itis.dto.AccountRegistrationDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class AccountService {

    private AccountDao accountDao;

    private final String SESSION_NAME = "account";
    private final String COOKIE_NAME = "username";
    private final static int COOKIE_MAX_AGE = 60 * 60 * 12;
    private final static int COOKIE_MIN_AGE = 0;

    public AccountService() {
        try {
            accountDao = new AccountDao(ConnectionProvider.getInstance());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public void auth(Account account, HttpServletRequest req) {
        req.getSession().setAttribute(SESSION_NAME, account);
    }

    public Account getAccount(HttpServletRequest req) {
        return (Account) req.getSession().getAttribute(SESSION_NAME);
    }

    public void deleteSessionAndCookie(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(SESSION_NAME);
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setMaxAge(COOKIE_MIN_AGE);
        resp.addCookie(cookie);
    }

    public boolean isNonAnonymous(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(COOKIE_NAME)) {
                String username = cookie.getValue();
                req.getSession().setAttribute(SESSION_NAME, getByUsernameOrEmail(username));
                return true;
            }
        }
        return session.getAttribute(SESSION_NAME) != null;
    }

    public void addCookie(String username, HttpServletResponse resp) {
        Cookie cookie = new Cookie(COOKIE_NAME, username);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        resp.addCookie(cookie);
    }

    public void save(AccountRegistrationDto account) {
        try {
            accountDao.save(account);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getByUsername(String username) {
        try {
            return accountDao.getByUsername(username);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getByEmail(String email) {
        try {
            return accountDao.getByEmail(email);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountAuthorDto getById(UUID id) {
        try {
            return accountDao.getById(id);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getByUsernameAndPassword(String username, String password) {
        try {
            return accountDao.getUserByUsernameAndPassword(username, password);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getByUsernameOrEmail(String username) {
        if (username.contains("@")) {
            return getByEmail(username);
        } else {
            return getByUsername(username);
        }
    }
}
