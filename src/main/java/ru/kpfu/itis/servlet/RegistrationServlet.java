package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dto.AccountDto;
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
        String[] birthday = req.getParameter("birthday").split("\\.");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        Date date = new Date(Integer.parseInt(birthday[2]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[0]));
        Date currentDate = new Date();

        if (accountService.findByUsername(username) != null) {
            req.setAttribute("error", "Этот логин уже используется");
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        } else if (((currentDate.getYear() + 1900) - date.getYear()) < 18) {
            req.setAttribute("error", "Вам нет 18");
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        } else if (accountService.findByEmail(email) != null) {
            req.setAttribute("error", "Аккаунт с этой почтой уже существует");
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        } else if (!password.equals(repeatPassword)) {
            System.out.println(password + " " + repeatPassword);
            req.setAttribute("error", "Пароли не совпадают");
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        } else {
            AccountDto account = new AccountDto(username, name, lastname, date, email, password);
            accountService.save(account);
            resp.sendRedirect(getServletContext().getContextPath() + "/sign-in");
        }
    }
}
