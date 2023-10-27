package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.dto.AccountRegistrationDto;
import ru.kpfu.itis.dto.AccountUpdateDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.util.DbException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    private final String SESSION_NAME = "account";
    private final String COOKIE_NAME = "username";
    private final static int COOKIE_MAX_AGE = 60 * 60 * 12;
    private final static int COOKIE_MIN_AGE = 0;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void auth(Account account, HttpServletRequest req) {
        req.getSession().setAttribute(SESSION_NAME, account);
    }

    @Override
    public Account getCurrentAccount(HttpServletRequest req) {
        if (isNonAnonymous(req)) {
            Account account = (Account) req.getSession().getAttribute(SESSION_NAME);
            return getById(account.uuid());
        }
        return null;
    }

    @Override
    public void deleteSessionAndCookie(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(SESSION_NAME);
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setMaxAge(COOKIE_MIN_AGE);
        resp.addCookie(cookie);
    }

    @Override
    public boolean isNonAnonymous(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    String username = cookie.getValue();
                    req.getSession().setAttribute(SESSION_NAME, getByUsernameOrEmail(username));
                    return true;
                }
            }
        }
        return session.getAttribute(SESSION_NAME) != null;
    }

    @Override
    public void addCookie(String username, HttpServletResponse resp) {
        Cookie cookie = new Cookie(COOKIE_NAME, username);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        resp.addCookie(cookie);
    }

    @Override
    public void save(AccountRegistrationDto account) {
        try {
            accountDao.save(account);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(AccountUpdateDto account) {
        try {
            accountDao.update(account);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getByUsername(String username) {
        try {
            return accountDao.getByUsername(username);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getByEmail(String email) {
        try {
            return accountDao.getByEmail(email);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getById(UUID id) {
        try {
            return accountDao.getById(id);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getByUsernameAndPassword(String username, String password) {
        try {
            return accountDao.getUserByUsernameAndPassword(username, password);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getByUsernameOrEmail(String username) {
        if (username.contains("@")) {
            return getByEmail(username);
        } else {
            return getByUsername(username);
        }
    }
}
