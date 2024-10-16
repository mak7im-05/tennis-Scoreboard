package com.maxim.tennisscoreboard.dao;

import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchDao {
    public void save(Match match) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.persist(match);
            tx1.commit();
        }
    }
}
