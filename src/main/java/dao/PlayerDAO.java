package dao;

import entity.dbEntity.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.BazaBakaSessionFactory;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class PlayerDAO {

    public PlayerEntity findById(int id) {
        return BazaBakaSessionFactory.getBigTennisFactory().openSession().get(PlayerEntity.class, id);
    }


    public void save(PlayerEntity player) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(player);
        tx1.commit();
        session.close();
    }

    public void update(PlayerEntity player) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(player);
        tx1.commit();
        session.close();
    }

    public void delete(PlayerEntity player) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(player);
        tx1.commit();
        session.close();
    }

    public List<PlayerEntity> findAll() {
        List<PlayerEntity> players = (List<PlayerEntity>) BazaBakaSessionFactory.getBigTennisFactory().openSession().createQuery("From PlayerEntity").list();
        return players;
    }

    public List<PlayerEntity> byName(String name) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        return session.createNamedQuery("Player.byName")
                .setParameter("name", name)
                .getResultList();
    }


}
