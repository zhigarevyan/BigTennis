package dao;

import entity.dbEntity.LeagueEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.BazaBakaSessionFactory;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class LeagueDAO {

    public LeagueEntity findById(int id) {
        return BazaBakaSessionFactory.getBigTennisFactory().openSession().get(LeagueEntity.class, id);
    }

    public void save(LeagueEntity league) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(league);
        tx1.commit();
        session.close();
    }

    public void update(LeagueEntity league) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(league);
        tx1.commit();
        session.close();
    }

    public void delete(LeagueEntity league) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(league);
        tx1.commit();
        session.close();
    }

    public List<LeagueEntity> findAll() {
        List<LeagueEntity> league = (List<LeagueEntity>) BazaBakaSessionFactory.getBigTennisFactory().openSession().createQuery("From LeagueEntity ").list();
        return league;
    }

    public List<LeagueEntity> byName(String name) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        return session.createNamedQuery("League.byName")
                .setParameter("name", name)
                .getResultList();
    }

}
