package ru.kpfu.itis;

import ru.kpfu.itis.dao.BeerDAO;
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
            sce.getServletContext().setAttribute("beerDAO", new BeerDAO(connectionProvider));
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
