package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.BeerDAO;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/types")
public class BeerListServlet extends HttpServlet {

    private BeerDAO beerDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        beerDAO = (BeerDAO) getServletContext().getAttribute("beerDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("beersAle", beerDAO.getBeers("Эль"));
            req.setAttribute("beersLager", beerDAO.getBeers("Лагер"));
            req.setAttribute("beersMixed", beerDAO.getBeers("Смешанное"));
        } catch (DbException e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/WEB-INF/view/beers/listBeer.jsp").forward(req, resp);
    }
}
