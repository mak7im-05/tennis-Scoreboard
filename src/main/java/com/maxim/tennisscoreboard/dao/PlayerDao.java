package com.maxim.tennisscoreboard.dao;

import com.maxim.tennisscoreboard.models.Player;
import com.maxim.tennisscoreboard.util.HibernateSessionFactoryUtil;
import org.hibernate.*;

public class PlayerDao {
    public void addPlayer(Player player) {
        try (SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();

            Transaction tx1 = session.beginTransaction();
            session.persist(player);
            tx1.commit();

            session.close();

            HibernateSessionFactoryUtil.shutdown();
        }

    }
}
