package ru.kpfu.itis.servlet;

import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.service.BeerService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/types/detail")
public class BeerDetailServlet extends HttpServlet {
    private BeerService beerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        beerService = (BeerService) getServletContext().getAttribute("beerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("id");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad request. No uuid has been provided");
        }
        Beer beer;
        beer = beerService.getDetails(UUID.fromString(uuid));
        if (beer == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
        req.setAttribute("beer", beer);
        req.getRequestDispatcher("/WEB-INF/view/beers/detailBeer.jsp").forward(req, resp);
    }
}
