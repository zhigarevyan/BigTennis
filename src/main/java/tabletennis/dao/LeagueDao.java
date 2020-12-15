package tabletennis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tabletennis.entity.dbEntity.LeaguesEntity;
import util.BazaBakaSessionFactory;

import java.util.List;

public class LeagueDao {

    public LeaguesEntity findById(int id) {
        return BazaBakaSessionFactory.getTableTennisFactory().openSession().get(LeaguesEntity.class, id);
    }

    public void save(LeaguesEntity league) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(league);
        tx1.commit();
        session.close();
    }

    public void update(LeaguesEntity league) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(league);
        tx1.commit();
        session.close();
    }

    public void delete(LeaguesEntity league) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(league);
        tx1.commit();
        session.close();
    }

    public List<LeaguesEntity> findAll() {
        List<LeaguesEntity> league = (List<LeaguesEntity>) BazaBakaSessionFactory.getTableTennisFactory().openSession().createQuery("From LeaguesEntity ").list();
        return league;
    }

    public List<LeaguesEntity> byName(String name) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        return session.createNamedQuery("League.byName")
                .setParameter("name", name)
                .getResultList();
    }

}
