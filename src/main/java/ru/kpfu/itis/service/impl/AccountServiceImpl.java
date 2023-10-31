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
import java.util.Date;
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
    public boolean register(AccountRegistrationDto account, HttpServletRequest req, String repeatPassword) {
        Date currentDate = new Date();

        if (!account.username().matches("^[a-zA-Z0-9]+$")) {
            req.setAttribute("error", "Логин может состоять только из латинских букв и цирф");
            return false;
        } else if (getByUsername(account.username()) != null) {
            req.setAttribute("error", "Этот логин уже используется");
            return false;
        } else if (((currentDate.getYear() + 1900) - account.birthday().getYear()) < 18) {
            req.setAttribute("error", "Вам нет 18");
            return false;
        } else if (!account.email().contains("@")) {
            req.setAttribute("error", "Указана неверная почта");
            return false;
        } else if (getByEmail(account.email()) != null) {
            req.setAttribute("error", "Аккаунт с этой почтой уже существует");
            return false;
        } else if (!account.password().equals(repeatPassword)) {
            req.setAttribute("error", "Пароли не совпадают");
            return false;
        } else {
            save(account);
            return true;
        }
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
    public boolean update(AccountUpdateDto updatedAccount, Account oldAccount, HttpServletRequest req) {
        try {
            if (!updatedAccount.username().matches("^[a-zA-Z0-9]+$")) {
                req.setAttribute("error", "Логин может состоять только из латинских букв и цирф");
                return false;
            } else if (getByUsername(updatedAccount.username()) != null && !oldAccount.username().equals(updatedAccount.username())) {
                req.setAttribute("error", "Этот логин уже используется");
                return false;
            } else if (!updatedAccount.email().contains("@")) {
                req.setAttribute("error", "Указана неверная почта");
                return false;
            } else if (getByEmail(updatedAccount.email()) != null && !oldAccount.email().equals(updatedAccount.email())) {
                req.setAttribute("error", "Аккаунт с этой почтой уже существует");
                return false;
            } else {
                accountDao.update(updatedAccount);
                return true;
            }
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
