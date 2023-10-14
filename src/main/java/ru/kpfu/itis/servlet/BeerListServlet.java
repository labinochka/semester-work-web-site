package ru.kpfu.itis.servlet;

import ru.kpfu.itis.service.BeerService;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/types/list")
public class BeerListServlet extends HttpServlet {

    private BeerService beerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        beerService = (BeerService) getServletContext().getAttribute("beerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("beersAle", beerService.getBeers("Эль"));
        req.setAttribute("beersLager", beerService.getBeers("Лагер"));
        req.setAttribute("beersMixed", beerService.getBeers("Смешанное"));
        req.getRequestDispatcher("/WEB-INF/view/beers/listBeer.jsp").forward(req, resp);
    }
}
