package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.dto.AccountRegistrationDto;
import ru.kpfu.itis.dto.AccountUpdateDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public interface AccountService {

    void auth(Account account, HttpServletRequest req);

    Account getCurrentAccount(HttpServletRequest req);

    void deleteSessionAndCookie(HttpServletRequest req, HttpServletResponse resp);

    boolean isNonAnonymous(HttpServletRequest req);

    void addCookie(String username, HttpServletResponse resp);

    void save(AccountRegistrationDto account);

    void update(AccountUpdateDto account);

    Account getByUsername(String username);

    Account getByEmail(String email);

    Account getById(UUID id);

    Account getByUsernameAndPassword(String username, String password);

    Account getByUsernameOrEmail(String username);
}
