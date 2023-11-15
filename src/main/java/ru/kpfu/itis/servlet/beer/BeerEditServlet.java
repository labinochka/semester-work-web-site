package ru.kpfu.itis.servlet.beer;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.BeerService;
import ru.kpfu.itis.util.ImageUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet("/types/edit")
public class BeerEditServlet extends HttpServlet {

    private BeerService beerService;
    private AccountService accountService;

    String uuid;
    Beer beer;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        beerService = (BeerService) getServletContext().getAttribute("beerService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uuid = req.getParameter("id");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad request. No uuid has been provided");
        }
        beer = beerService.getById(UUID.fromString(uuid));
        if (beer == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
        req.setAttribute("beer", beer);
        req.getRequestDispatcher("/WEB-INF/view/beers/editBeer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String sort = req.getParameter("sort");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Part image = req.getPart("image");
        String[] file = image.getSubmittedFileName().split("\\.");

        Account author = accountService.getCurrentAccount(req);

        String imageName = null;
        if (file.length > 1) {
            String fileName = file[0] + "-beer-" + uuid.toString() + "." + file[1];
            imageName = ImageUtil.makeFile(image, fileName, req);
        }

        Beer newBeer = new Beer(UUID.fromString(uuid), sort, title, imageName, content);
        if (author.role().name().equals("admin")) {
            if (beerService.update(newBeer, beer, req)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/types/list");
            } else {
                req.getRequestDispatcher("/WEB-INF/view/beers/addBeer.jsp").forward(req, resp);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
