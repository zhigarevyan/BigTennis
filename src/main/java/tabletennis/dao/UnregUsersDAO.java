package tabletennis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tabletennis.entity.dbEntity.UnregUsersEntity;
import util.BazaBakaSessionFactory;

import java.util.List;

public class UnregUsersDAO {

    public UnregUsersEntity findById(int id) {
        return BazaBakaSessionFactory.getTableTennisFactory().openSession().get(UnregUsersEntity.class, id);
    }

    public void save(UnregUsersEntity appuser) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(appuser);
        tx1.commit();
        session.close();
    }

    public void update(UnregUsersEntity appuser) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(appuser);
        tx1.commit();
        session.close();
    }

    public void delete(UnregUsersEntity appuser) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(appuser);
        tx1.commit();
        session.close();
    }

    public List<UnregUsersEntity> findAll() {
        List<UnregUsersEntity> unregUsers = (List<UnregUsersEntity>) BazaBakaSessionFactory.getTableTennisFactory().openSession().createQuery("From UnregUsersEntity ",UnregUsersEntity.class).list();
        return unregUsers;
    }
}
