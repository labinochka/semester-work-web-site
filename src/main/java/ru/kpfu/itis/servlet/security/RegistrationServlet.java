package ru.kpfu.itis.servlet.security;

import ru.kpfu.itis.dto.AccountRegistrationDto;
import ru.kpfu.itis.service.AccountService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("login");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String[] birthday = req.getParameter("birthday").split("-");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        Date date = new Date(Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]));

        AccountRegistrationDto account = new AccountRegistrationDto(username, name, lastname, date, email, password);
        if (accountService.register(account, req, repeatPassword)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/sign-in");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);

        }
    }
}
