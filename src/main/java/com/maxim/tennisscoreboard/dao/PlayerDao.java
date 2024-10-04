package com.maxim.tennisscoreboard.dao;

import com.maxim.tennisscoreboard.models.Player;
import com.maxim.tennisscoreboard.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerDao {
    public void savePlayer(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(player);
        tx1.commit();
        session.close();
    }

    public Player findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM Player WHERE name = :name";
        List<Player> playerList = session.createQuery(hql).setParameter("name", name).getResultList();
        if (!playerList.isEmpty()) {
            return playerList.getFirst();
        } else {
            return null;
        }
    }
}
