package ru.kpfu.itis.servlet.post;


import ru.kpfu.itis.dto.PostDto;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

@WebServlet("/create")
public class PostCreateServlet extends HttpServlet {
    AccountService accountService;
    PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
        postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/posts/createPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Date dateOfPublication = new Date();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Part image = req.getPart("image");

        PostDto post = new PostDto(accountService.getAccount(req), dateOfPublication, title, content,
                image.getSubmittedFileName());
        postService.save(post);
        resp.sendRedirect(getServletContext().getContextPath() + "/posts/list");

    }
}
