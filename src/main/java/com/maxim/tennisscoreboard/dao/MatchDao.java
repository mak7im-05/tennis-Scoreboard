package com.maxim.tennisscoreboard.dao;

import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.models.Player;
import com.maxim.tennisscoreboard.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao {
    public void save(Match match) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.persist(match);
            tx1.commit();
        }
    }

    public List<Match> findByName(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "From Match where player1.name=:name or player2.name=:name";
            List<Match> playerList = session.createQuery(hql).setParameter("name", name).getResultList();
            return playerList;
        }
    }
}
