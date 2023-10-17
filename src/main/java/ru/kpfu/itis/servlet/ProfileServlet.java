package ru.kpfu.itis.servlet;

import ru.kpfu.itis.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Account account = (Account) req.getSession().getAttribute("account");
        if (account!= null) {
            req.setAttribute("account", account);
            req.getRequestDispatcher("/WEB-INF/view/profile/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(getServletContext().getContextPath() + "/sign-in");
        }
    }
}
