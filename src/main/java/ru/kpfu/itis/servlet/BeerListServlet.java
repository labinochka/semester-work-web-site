package ru.kpfu.itis.servlet;

import ru.kpfu.itis.service.BeerService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class BeerListServlet extends HttpServlet {

    private BeerService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = (BeerService) getServletContext().getAttribute("beerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/beer.jsp").forward(req, resp);
    }
}
