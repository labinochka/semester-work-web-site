package ru.kpfu.itis.servlet;

import ru.kpfu.itis.model.Account;
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
        String username = req.getParameter("login");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String[] birthday = req.getParameter("birthday").split("\\.");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        Date date = new Date(Integer.parseInt(birthday[2]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[0]));
        Date currentDate = new Date();

        if (accountService.findByUsername(username)) {
            String error = "Этот логин уже используется";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        } else if (((currentDate.getYear() + 1900) - date.getYear()) < 18) {
            String error = "Вам нет 18";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        } else if (accountService.findByEmail(email)){
            String error = "Аккаунт с этой почтой уже существует";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        }else if (!password.equals(repeatPassword)) {
            System.out.println(password + " " + repeatPassword);
            String error = "Пароли не совпадают";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(req, resp);
        } else {
            Account account = new Account(null, username, name, lastname, date, email, password,
                    null, null);
            accountService.save(account);
            req.getRequestDispatcher("/WEB-INF/view/security/signIn.jsp").forward(req, resp);
        }
    }
}
