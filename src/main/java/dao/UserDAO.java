package dao;

import entity.dbEntity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.BazaBakaSessionFactory;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDAO {

    public UserEntity findById(int id) {
        return BazaBakaSessionFactory.getBigTennisFactory().openSession().get(UserEntity.class, id);
    }

    public void save(UserEntity appuser) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(appuser);
        tx1.commit();
        session.close();
    }

    public void update(UserEntity appuser) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(appuser);
        tx1.commit();
        session.close();
    }

    public void delete(UserEntity appuser) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(appuser);
        tx1.commit();
        session.close();
    }

    public List<UserEntity> findAll() {
        List<UserEntity> appusers = (List<UserEntity>) BazaBakaSessionFactory.getBigTennisFactory().openSession().createQuery("From UserEntity ").list();
        return appusers;
    }

}
