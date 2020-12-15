package bigtennis.dao;

import bigtennis.entity.dbEntity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class MatchDAO {

    public MatchEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(MatchEntity.class, id);
    }

    public void save(MatchEntity match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(match);
        tx1.commit();
        session.close();
    }

    public void update(MatchEntity match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(match);
        tx1.commit();
        session.close();
    }

    public void delete(MatchEntity match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(match);
        tx1.commit();
        session.close();
    }

    public List<MatchEntity> get2PlMatches(int quantity, String p1name, String p2name, String league, String courtType) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createNamedQuery("Match.get2PlMatches")
                .setParameter("p1name",p1name)
                .setParameter("p2name",p2name)
                .setParameter("league",league+"%")
                .setParameter("courtType",courtType+"%")
                .setMaxResults(quantity)
                .getResultList();
    }

    public List<MatchEntity> getPlMatches(int quantity, String p1name, String league, String courtType) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createNamedQuery("Match.getPlMatches")
                .setParameter("p1name",p1name)
                .setParameter("league",league+"%")
                .setParameter("courtType",courtType+"%")
                .setMaxResults(quantity)
                .getResultList();
    }

    public List<MatchEntity> byParams(String p1name, String p2name, String score, String set1, String set2, String set3, String set4, String set5, String date, String league, String courtType) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createNamedQuery("Match.byParams")
                .setParameter("p1name",p1name)
                .setParameter("p2name",p2name)
                .setParameter("score",score)
                .setParameter("set1",set1)
                .setParameter("set2",set2)
                .setParameter("set3",set3)
                .setParameter("set4",set4)
                .setParameter("set5",set5)
                .setParameter("date",date)
                .setParameter("league",league)
                .setParameter("courtType",courtType)
                .getResultList();
    }


    public List<MatchEntity> getLastMatches(int quantity, String league, String courtType) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //return session.createQuery(GET_LAST_MATCHES_BY_QUANTITY).setMaxResults(quantity).list();
        return session.createNamedQuery("Match.getMatches")
                .setMaxResults(quantity)
                .setParameter("league",league+"%")
                .setParameter("courtType",courtType+"%")
                .getResultList();
    }


    public List<MatchEntity> byId(PlayerEntity player1, PlayerEntity player2, ResultEntity result, String date, LeagueEntity league, CourtTypeEntity courtType) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        int a = 5;
        return session.createNamedQuery("Match.byId")
                .setParameter("p1id", player1)
                .setParameter("p2id", player2)
                .setParameter("resultid",result)
                .setParameter("date",date)
                .setParameter("leagueid",league)
                .setParameter("courtTypeID",courtType)
                .getResultList();
    }




    public List<MatchEntity> findAll() {
        List<MatchEntity> matches = (List<MatchEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From MatchEntity ").list();
        return matches;
    }

}
