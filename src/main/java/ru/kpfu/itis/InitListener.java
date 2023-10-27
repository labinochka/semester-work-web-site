package ru.kpfu.itis;

import ru.kpfu.itis.dao.impl.AccountDaoImpl;
import ru.kpfu.itis.dao.impl.BeerDaoImpl;
import ru.kpfu.itis.dao.impl.CommentDaoImpl;
import ru.kpfu.itis.dao.impl.PostDaoImpl;
import ru.kpfu.itis.service.impl.AccountServiceImpl;
import ru.kpfu.itis.service.impl.BeerServiceImpl;
import ru.kpfu.itis.service.impl.CommentServiceImpl;
import ru.kpfu.itis.service.impl.PostServiceImpl;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            sce.getServletContext().setAttribute("beerService", new BeerServiceImpl(new BeerDaoImpl(connectionProvider)));
            sce.getServletContext().setAttribute("accountService", new AccountServiceImpl(new AccountDaoImpl(connectionProvider)));
            sce.getServletContext().setAttribute("postService", new PostServiceImpl(new PostDaoImpl(connectionProvider)));
            sce.getServletContext().setAttribute("commentService", new CommentServiceImpl(new CommentDaoImpl(connectionProvider)));
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
