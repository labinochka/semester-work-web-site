package ru.kpfu.itis.servlet.post;

import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.service.impl.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/posts/detail")
public class PostDetailServlet extends HttpServlet {

    PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("id");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad request. No uuid has been provided");
        }
        Post post;
        post = postService.getById(UUID.fromString(uuid));
        if (post == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
        req.setAttribute("post", post);
        req.getRequestDispatcher("/WEB-INF/view/posts/detailPost.jsp").forward(req, resp);
    }

}
