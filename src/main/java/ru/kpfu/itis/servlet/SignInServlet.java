package ru.kpfu.itis.servlet;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.util.PasswordUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/security/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("login");
        String password = PasswordUtil.encrypt(req.getParameter("password"));
        String rememberMe = req.getParameter("check");

        Account account;

        if (username.contains("@")) {
            account = accountService.findByEmail(username);
        } else {
            account = accountService.findByUsername(username);
        }

        if (account == null) {
            req.setAttribute("error", "Аккаунт не найден");
            req.getRequestDispatcher("/WEB-INF/view/security/signIn.jsp").forward(req, resp);
        } else {
            if (account.password().equals(password)) {
                accountService.auth(account, req, resp);
                req.setAttribute("account", account);
                req.getRequestDispatcher("/WEB-INF/view/profile/profile.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("error", "Неверный логин или пароль");
                req.getRequestDispatcher("/WEB-INF/view/security/signIn.jsp").forward(req, resp);
            }
        }
    }
}
