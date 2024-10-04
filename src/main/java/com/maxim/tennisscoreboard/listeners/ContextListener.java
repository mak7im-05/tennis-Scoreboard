package com.maxim.tennisscoreboard.listeners;

import com.maxim.tennisscoreboard.util.HibernateSessionFactoryUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateSessionFactoryUtil.shutdown();
    }
}