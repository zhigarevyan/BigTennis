package tabletennis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tabletennis.entity.dbEntity.PlayersEntity;
import util.BazaBakaSessionFactory;

import java.util.List;

public class PlayerDao {

    public PlayersEntity findById(int id) {
        return BazaBakaSessionFactory.getTableTennisFactory().openSession().get(PlayersEntity.class, id);
    }


    public void save(PlayersEntity player) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(player);
        tx1.commit();
        session.close();
    }

    public void update(PlayersEntity player) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(player);
        tx1.commit();
        session.close();
    }

    public void delete(PlayersEntity player) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(player);
        tx1.commit();
        session.close();
    }

    public List<PlayersEntity> findAll() {
        List<PlayersEntity> players = (List<PlayersEntity>) BazaBakaSessionFactory.getTableTennisFactory().openSession().createQuery("From PlayersEntity").list();
        return players;
    }

    public List<PlayersEntity> byName(String name) {
        Session session = BazaBakaSessionFactory.getTableTennisFactory().openSession();
        return session.createNamedQuery("Player.byName")
                .setParameter("name", name)
                .getResultList();
    }

}
