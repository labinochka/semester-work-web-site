package ru.kpfu.itis;

import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.BeerService;
import ru.kpfu.itis.service.impl.PostService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("beerService", new BeerService());
        sce.getServletContext().setAttribute("accountService", new AccountService());
        sce.getServletContext().setAttribute("postService", new PostService());
    }
}
