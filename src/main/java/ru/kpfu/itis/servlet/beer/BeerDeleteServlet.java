package ru.kpfu.itis.servlet.beer;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.BeerService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/types/delete")
public class BeerDeleteServlet extends HttpServlet {

    private BeerService beerService;
    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        beerService = (BeerService) getServletContext().getAttribute("beerService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("id");
        Beer beer = beerService.getById(UUID.fromString(uuid));
        Account author = accountService.getCurrentAccount(req);

        if (author.role().name().equals("admin")) {
            beerService.delete(beer);
            resp.sendRedirect(getServletContext().getContextPath() + "/types/list");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
