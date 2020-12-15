package bigtennis.dao;

import bigtennis.entity.dbEntity.CourtTypeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.BazaBakaSessionFactory;

import java.util.List;

public class CourtTypeDAO {

    public CourtTypeEntity findById(int id) {
        return BazaBakaSessionFactory.getBigTennisFactory().openSession().get(CourtTypeEntity.class, id);
    }

    public void save(CourtTypeEntity courtType) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(courtType);
        tx1.commit();
        session.close();
    }

    public void update(CourtTypeEntity courtType) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(courtType);
        tx1.commit();
        session.close();
    }

    public void delete(CourtTypeEntity courtType) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(courtType);
        tx1.commit();
        session.close();
    }

    public List<CourtTypeEntity> findAll() {
        List<CourtTypeEntity> league = (List<CourtTypeEntity>) BazaBakaSessionFactory.getBigTennisFactory().openSession().createQuery("From CourtTypeEntity ").list();
        return league;
    }

    public List<CourtTypeEntity> byName(String name) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        return session.createNamedQuery("CourtType.byName")
                .setParameter("name", name)
                .getResultList();
    }

}
