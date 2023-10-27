package ru.kpfu.itis.servlet.profile;

import ru.kpfu.itis.dto.AccountUpdateDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.service.AccountService;
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

@MultipartConfig
@WebServlet("/profile/edit")
public class ProfileEditServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/profile/editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("login");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String about = req.getParameter("about");
        Part image = req.getPart("image");

        Account account = accountService.getCurrentAccount(req);

        if (!username.matches("^[a-zA-Z0-9]+$")) {
            req.setAttribute("error", "Логин может состоять только из латинских букв и цирф");
            req.getRequestDispatcher("/WEB-INF/view/profile/editProfile.jsp").forward(req, resp);
        } else if (accountService.getByUsername(username) != null && !username.equals(account.username())) {
            req.setAttribute("error", "Этот логин уже используется");
            req.getRequestDispatcher("/WEB-INF/view/profile/editProfile.jsp").forward(req, resp);
        } else if (accountService.getByEmail(email) != null && !email.equals(account.email())) {
            req.setAttribute("error", "Аккаунт с этой почтой уже существует");
            req.getRequestDispatcher("/WEB-INF/view/profile/editProfile.jsp").forward(req, resp);
        } else {
            String[] file = image.getSubmittedFileName().split("\\.");
            String imageName = null;

            if (file.length > 1) {
                String fileName = "account-" + account.uuid().toString()+ "." + file[1];
                imageName = ImageUtil.makeFile(image, fileName, req);
            }

            AccountUpdateDto updatedAccount = new AccountUpdateDto(account.uuid(), username, name,
                    lastname, email, about, imageName);
            accountService.update(updatedAccount);
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        }
    }
}
