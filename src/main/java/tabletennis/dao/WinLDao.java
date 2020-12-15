package tabletennis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tabletennis.entity.dbEntity.WinLsEntity;
import util.BazaBakaSessionFactory;

import java.util.List;

public class WinLDao {

    public WinLsEntity findById(int id) {
        return BazaBakaSessionFactory.getTableTennisFactory().openSession().get(WinLsEntity.class, id);
    }

    public void save(WinLsEntity winL) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(winL);
        tx1.commit();
        session.close();
    }

    public void update(WinLsEntity winL) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(winL);
        tx1.commit();
        session.close();
    }

    public void delete(WinLsEntity winL) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(winL);
        tx1.commit();
        session.close();
    }

    public List<WinLsEntity> findAll() {
        List<WinLsEntity> winLs = (List<WinLsEntity>) BazaBakaSessionFactory.getTableTennisFactory().openSession().createQuery("From WinLsEntity ").list();
        return winLs;
    }

}
